# -*- coding: utf-8 -*-


class MyIter(object):

    def __init__(self):
        self.list = [1, 2, 3, 4, 5]

    def read(self):
        for ele in xrange(len(self.list)):
            yield ele

    def __iter__(self):
        return self.read()

    def __str__(self):
        return ",".join(map(str, self.list))

    __repr__ = __str__


if __name__ == '__main__':
    obj = MyIter()
    for num in obj:
        print num
    print obj
