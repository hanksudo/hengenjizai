from twisted.web.resource import Resource
from twisted.web import server
from twisted.internet import reactor
from twisted.python.util import println


class ExampleResource(Resource):

    def render_GET(self, request):
        request.write("hello")
        d = request.notifyFinish()
        d.addCallback(lambda _: println('finished normally'))
        d.addErrback(println, "error")
        reactor.callLater(10, request.finish)
        return server.NOT_DONE_YET

resource = ExampleResource()
