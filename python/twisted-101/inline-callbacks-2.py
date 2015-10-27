from twisted.internet import reactor, defer


@defer.inlineCallbacks
def my_callbacks_1():
    print 'my_callbacks_1 first callback'
    d = defer.Deferred()
    reactor.callLater(2, d.callback, None)
    yield d

    print 'my_callbacks_1 second callback'
    d = defer.Deferred()
    reactor.callLater(2, d.callback, None)
    yield d

    print 'my_callbacks_1 third callback'
    defer.returnValue(1)


@defer.inlineCallbacks
def my_callbacks_err():
    print 'my_callbacks_err first callback'
    d = defer.Deferred()
    reactor.callLater(3, d.callback, None)
    yield d

    print 'my_callbacks_err second callback'
    d = defer.Deferred()
    reactor.callLater(3, d.callback, None)
    yield d

    print 'my_callbacks_1 third callback'
    raise Exception('oh')


def got_result(res):
    print 'got result:', res


def got_error(err):
    print 'got error:', err


def run_callbacks():
    d1 = my_callbacks_1()
    d1.addCallbacks(got_result, got_error)

    d2 = my_callbacks_err()
    d2.addCallbacks(got_result, got_error)

    def callbacks_finished(_):
        callbacks_finished.count += 1
        if callbacks_finished.count == 2:
            print 'all done'
            reactor.stop()
    callbacks_finished.count = 0

    d1.addBoth(callbacks_finished)
    d2.addBoth(callbacks_finished)


reactor.callWhenRunning(run_callbacks)
reactor.run()
