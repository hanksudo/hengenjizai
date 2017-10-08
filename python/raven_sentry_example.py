from raven import Client

url = '**dsn**'

client = Client(url)

try:
    1 / 0
except ZeroDivisionError:
    client.captureException()
