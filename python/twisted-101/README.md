# Twisted 101

Twisted is an event-driven networking engine

- [Twisted](https://twistedmatrix.com/trac/)

## Installation

```bash
mkvirtualenv twisted-101 -p python2
pip install twisted

# Serving Static Content From a directory
twistd -n web --path ./
```

## echo server

```bash
python echo_server.py
telnet 127.0.0.1 1234
```