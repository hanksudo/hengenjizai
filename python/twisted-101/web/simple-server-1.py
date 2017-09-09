from twisted.web.resource import Resource
from twisted.web.server import Site
from twisted.internet import reactor, endpoints


class Hello(Resource):
    def getChild(self, name, request):
        if name == '':
            return self
        return Resource.getChild(self, name, request)

    def render_GET(self, request):
        return "Hello, world! I am located at %r." % (request.prepath,)

resource = Hello()
resource.putChild("fred", Hello())
resource.putChild("bob", Hello())

site = Site(resource)
endpoint = endpoints.TCP4ServerEndpoint(reactor, 8080)
endpoint.listen(site)
reactor.run()
