from twisted.web.resource import Resource
from twisted.web.server import Site
from twisted.internet import reactor


class Hello(Resource):
    def getChild(self, name, request):
        print 'getChild', name
        if name == '':
            return self
        return Resource.getChild(self, name, request)

    # def getChildWithDefault(self, firstSegment, request):
    #     print 'getChildWithDefault', firstSegment

    def render_GET(self, request):
        return 'index'


class PaymentRequired(Resource):
    def render_GET(self, request):
        print 'pre', request.prepath
        print 'post', request.postpath
        return "Hello, world! I am located at %r." % (request.prepath,)

root = Hello()
root.putChild("buy", PaymentRequired())
factory = Site(root)
reactor.listenTCP(8080, factory)
reactor.run()
