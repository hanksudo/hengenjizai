#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Exercise 10 : What Was That?

@author Hank Wang <drapho@gmail.com>
@version 20140316
"""

tabby_cat = "\tI'm tabbed in."
persian_cat = "I'm split\non a line."
backslash_cat = "I'm \\ a \\ cat."

fat_cat = """
I'll do a list:
\t* Cat food
\t* Fishies
\t* Catnip\n\t* Grass
"""

print tabby_cat
print persian_cat
print backslash_cat
print fat_cat


for i in ["/", "-", "|", "\\", "|"]:
    print "%s\r" % i,
