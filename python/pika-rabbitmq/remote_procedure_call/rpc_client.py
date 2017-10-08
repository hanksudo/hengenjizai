#!/usr/bin/env python
import pika
import uuid
import sys

class FibonacciRpcClient(object):
    def __init__(self):
        self.connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))

        self.channel = self.connection.channel()

        result = self.channel.queue_declare(exclusive=True)
        self.callback_queue = result.method.queue
        self.channel.basic_consume(self.on_response, no_ack=True, queue=self.callback_queue)

    def on_response(self, ch, method, properties, body):
        if self.correlation_id == properties.correlation_id:
            self.response = body

    def call(self, n):
        self.response = None
        self.correlation_id = str(uuid.uuid4())
        self.channel.basic_publish(
            exchange='',
            routing_key='rpc_queue',
            properties=pika.BasicProperties(
                reply_to=self.callback_queue,
                correlation_id=self.correlation_id
            ),
            body=str(n)
        )
        while self.response is None:
            self.connection.process_data_events()
        return int(self.response)

fibonacci_rpc = FibonacciRpcClient()

n = sys.argv[1] if len(sys.argv) > 1 else 30
print " [x] Requesting fib(%r)" % n
response = fibonacci_rpc.call(n)
print " [.] Got %r" % (response,)
