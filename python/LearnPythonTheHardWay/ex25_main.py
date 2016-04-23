#!/usr/bin/env python
# -*- coding: utf-8 -*-
"""
Exercise 25 : Even More Practice (Main Program)

@author Hank Wang <drapho@gmail.com>
@version 20160423
"""
import ex25


sentence = "All good things come to those who wait."
words = ex25.break_words(sentence)
print words

sorted_words = ex25.sort_words(words)
print sorted_words

ex25.print_first_word(words)
ex25.print_last_word(sorted_words)
print sorted_words

sorted_words = ex25.sort_sentence(sentence)
print sorted_words
ex25.print_first_and_last(sentence)
ex25.print_first_and_last_sorted(sentence)
