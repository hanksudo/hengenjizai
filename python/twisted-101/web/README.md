# Web

## simple server

```bash
python simpler_server_1.py
curl http://localhost:8080/
curl http://localhost:8080/fred
curl http://localhost:8080/bob
```

## rpc script

[.rpy scripts](http://twistedmatrix.com/documents/current/web/howto/using-twistedweb.html#rpy-scripts)

```bash
twistd -n web --path rpy/
curl http://localhost:8080/test.rpy
```

## Session

[session](http://twistedmatrix.com/documents/current/web/howto/using-twistedweb.html#session)

```bash
python session.py
```

use browser to open

- [http://localhost:8080](http://localhost:8080)
- [http://localhost:8080/expire](http://localhost:8080/expire)