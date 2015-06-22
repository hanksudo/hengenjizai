# -*- coding: utf-8 -*-
class propertycache(object):
    def __init__(self, func):
        print 'init'
        self.func = func
        self.name = func.__name__

    def __get__(self, obj, type=None):
        print 'get', obj
        result = self.func(obj)
        self.cachevalue(obj, result)
        return result

    def cachevalue(self, obj, value):
        print 'cacheValue'
        setattr(obj, self.name, value)


class foo(object):
    @propertycache
    def expensive(self):
        print 'created'
        return [1, 2, 3]

f = foo()
print 'start==='
print f.expensive  # prints created
print 'second==='
print f.expensive  # simply returns [1, 2, 3] 
# to forget the saved value and calculate it again, use delattr
delattr(f, 'expensive')
