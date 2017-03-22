import numpy as np
import matplotlib.pyplot as plt

x = np.linspace(0, 1)
y = np.sin(5 * np.pi * x) * np.exp(-5 * x)

plt.fill(x, y, "c")
plt.grid(True)
plt.show()
