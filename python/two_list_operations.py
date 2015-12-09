# https://docs.python.org/2/library/sets.html#set-objects

a = [1, 2, 3, 4, 5, 6]
b = [2, 3, 6, 8, 4]

s = set(a)
t = set(b)

print list(s - t)
print list(s & t)
print list(s ^ t)
print list(s | t)
print s <= t
print s >= t
