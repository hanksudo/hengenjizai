#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Exercise 5 : More Variables and Printing
http://docs.python.org/2/library/stdtypes.html#string-formatting

@author Hank Wang <drapho@gmail.com>
@version 20140316
"""

name = 'Hank Wang'
age = 29
height = 169.5  # centimetre
height_in_inches = round(height * 0.393700787)
weight = 55.0  # kilo gram
weight_in_pounds = round(weight * 2.20462262)
eyes = 'Dark Brown'
teeth = 'White'
hair = 'Black'

print "Let's talk about %s." % name
print "He's %.1f cm tall, it's %d inches." % (height, height_in_inches)
print "He's %.1f kg heavy, it's %d pounds." % (weight, weight_in_pounds)
print "Actually that's not too heavy."
print "He's got %s eyes and %s hair" % (eyes, hair)
print "His teeth are usually %s depending on the coffee." % teeth
print "If I add %.1f, %.1f, and %d I get %d." % (age, height, weight, age + height + weight)
