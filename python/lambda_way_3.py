# method 1
x = [2, 3, 4]
y = [4, 5]
z = []

for i in x:
    for j in y:
        z += [i + j]

assert x == [2, 3, 4]
assert y == [4, 5]
assert z == [2+4, 2+5, 3+4, 3+5, 4+4, 4+5]
assert z == [6, 7, 7, 8, 8, 9]

# method 2
z = [i + j for i in x for j in y]
assert z == [6, 7, 7, 8, 8, 9]

# lambda way
t = map(lambda i: map(lambda j: i + j, y), x)
z = sum(t, [])
assert z == [6, 7, 7, 8, 8, 9]
