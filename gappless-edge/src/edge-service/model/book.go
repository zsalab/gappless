package model

type Book struct {
	Id        *int64  `json:"id"`
	Title     *string `json:"title"`
	NrOfPages *int32  `json:"nrOfPages,omitempty"`
}
