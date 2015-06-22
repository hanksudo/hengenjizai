from twisted.web.server import Site
from twisted.web.resource import Resource
from twisted.internet import reactor


class YearPage(Resource):
    def __init__(self, year):
        Resource.__init__(self)
        self.year = year

    def render_GET(self, request):
        return "%d" % self.year


class Root(Resource):
    def getChild(self, name, request):
        return YearPage(int(name))

root = Root()
factory = Site(root)
reactor.listenTCP(8880, factory)
reactor.run()
