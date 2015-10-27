from twisted.internet import reactor, defer


@defer.inlineCallbacks
def my_callbacks():
    print 'first callback'
    result = yield 1

    print 'second callback got', result
    d = defer.Deferred()
    reactor.callLater(5, d.callback, 2)
    result = yield d

    print 'third callback got', result

    d = defer.Deferred()
    reactor.callLater(5, d.errback, Exception(3))

    try:
        yield d
    except Exception, e:
        result = e

    print 'fourth callbackk got', repr(result)

    reactor.stop()

reactor.callWhenRunning(my_callbacks)
reactor.run()
