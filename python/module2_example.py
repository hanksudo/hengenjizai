class Test():
    _a = 1
    __a = 2

    def __init__(self):
        self.FILES = []

    @property
    def a(self):
        return self._a

    def abc(self):
        self._files.append(1)
        pass

print Test._a
print Test._Test__a

t = Test()
print t._a
print t._Test__a
t._a = 3
print t._a
t._Test__a = 3
print t._Test__a

print t.a
t.a = 4
print t.a
