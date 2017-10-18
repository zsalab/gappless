package handlerCommon

import (
	"github.com/gorilla/mux"
	"net/http"
	"strconv"
)

func GetId(httpResponseWriter http.ResponseWriter, httpRequest *http.Request) *int64 {
	id, err := strconv.ParseInt(mux.Vars(httpRequest)["id"], 10, 0)
	if err != nil {
		RespondError(httpResponseWriter, http.StatusBadRequest, err.Error())
		return nil
	}
	return &id
}
