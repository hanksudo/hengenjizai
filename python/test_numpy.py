import numpy
import math
import random


def mean(dataSet, digits=0):
    """ Average """
    result = round(numpy.mean(dataSet), digits)

    if math.isnan(result):
        return 0
    else:
        return result


def std(dataSet, digits=0, ddof=1):
    """ We use "sample standard divation" (n-1) """
    result = round(numpy.std(dataSet, ddof=ddof), digits)

    if math.isnan(result):
        return 0
    else:
        return result

def start():
    numbers = random.choice(range(5, 50))
    nums = list(random.choice(range(100, 600)) for i in range(numbers))

    if std(nums) < mean(nums) / 3:
        print nums

if __name__ == "__main__":
    times = 700
    for i in range(times):
        start()
