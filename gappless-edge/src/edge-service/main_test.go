package main

import (
	"net/http"
	"net/http/httptest"
	"testing"
)

func TestDefaultHandler(t *testing.T) {
	r, _ := http.NewRequest("GET", "/", nil)
	w := httptest.NewRecorder()

	defaultHandler(w, r)

	if w.Body.String() != "<h1>Hello</h1>" {
		t.Error("Expected result Hello but got ", w.Body.String())
	}
}
