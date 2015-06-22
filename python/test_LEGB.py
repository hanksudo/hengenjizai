#!/usr/bin/env python
# -*- coding: utf-8 -*-

global_var = 100


def f():
    enclosed_var = 10

    def g():
        local_var = 1
        return sum([local_var, enclosed_var, global_var])

    return g()

if __name__ == '__main__':
    print f()
