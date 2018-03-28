# coding: utf-8
import numpy as np
a = np.array([1,2,3])
a+a
2*a
a**2
a = np.array([1,2])
b = np.array([2,1])
a
b
dot = 0
for e, f in zip(a,b):
    dot += e*f
    
dot
zip(a,b)
a*b
np.sum(a*b)
(a*b).sum()
