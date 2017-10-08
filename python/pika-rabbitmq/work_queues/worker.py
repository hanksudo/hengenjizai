#!/usb/bin/env python
import pika
import time

connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
channel = connection.channel()

channel.queue_declare(queue='task_queue', durable=True)

print ' [*] Waiting for messages. To exit press CTRL+C'


def callback(ch, method, properties, body):
    print " [x] Received %r" % (body,)
    if int(body) % 2 == 0:
        time.sleep(5)
    time.sleep(body.count('.'))
    print " [x] Done"
    ch.basic_ack(delivery_tag=method.delivery_tag)

# fair dispatch, if not setup, will be round-robin dispatching
channel.basic_qos(prefetch_count=1)

channel.basic_consume(callback, queue='task_queue')
channel.start_consuming()
