#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Exercise 18 : Names, Variables, Code, Functions

@author Hank Wang <drapho@gmail.com>
@version 20140710
"""


def print_two(*args):
    arg1, arg2 = args
    print "arg1: %r, arg2: %r" % (arg1, arg2)


def print_two_again(*args):
    arg1, arg2 = args
    print "arg1: %r, arg2: %r" % (arg1, arg2)


def print_one(arg1):
    print "arg1: %r" % arg1


def print_none():
    print "I got nothin'."


print_two("Yo", "Man")
print_two_again("Yo", "Man")
print_one("One!")
print_none()
