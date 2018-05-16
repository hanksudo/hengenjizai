import abc
import six
import inspect

@six.add_metaclass(abc.ABCMeta)
class GF(object):

    @abc.abstractmethod
    def name(self):
        raise NotImplemented


class F(GF):
    def __init__(self):
        pass

    def name(self):
        return "Hank"

    @abc.abstractmethod
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
    print("{} is abstract class: {}".format(GF.__name__, inspect.isabstract(GF)))
    print("{} is abstract class: {}".format(F.__name__, inspect.isabstract(F)))
    print("{} is abstract class: {}".format(C.__name__, inspect.isabstract(C)))

    # subclasses
    print("GF sub classes: {}".format(GF.__subclasses__()))
    print("F sub classes: {}".format(F.__subclasses__()))

    # get all concrete sub-classes
    print("concrete sub-classes: {}".format(get_all_subclasses(GF)))


def get_all_subclasses(cls):
    all_subclasses = []

    for subclass in cls.__subclasses__():
        if not inspect.isabstract(subclass):
            all_subclasses.append(subclass)
        all_subclasses.extend(get_all_subclasses(subclass))

    return all_subclasses


if __name__ == '__main__':
    main()
