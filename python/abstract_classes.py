from abc import ABCMeta, abstractmethod
import inspect

class GF(object):
    __metaclass__ = ABCMeta

    @abstractmethod
    def name(self):
        raise NotImplemented


class F(GF):
    def __init__(self):
        pass

    def name(self):
        return "Hank"

    @abstractmethod
    def age(self):
        raise NotImplemented


class C(F):
    def __init__(self):
        pass

    def name(self):
        return "hey"

    def age(self):
        return 30


def main():
    # Can't instantiate abstract class
    try:
        F()
    except Exception as e:
        print(e)

    # is Abstract
    print(inspect.isabstract(GF))
    print(inspect.isabstract(F))
    print(inspect.isabstract(C))

    # subclasses
    print(GF.__subclasses__())
    print(F.__subclasses__())

    # get all concrete sub-classes
    print(get_all_subclasses(GF))


def get_all_subclasses(cls):
    all_subclasses = []

    for subclass in cls.__subclasses__():
        if not inspect.isabstract(subclass):
            all_subclasses.append(subclass)
        all_subclasses.extend(get_all_subclasses(subclass))

    return all_subclasses


if __name__ == '__main__':
    main()
