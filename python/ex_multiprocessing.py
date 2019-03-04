import multiprocessing
ITERATIONS = 1000000


class MyClass(multiprocessing.Process):

    def __init__(self, num):
        self.num = num
        super(MyClass, self).__init__()

    def run(self):
        for i in range(ITERATIONS):
            pass
        print(self.num)


def main():
    for j in range(10):
        t = MyClass(j)
        t.start()

if __name__ == '__main__':
    main()
