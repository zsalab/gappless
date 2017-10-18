#! /bin/sh

owd="$(pwd)"
cd "$(dirname $0)"
GP="$(dirname $(dirname $(pwd)))"
cd "$owd"
GOPATH="$GP" go $@
