from twisted.internet import defer

def printResult(result):
    for (success, value) in result:
        if success:
            print("Success:", value)
        else:
            print("Failure:", value.getErrorMessage())

def main():
    d1 = defer.Deferred()
    d2 = defer.Deferred()
    d3 = defer.Deferred()

    dl = defer.DeferredList([d1, d2, d3])
    dl.addCallback(printResult)

    d1.callback("one")
    d2.errback(Exception("bang!"))
    d3.callback("three")

if __name__ == '__main__':
    main()
