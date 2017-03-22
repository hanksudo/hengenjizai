from twisted.internet import reactor
from twisted.web.server import Site
from twisted.web.resource import Resource
import time

class ClockPage(Resource):
    isLeaf = True
    def render_GET(self, request):
        return "%s" % (time.ctime(),)

resource = ClockPage()
factory = Site(resource)
reactor.listenTCP(8880, factory)
reactor.run()