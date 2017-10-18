## Prereuisites

- on PATH: go, protoc gradle

- installed docker (or manual startup)

## Install prereuisites on Mac OS X

brew update && brew install go gradle protobuf

go get -u github.com/golang/protobuf/protoc-gen-go

## clone from github

git clone https://github.com/zsalab/gappless.git

## Build projects

gradle build

## Start up the containers (one go edge with two java backend shard)

docker-compose up -d

## Add some book with specified ID (just to be able to update and delete based on fixed ID)

curl -H 'Accept: application/json' -X POST -d '{ "id": 1, "title": "first book", "nrOfPages": 1 }'  http://localhost:8180/books

curl -H 'Accept: application/json' -X POST -d '{ "id": 2, "title": "second book", "nrOfPages": 2 }'  http://localhost:8180/books

curl -H 'Accept: application/json' -X POST -d '{ "id": 3, "title": "3rd book", "nrOfPages": 3 }'  http://localhost:8180/books

## List back all books

curl -X GET http://localhost:8180/books

## Add some more with generated ID

for i in `seq 1 100`; do curl -H 'Accept: application/json' -X POST -d "{ \"title\": \"$i book\", \"nrOfPages\": $i }"  http://localhost:8180/books; done

## List back all books

curl -X GET http://localhost:8180/books

## Update book #1

curl -H 'Accept: application/json' -X PUT -d '{ "id": 1, "title": "updated book", "nrOfPages": 1000 }'  http://localhost:8180/books/1

## Read back #1

curl -X GET http://localhost:8180/books/1

## Delete book #1

curl -X DELETE http://localhost:8180/books/1

## Try to read back #1

curl -X GET http://localhost:8180/books/1

## Shutdown

docker-compose down --rmi all
