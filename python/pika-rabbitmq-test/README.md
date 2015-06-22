# pika test with RabbitMQ

- [pika](https://pika.readthedocs.org/en/0.9.14/)
- [RabbitMQ](http://www.rabbitmq.com/)
- [RabbitMQ Note](https://gist.github.com/hanksudo/09b9a854d91e072b8d2d)

## Hello World
## Work Queues
## Publish / Subscribe
## Routing

- emit_log

``` bash
python emit_log_direct.py
python emit_log_direct.py "yo"
python emit_log_direct.py info "information for you"
python emit_log_direct.py error "get some error"
python emit_log_direct.py warning "get some warning"
```

- receive_log

``` bash
python receive_logs_direct.py info
python receive_logs_direct.py info error warning
```

## Topics

- emit_log

``` bash
python emit_log_topic.py
python emit_log_topic.py "kern.critical" "A critical kernel error"
```

- receive_log

``` bash
python receive_logs_topic.py "anonymous.*"
python receive_logs_topic.py "#"
python receive_logs_topic.py "kern.*"
python receive_logs_topic.py "*.critical"
python receive_logs_topic.py "kern.*" "*.critical"
```

## Remote procedure call (RPC)

