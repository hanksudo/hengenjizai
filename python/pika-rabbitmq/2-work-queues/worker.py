#!/usb/bin/env python
import time
import pika

connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
channel = connection.channel()

channel.queue_declare(queue='task_queue', durable=True)
print(' [*] Waiting for messages. To exit press CTRL+C')

def callback(ch, method, properties, body):
    print(" [x] Received %r" % body)
    time.sleep(body.count(b'.'))
    print(" [x] Done")
    ch.basic_ack(delivery_tag=method.delivery_tag)

# fair dispatch, if not setup, will be round-robin dispatching
channel.basic_qos(prefetch_count=1)

channel.basic_consume(callback,
                      queue='task_queue')
channel.start_consuming()
connection.close()