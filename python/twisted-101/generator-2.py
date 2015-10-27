def my_generator():
    print 'starting up'
    yield 1
    print "working"
    yield 2
    print "still working"
    yield 3
    print 'done'

gen = my_generator()


while True:
    try:
        n = gen.next()
    except StopIteration:
        break
    else:
        print n
