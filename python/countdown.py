from datetime import datetime
import time

while True:
    print (datetime(2016, 2, 15, 22, 00, 00) - datetime.now()).total_seconds() / 3600
    time.sleep(1)
