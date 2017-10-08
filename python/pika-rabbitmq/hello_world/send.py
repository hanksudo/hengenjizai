#!/usb/bin/env python
import pika

connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
channel = connection.channel()

channel.queue_declare(queue='hello')

for i in range(5):
    message = 'Hello World! %d' % (i + 1)
    channel.basic_publish(exchange='',
                          routing_key='hello',
                          body=message)
    print(" [x] Sent {}".format(message))
connection.close()
