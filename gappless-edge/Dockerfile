FROM golang:1.9-alpine
VOLUME /tmp
ADD src/edge-service /go/src/edge-service
WORKDIR /go/src/edge-service
RUN go build -o /go/bin/edge-service .
ENTRYPOINT exec /go/bin/edge-service ${ARGS}
