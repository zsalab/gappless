#! /bin/sh

owd="$(pwd)"
cd "$(dirname $0)"
GOPATH="$(dirname $(dirname $(pwd)))" go $@
cd "$owd"
