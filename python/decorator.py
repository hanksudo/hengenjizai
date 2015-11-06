
def extractData(handler):
    def extracted(request, *args, **kwargs):
        # print "extractData args=", args
        return handler(request, 'this is data', *args, **kwargs)

    return extracted


def doAuth(handler):
    def authed(request, *args, **kwargs):
        # print "doAuth args=", args
        return handler(request, "authenticated", *args, **kwargs)
    return authed


def audit(handler):
    def audited(request, *args, **kwargs):
        # print "audit args=", args
        return handler(request, *args, **kwargs)
    return audited


def api(*method, **kwargs):
    def decorate(handler):
        return extractData(doAuth(audit(handler)))
    return decorate


def api2(*method, **kwargs):
    def decorate(handler):
        decorators = [
            audit,
            doAuth,
            extractData
        ]
        wrapped = handler
        for decorator in decorators:
            wrapped = decorator(wrapped)
        return wrapped
    return decorate


@api("POST")
def render_this(req, token, data):
    print req
    print token
    print data


@api2("POST")
def render_that(req, token, data):
    print req
    print token
    print data

render_this("this is req")
print "\n"
render_that("this is req2")
