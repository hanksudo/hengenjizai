# method 1
x = [2, 3, 4, 5, 6]
y = []
for v in x:
    if v % 2:
        y += [v * 5]

assert x == [2, 3, 4, 5, 6]
assert y == [15, 25]

# method2
y = [v * 5 for v in x if v % 2]
assert y == [15, 25]

# lambda way
y = map(lambda v: v * 5, filter(lambda u: u % 2, x))
assert y == [15, 25]
