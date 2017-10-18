package main

import (
	"edge-service/handler/book"
	"flag"
	"github.com/gorilla/mux"
	"log"
	"net/http"
	"strings"
)

type arrayFlags []string

func (item *arrayFlags) Set(value string) error {
	*item = append(*item, value)
	return nil
}
func (item *arrayFlags) String() string {
	return strings.Join(*item, ", ")
}

// Default Request Handler
func defaultHandler(w http.ResponseWriter, r *http.Request) {
	w.Write([]byte("<h1>Hello</h1>"))
}

func main() {
	var serverAddresses arrayFlags
	listen := flag.String("listen", ":8180", "Server listen address like 127.0.0.1:8180")
	flag.Var(&serverAddresses, "backend", "Backend server address add multiple time for sharding")
	flag.Parse()
	if serverAddresses == nil {
		log.Fatal("least one backend address required")
	}
	if len(serverAddresses) > 10 {
		log.Fatal("maximum 10 shards allowed")
	}
	bookHandler.InitPools(serverAddresses)
	router := mux.NewRouter()
	router.HandleFunc("/", defaultHandler)
	router.HandleFunc("/books", bookHandler.List).Methods("GET")
	router.HandleFunc("/books/{id:[0-9]+}", bookHandler.Get).Methods("GET")
	router.HandleFunc("/books", bookHandler.Add).Methods("POST")
	router.HandleFunc("/books/{id:[0-9]+}", bookHandler.Update).Methods("PUT", "PATCH")
	router.HandleFunc("/books/{id:[0-9]+}", bookHandler.Delete).Methods("DELETE")
	log.Fatal(http.ListenAndServe(*listen, router))
}
