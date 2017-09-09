from twisted.web.server import Site
from twisted.web.resource import Resource
from twisted.internet import reactor


class PaymentRequired(Resource):
    def render_GET(self, request):
        request.setResponseCode(402)
        return "Please swipe your credit card."

root = Resource()
root.putChild("buy", PaymentRequired())
factory = Site(root)
reactor.listenTCP(8880, factory)
reactor.run()
