#!/usr/bin/env python
# -*- coding: UTF-8 -*-

"""
Threading Example

@author: Hank Wang <drapho@gmail.com>
@version: 20101230
"""
import time
from threading import Thread

def myfunc(i):
    print("sleeping 10 sec from thread %d" % i)
    time.sleep(10)
    print("finished sleeping from thread %d" % i)

def main():
    for i in range(10):
        t = Thread(target=myfunc, args=(i,))
        time.sleep(1)
        t.start()

if __name__ == '__main__':
    main()
