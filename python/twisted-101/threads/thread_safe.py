from twisted.internet import reactor


def notThreadSafe(x):
    """ do something this isn't thread-safe """
    print 'notThreadSafe, %d' % x


def threadSafeScheduler():
    """ Run in thread-safe manner """
    print 'safe'
    reactor.callFromThread(notThreadSafe, 3)  # notThreadSafe(3)

reactor.run()
