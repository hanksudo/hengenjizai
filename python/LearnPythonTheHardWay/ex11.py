#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Exercise 11 : Asking Questions

@author Hank Wang <drapho@gmail.com>
@version 20140316
"""

print "How old are you?",
age = raw_input()
print "How tall are you?",
height = raw_input()
print "How much do you weight?",
weight = raw_input()

print "So, you're %r old, %r tall and %r heavy." % (age, height, weight)
