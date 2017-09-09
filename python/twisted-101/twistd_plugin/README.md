# Twistd plugin

[Writing a twistd Plugin](http://twistedmatrix.com/documents/current/core/howto/tap.html)

```bash
twistd myproject --help
twistd -n myproject

twistd myproject2 --help
twistd -n myproject2  # use tap plugin
```

if `twistd` can't find command, then set env:

```bash
export PYTHONPATH=""
```