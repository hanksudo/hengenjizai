from twisted.web.resource import Resource
from twisted.web.server import Site
from twisted.internet import reactor


class ShowSession(Resource):
    def getChild(self, name, request):
        print name
        if name == '':
            return self
        return Resource.getChild(self, name, request)

    def render_GET(self, request):
        return 'Your session id is: ' + request.getSession().uid


class ExpireSession(Resource):
    def render_GET(self, request):
        request.getSession().expire()
        return 'Your session has been expired.'


# class CounterSession(Resource):
    # def render_GET(self, )


resource = ShowSession()
resource.putChild("expire", ExpireSession())
factory = Site(resource)
reactor.listenTCP(8080, factory)
reactor.run()
