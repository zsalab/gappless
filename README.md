# Test project for hybrid go and java gRPC communication

## go service

The service has one optional (listen) and one mandatory command line parameter (backend). Maximum 10 backend supported, all backend address introduce a new shard.

Example with specific port and three shard
```
./gappless-edge/bin/edge-service --listen 127.0.0.1:8181 --backend 127.0.0.1:9100 --backend 127.0.0.1:9101 --backend 127.0.0.1:9102
```
Simple example without sharding (listen on default port: 8180)
```
./gappless-edge/bin/edge-service --backend 127.0.0.1:9110
```

TODO: improve test coverage (especially grpc)

## java service

The service listen on 9110 port by default, but it is possible to change with the grpc.port command line argument. The service uses H2 memory database.

Example start with specific port
```
java -jar java -jar gappless-backend/build/libs/gappless-backend-1.0.0.jar --grpc.port=9200
```

TODO: improve test coverage

## Prereuisites

- on PATH: go, protoc, protoc-gen-go, mockgen and gradle

- installed docker (or manual startup)

## Install prereuisites on Mac OS X

```
brew update && brew install go gradle protobuf
go get -u github.com/golang/protobuf/protoc-gen-go
go get -u github.com/golang/mock/mockgen
export GOPATH="$HOME/go"
export GOROOT="$(brew --prefix golang)/libexec"
export PATH="$GOPATH/bin:$GOROOT/bin:$PATH"
```

## clone from github

```
git clone https://github.com/zsalab/gappless.git
```

## Build projects

```
gradle build
```

## Testing with docker

Start up the containers (one go edge with two java backend shard)

```
docker-compose up -d
```

Add some book with specified ID (just to be able to update and delete based on fixed ID)

```
curl -H 'Accept: application/json' -X POST -d '{ "id": 1, "title": "first book", "nrOfPages": 1 }'  http://localhost:8180/books
curl -H 'Accept: application/json' -X POST -d '{ "id": 2, "title": "second book", "nrOfPages": 2 }'  http://localhost:8180/books
curl -H 'Accept: application/json' -X POST -d '{ "id": 3, "title": "3rd book", "nrOfPages": 3 }'  http://localhost:8180/books
```

List back all books

```
curl -X GET http://localhost:8180/books
```

Add some more with generated ID

```
for i in `seq 1 100`; do curl -H 'Accept: application/json' -X POST -d "{ \"title\": \"$i book\", \"nrOfPages\": $i }"  http://localhost:8180/books; done
```

List back all books

```
curl -X GET http://localhost:8180/books
```

Update book #1

```
curl -H 'Accept: application/json' -X PUT -d '{ "id": 1, "title": "updated book", "nrOfPages": 1000 }'  http://localhost:8180/books/1
```

Read back #1

```
curl -X GET http://localhost:8180/books/1
```

Delete book #1

```
curl -X DELETE http://localhost:8180/books/1
```

Try to read back #1

```
curl -X GET http://localhost:8180/books/1
```

Shutdown and remove images

```
docker-compose down --rmi all
```
