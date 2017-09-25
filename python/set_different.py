d1 = {"a": "a_v", "b": "b_v"}
d2 = {"b": "b_v", "c": "c_v"}

print d1, d2
print set(d1).difference(d2)
print set(d1) - set(d2)
print set(d1).symmetric_difference(d2)
print set(d1) ^ set(d2)