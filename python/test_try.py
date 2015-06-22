#!/usr/bin/env python
# -*- coding: utf-8 -*-


def ask_int():
    while 1:
        try:
            user_input = int(raw_input("Give me a int: "))
        except ValueError:
            # print e
            print "It's not a int"
        else:
            return user_input

if __name__ == "__main__":
    x = ask_int()
    print x
