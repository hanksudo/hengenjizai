from twisted.internet import reactor


def aSillyBlockingMethod(x):
    import time
    time.sleep(2)
    print x

print 'Reactor is running...'

# run method in thread
reactor.callInThread(aSillyBlockingMethod, "2 seconds have passed")
reactor.run()
