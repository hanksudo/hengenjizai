def my_generator():
    print 'starting up'
    yield 1
    print "working"
    yield 2
    print "still working"
    yield 3
    print 'done'

for n in my_generator():
    print n
