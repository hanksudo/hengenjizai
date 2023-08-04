# connect-go-example

## Requirements

```bash
go install github.com/bufbuild/buf/cmd/buf@latest
go install github.com/fullstorydev/grpcurl/cmd/grpcurl@latest
go install google.golang.org/protobuf/cmd/protoc-gen-go@latest
go install connectrpc.com/connect/cmd/protoc-gen-connect-go@latest
```

## buf

```bash
buf lint
buf generate
```

## Test

```bash
go run ./cmd/server/main.go
```

Made request by curl and grpcurl

```bash
curl \
    --header "Content-Type: application/json" \
    --data '{"name": "Hank"}' \
    http://localhost:8080/greet.v1.GreetService/Greet
grpcurl \
    -protoset <(buf build -o -) -plaintext \
    -d '{"name": "Hank"}' \
    localhost:8080 greet.v1.GreetService/Greet
```

Made request by go client

```bash
go run ./cmd/client/main.go
```