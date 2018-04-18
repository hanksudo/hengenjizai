# 03 - What Make a Good Feature? - https://youtu.be/N9fDIAflCMY

import numpy as np
import matplotlib.pyplot as plt

greyhounds = 500
labs = 500

grey_height = 28 + 4 * np.random.randn(greyhounds)
lab_height = 24 + 4 * np.random.randn(labs)

# Greyhounds - red, labradors - blue
plt.hist([grey_height, lab_height], stacked=True, color=["r", "b"])
plt.show()