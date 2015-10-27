import traceback
from twisted.internet import defer, reactor


@defer.inlineCallbacks
def my_callbacks():
    yield 1
    traceback.print_stack()
    reactor.stop()

reactor.callWhenRunning(my_callbacks)
reactor.run()
