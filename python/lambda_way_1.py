# method 1
x = [2, 3, 4, 5, 6]
y = []

for v in x:
    y += [v*5]

assert x == [2, 3, 4, 5, 6]
assert y == [10, 15, 20, 25, 30]

# method 2
y = [v * 5 for v in x]
assert y == [10, 15, 20, 25, 30]

# labmda way
y = map(lambda v: v * 5, x)
assert y == [10, 15, 20, 25, 30]
