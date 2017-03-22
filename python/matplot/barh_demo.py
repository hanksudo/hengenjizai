import matplotlib.pyplot as plt
import numpy as np
import names

amount = 5
people = (names.get_first_name() for n in range(amount))
y_pos = np.arange(amount)
performance = 20 * np.random.rand(amount)
error = np.random.rand(amount)

plt.barh(y_pos, performance, xerr=error, align="center", alpha=0.4)
plt.yticks(y_pos, people)
plt.xlabel("Age")
plt.title("Simple demo of a horizontal bar chart.")

plt.show()
