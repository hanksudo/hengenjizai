#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Exercise 16 : Reading and Writing Files

@author Hank Wang <drapho@gmail.com>
@version 20140710
"""

from sys import argv

script, filename = argv

print "We're going to erase %r." % filename
print "If you don't want that, hit CTRL-C (^C)."
print "If you do want that, hit RETURN."

raw_input("?")

print "Opening the file..."
target = open(filename, 'w')  # w - already truncate, a - append content

print "Truncating the file. Goodbye!"
target.truncate()

print "Now I'm going to ask you for three lines."

line1 = raw_input("line 1: ")
line2 = raw_input("line 2: ")
line3 = raw_input("line 3: ")

target.write("%s\n%s\n%s\n" % (line1, line2, line3))

print "Add finally, we close it."
target.close()
