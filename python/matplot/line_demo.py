import matplotlib.pyplot as plt
import numpy as np

year = np.arange(1950, 2020, 5)
pop = [
    2525, 2758, 3018, 3322, 3682, 4061, 4440, 4853, 5310, 5735,
    6127, 6520, 6930, 7349
]

plt.plot(year, pop)
plt.xlabel("year")
plt.ylabel("population")
plt.title("World population")
plt.show()
