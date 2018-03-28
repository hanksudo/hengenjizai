"""
Vector - find component form
"""
import numpy as np


def solve(_v, _w):
    _vx, _vy = _v
    _wx, _wy = _w
    print (
        round(_vx * np.cos(np.deg2rad(_vy)) + _wx * np.cos(np.deg2rad(_wy)), 2),
        round(_vx * np.sin(np.deg2rad(_vy)) + _wx * np.sin(np.deg2rad(_wy)), 2)
    )

solve((7, 160), (9, 20))
solve((5, 170), (6, 260))
solve((7, 200), (5, 70))
