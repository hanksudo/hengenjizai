# RabbitMQ with pika

- [pika](https://pika.readthedocs.io/en/0.11.0/)
- [RabbitMQ](http://www.rabbitmq.com/)
- [RabbitMQ Note](https://gist.github.com/hanksudo/09b9a854d91e072b8d2d)

## Tutorials

### Hello World

![](https://www.rabbitmq.com/img/tutorials/python-one-overall.png)

### Work Queues

![](https://www.rabbitmq.com/img/tutorials/python-two.png)

- Create two workers

```bash
python worker.py
python worker.py
```

- Add multi tasks with different dots

```bash
python new_task.py .....................
python new_task.py ...
python new_task.py ...
python new_task.py ...
```

### Publish / Subscribe

### Routing

- emit_log

```bash
python emit_log_direct.py
python emit_log_direct.py "yo"
python emit_log_direct.py info "information for you"
python emit_log_direct.py error "get some error"
python emit_log_direct.py warning "get some warning"
```

- receive_log

```bash
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

