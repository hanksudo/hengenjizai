from twisted.internet import defer

def cbPrintResult(result):
    print(result)

def main():
    d1 = defer.Deferred()
    d2 = defer.Deferred()

    d = defer.gatherResults([d1, d2], consumeErrors=True)
    d.addCallback(cbPrintResult)

    d1.callback("one")
    d2.callback("two")

if __name__ == '__main__':
    main()
