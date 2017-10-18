package bookHandler

import (
	"edge-service/handler"
	"edge-service/model"
	"edge-service/protobuf"
	"encoding/json"
	"fmt"
	"github.com/DMXRoid/grpoolc"
	"golang.org/x/net/context"
	"google.golang.org/grpc"
	"io"
	"log"
	"net/http"
	"time"
)

type rpcFunc func(client protobuf.BookServiceClient, ctx context.Context, payload interface{}) (interface{}, error)

var (
	shardCnt int
)

func ptrInt64(i int64) *int64 {
	return &i
}

func InitPools(serverAddresses []string) {
	shardCnt = len(serverAddresses)
	for shard := 0; shard < shardCnt; shard++ {
		newPool(&serverAddresses[shard], fmt.Sprintf("backend-service%d", shard))
	}
}

func newPool(serverAddr *string, poolName string) {
	newConnectionGenerator := func() (*grpc.ClientConn, error) {
		return grpc.Dial(*serverAddr, grpc.WithInsecure())
	}
	grpoolc.New(poolName, newConnectionGenerator, 3)
}

func decodeBook(httpResponseWriter http.ResponseWriter, httpRequest *http.Request) *model.Book {
	book := model.Book{}
	decoder := json.NewDecoder(httpRequest.Body)
	if err := decoder.Decode(&book); err != nil {
		handlerCommon.RespondError(httpResponseWriter, http.StatusBadRequest, err.Error())
		return nil
	}
	defer httpRequest.Body.Close()
	return &book
}

func callGrpc(shard int, rpcCall rpcFunc, payload interface{}) (interface{}, error) {
	srv := fmt.Sprintf("backend-service%d", shard)
	conn, err := grpoolc.Get(srv)
	if err != nil {
		log.Print("poolGet: " + err.Error())
		return nil, err
	}
	defer grpoolc.Put(srv, conn)
	client := protobuf.NewBookServiceClient(conn)
	ctx := context.Background()

	resp, err := rpcCall(client, ctx, payload)
	if err != nil {
		log.Print("rpcCall: " + err.Error())
	}
	return resp, err
}

func bookRestResponse(w http.ResponseWriter, protoBook *protobuf.Book, successCode int, err error) {
	if err != nil {
		handlerCommon.RespondError(w, http.StatusInternalServerError, err.Error())
		return
	}
	book := &model.Book{Id: &protoBook.Id, Title: &protoBook.Title, NrOfPages: &protoBook.NrOfPage}
	handlerCommon.RespondJSON(w, successCode, book)
}

func rpcList(client protobuf.BookServiceClient, ctx context.Context, payload interface{}) (interface{}, error) {
	resp, err := client.List(ctx, payload.(*protobuf.Empty))
	return resp, err
}

type chItem struct {
	book *protobuf.Book
	err  error
}

func List(w http.ResponseWriter, r *http.Request) {
	ch := make(chan chItem, 1)
	listClients := make([]protobuf.BookService_ListClient, shardCnt)
	for shard := 0; shard < shardCnt; shard++ {
		resp, err := callGrpc(shard, rpcList, &protobuf.Empty{})
		if err != nil {
			handlerCommon.RespondError(w, http.StatusInternalServerError, err.Error())
		}
		listClients[shard] = resp.(protobuf.BookService_ListClient)
		go func(listClient protobuf.BookService_ListClient) {
			for {
				book, err := listClient.Recv()
				ch <- chItem{book: book, err: err}
				if err != nil {
					break
				}
			}
		}(listClients[shard])
	}

	books := make([]model.Book, 0)
	activeChannelCount := shardCnt
	for {
		item := <-ch
		if item.book != nil {
			books = append(books, model.Book{Id: &item.book.Id, Title: &item.book.Title, NrOfPages: &item.book.NrOfPage})
		}
		if item.err != nil {
			if item.err != io.EOF {
				for shard := 0; shard < shardCnt; shard++ {
					listClients[shard].CloseSend()
				}
				handlerCommon.RespondError(w, http.StatusInternalServerError, item.err.Error())
				return
			}
			activeChannelCount--
			if activeChannelCount == 0 {
				break
			}
		}
	}

	handlerCommon.RespondJSON(w, http.StatusOK, books)
}

func rpcGet(client protobuf.BookServiceClient, ctx context.Context, payload interface{}) (interface{}, error) {
	resp, err := client.Read(ctx, payload.(*protobuf.Id))
	return resp, err
}

func Get(w http.ResponseWriter, r *http.Request) {
	if id := handlerCommon.GetId(w, r); id != nil {
		resp, err := callGrpc(int(*id%int64(shardCnt)), rpcGet, &protobuf.Id{Id: *id})
		bookRestResponse(w, resp.(*protobuf.Book), http.StatusOK, err)
	}
}

func rpcCreate(client protobuf.BookServiceClient, ctx context.Context, payload interface{}) (interface{}, error) {
	resp, err := client.Create(ctx, payload.(*protobuf.Book))
	return resp, err
}

func Add(w http.ResponseWriter, r *http.Request) {
	if book := decodeBook(w, r); book != nil {
		// for primitive testing allow the clients to provide the ID
		if book.Id == nil {
			// super simple Long ID generation - it would be better to use UUID
			book.Id = ptrInt64(time.Now().UnixNano())
		}
		resp, err := callGrpc(int(*book.Id%int64(shardCnt)), rpcCreate, &protobuf.Book{Id: *book.Id, Title: *book.Title, NrOfPage: *book.NrOfPages})
		bookRestResponse(w, resp.(*protobuf.Book), http.StatusCreated, err)
	}
}

func rpcUpdate(client protobuf.BookServiceClient, ctx context.Context, payload interface{}) (interface{}, error) {
	resp, err := client.Update(ctx, payload.(*protobuf.Book))
	return resp, err
}

func Update(w http.ResponseWriter, r *http.Request) {
	if r.Method == "PATCH" {
		handlerCommon.RespondError(w, http.StatusNotAcceptable, "PATCH not supported yet")
		return
	}
	if id := handlerCommon.GetId(w, r); id != nil {
		if book := decodeBook(w, r); book != nil {
			if *id != *book.Id {
				handlerCommon.RespondError(w, http.StatusNotAcceptable, "ID change not supported yet")
				return
			}
			resp, err := callGrpc(int(*id%int64(shardCnt)), rpcUpdate, &protobuf.Book{Id: *book.Id, Title: *book.Title, NrOfPage: *book.NrOfPages})
			bookRestResponse(w, resp.(*protobuf.Book), http.StatusOK, err)
		}
	}
}

func rpcDelete(client protobuf.BookServiceClient, ctx context.Context, payload interface{}) (interface{}, error) {
	resp, err := client.Delete(ctx, payload.(*protobuf.Id))
	return resp, err
}

func Delete(w http.ResponseWriter, r *http.Request) {
	if id := handlerCommon.GetId(w, r); id != nil {
		_, err := callGrpc(int(*id%int64(shardCnt)), rpcDelete, &protobuf.Id{Id: *id})
		if err != nil {
			handlerCommon.RespondError(w, http.StatusInternalServerError, err.Error())
			return
		}
	}
}
