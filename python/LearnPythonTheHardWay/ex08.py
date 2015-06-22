#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Exercise 8 : Printing, Printing

@author Hank Wang <drapho@gmail.com>
@version 20140316
"""

formatter = "%r %r %r %r"

print formatter % (1, 2, 3, 4)
print formatter % ("one", "two", "three", "four")
print formatter % (True, False, False, True)
print formatter % (formatter, formatter, formatter, formatter)
print formatter % (
    "I had this thing.",
    "That you could type up right.",
    "But it didn't sing.",
    "So I said goodnight."
)
print formatter % ("試", "試", "中", "文")
