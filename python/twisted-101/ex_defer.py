from twisted.internet import defer, reactor
from twisted.web.client import getPage


def listCallback(results):
    for isSuccess, content in results:
        print "Successful? %s" % isSuccess
        print "Content Length: %s" % len(content)


def main():
    d1 = getPage('http://www.google.com')
    d2 = getPage('http://yahoo.com')
    dl = defer.DeferredList([d1, d2])
    dl.addCallback(listCallback)
    dl.addCallback(lambda x: reactor.stop())


main()
reactor.run()
