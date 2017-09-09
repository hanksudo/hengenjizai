from twisted.web import server, resource
from twisted.internet import reactor, endpoints


class Simple(resource.Resource):
    isLeaft = True

    def render_GET(self, request):
        return "<html>Hello Twisted!</html>"

site = server.Site(Simple())
endpoint = endpoints.TCP4ServerEndpoint(reactor, 8080)
endpoint.listen(site)
reactor.run()
