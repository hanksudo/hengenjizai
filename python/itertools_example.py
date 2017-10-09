#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
# https://docs.python.org/3.6/library/itertools.html#module-itertools
# https://medium.com/@mr_rigden/a-guide-to-python-itertools-82e5a306cdf8
import itertools
import operator

# ----------
# accumulate
data = [1, 2, 3, 4, 5]
result = itertools.accumulate(data, operator.mul)
for each in result:
    print(each)


data = [5, 2, 6, 4, 5, 9, 1]
result = itertools.accumulate(data, max)
for each in result:
    print(each)

# if function not pass, default will be summed.
data = [5, 2, 6, 4, 5, 9, 1]
result = itertools.accumulate(data)
for each in result:
    print(each)

# ----------------------------------------------
# combinations and combinations_with_replacement
shapes = ['circle', 'triangle', 'square']
result = itertools.combinations(shapes, 2)
for each in result:
    print(each)

result = itertools.combinations(shapes, 3)
for each in result:
    print(each)

result = itertools.combinations_with_replacement(shapes, 2)
for each in result:
    print(each)

# -----
# count
for i in itertools.count(start=10, step=3):
    print(i)
    if i > 20:
        break

# -----
# cycle
colors = ['red', 'orange', 'yellow', 'green', 'blue', 'violet']
for color in itertools.cycle(colors):
    print(color)
    break  # if no break will be endlessly

# -----
# chain
colors = ['red', 'orange', 'yellow', 'green', 'blue']
shapes = ['circle', 'triangle', 'square', 'pentagon']

result = itertools.chain(colors, shapes)
for each in result:
    print(each)

# --------
# compress
shapes = ['circle', 'triangle', 'square', 'pentagon']
selections = [True, False, True, False]

result = itertools.compress(shapes, selections)
for each in result:
    print(each)
# ---------
# dropwhile
data = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1]
result = itertools.dropwhile(lambda x: x<5, data)
for each in result:
    print(each)

# -----------
# filterfalse
result = itertools.filterfalse(lambda x: x<5, data)
for each in result:
    print(each)

# -------
# groupby
robots = [{
    'name': 'blaster',
    'faction': 'autobot'
}, {
    'name': 'galvatron',
    'faction': 'decepticon'
}, {
    'name': 'jazz',
    'faction': 'autobot'
}, {
    'name': 'metroplex',
    'faction': 'autobot'
}, {
    'name': 'megatron',
    'faction': 'decepticon'
}, {
    'name': 'starcream',
    'faction': 'decepticon'
}]
robots = sorted(robots, key=lambda x: x['faction'])
for key, group in itertools.groupby(robots, key=operator.itemgetter('faction')):
    print(key)
    print(list(group))

# ------
# islice
colors = ['red', 'orange', 'yellow', 'green', 'blue']
few_colors = itertools.islice(colors, 2)
for each in few_colors:
    print(each)

# ------------
# permutations
suits = ['♠', '♥', '♦', '♣']
ranks = ['A', 'K', 'Q', 'J'] + list(map(str, range(1, 11)))

result = itertools.product(suits, ranks)
for each in result:
    print(str(each))

# ------
# repeat
for i in itertools.repeat("spam", 5):
    print(i)

# -------
# starmap
data = [(2, 6), (8, 4), (7, 3)]
result = itertools.starmap(operator.mul, data)
for each in result:
    print(each)

# ---------
# takewhile
data = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1]
result = itertools.takewhile(lambda x: x<5, data)
for each in result:
    print(each)

# ---
# tee
colors = ['red', 'orange', 'yellow', 'green', 'blue']
alpha_colors, beta_colors = itertools.tee(colors, 2)
for each in alpha_colors:
    print(each)
print('..')
for each in beta_colors:
     print(each)

# ------
# zip_longest
colors = ['red', 'orange', 'yellow', 'green', 'blue',]
data = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10,]
for each in itertools.zip_longest(colors, data, fillvalue=None):
    print(each)
