from twisted.web.xmlrpc import Proxy
from twisted.internet import reactor


def printValue(value):
    print repr(value)
    reactor.stop()


def printError(error):
    print 'error', error
    reactor.stop()

proxy = Proxy('http://advogato.org/XMLRPC')
proxy.callRemote('test.sumprod', 3, 5).addCallbacks(printValue, printError)
reactor.run()
