package handlerCommon

import (
	"net/http"
	"net/http/httptest"
	"testing"
)

type testStruct struct {
	F1 int64  `json:"f1"`
	F2 string `json:"f2"`
}

func TestRespondJSON(t *testing.T) {
	w := httptest.NewRecorder()

	RespondJSON(w, http.StatusOK, &testStruct{F1: 1, F2: "str"})

	if w.Body.String() != "{\"f1\":1,\"f2\":\"str\"}" {
		t.Error("Expected result is `{\"f1\":1,\"f2\":\"str\"}` but got ", w.Body.String())
	}
}
