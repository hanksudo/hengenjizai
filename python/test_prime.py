#!/usr/bin/env python
# -*- coding: utf-8 -*-

for n in range(2, 100):
    for i in range(2, n):
        if n % i == 0:
            break
    else:
        print '{} is a prime!'.format(n)
