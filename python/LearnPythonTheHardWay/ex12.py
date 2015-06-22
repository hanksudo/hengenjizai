#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Exercise 12 : Prompting People

@author Hank Wang <drapho@gmail.com>
@version 20140321
"""

age = raw_input("How old are you? ")
height = raw_input("How tall are you? ")
weight = raw_input("How much do you weight? ")

print "So, you're %r old, %r tall and %r heavy." % (age, height, weight)
