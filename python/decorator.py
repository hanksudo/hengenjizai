
def extractData(handler):
    def extracted(request, *args, **kwargs):
        print "extractData args=", args
        return handler(request, 'this is data', *args, **kwargs)

    return extracted


def doAuth(handler):
    def authed(request, *args, **kwargs):
        print "doAuth args=", args
        return handler(request, 123, *args, **kwargs)
    return authed


def audit(handler):
    def audited(request, *args, **kwargs):
        print "audit args=", args
        return handler(request, *args, **kwargs)
    return audited


def api(*method, **kwargs):
    def decorate(handler):
        return extractData(doAuth(audit(handler)))
    return decorate


@api("POST")
def render_this(req, token, data):
    print req
    print token
    print data

render_this("this is req")
