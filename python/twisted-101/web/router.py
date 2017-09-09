import json
from twisted.web.resource import Resource, NoResource
from twisted.web.server import Site
from twisted.internet import reactor


def get_echo(request):
    request.setHeader("Content-Type", "application/json")
    return json.dumps({
        "message": "ok",
        "data": "this is echo from get."
    }, indent=2)


def post_echo(request):
    request.setHeader("Content-Type", "application/json")
    return json.dumps({
        "message": "ok",
        "data": "this is echo from post."
    }, indent=2)


routers = {}


def url(uri, handler, methods=["POST", "GET", "PUT", "DELETE"]):
    if uri[0] != "/":
        uri = "/" + uri

    dispatcher = routers.get(uri)
    if dispatcher is None:
        dispatcher = {
            "POST": None,
            "GET": None,
            "PUT": None,
            "DELETE": None
        }

    for method in methods:
        dispatcher[method] = handler

    routers[uri] = dispatcher


class Router(object):
    @staticmethod
    def get(uri, handler):
        url(uri, handler, ["GET"])

    @staticmethod
    def post(uri, handler):
        url(uri, handler, ["POST"])

    @staticmethod
    def put(uri, handler):
        url(uri, handler, ["put"])

    @staticmethod
    def delete(uri, handler):
        url(uri, handler, ["delete"])

    @staticmethod
    def all(uri, handler):
        url(uri, handler)

url("/echo", get_echo, ["GET"])
url("api/echo", get_echo, ["GET"])
url("api/echo", post_echo, ["POST"])


class Root(Resource):

    def getChild(self, name, request):
        print "{} getChild {}".format(self.__class__, name)
        if name == "":
            return self
        return Resource.getChild(self, name, request)

    def render_GET(self, request):
        return "Index"

    def getChildWithDefault(self, firstSegment, request):
        dispatcher = routers.get(request.uri)
        if dispatcher:
            handler = dispatcher[request.method]
            if handler:
                return ChildResource(handler)

        return NoResource()


class ChildResource(Resource):
    isLeaf = True

    def __init__(self, handler):
        print handler
        self.handler = handler

    def getChildWithDefault(self, segment, request):
        # self.response = self.handler(request)
        print segment
        return self

    def render(self, request):
        self.response = self.handler(request)
        return self.response


class PaymentRequired(Resource):
    # isLeaf = False

    def render_HEAD(self, request):
        request.setResponseCode(404)
        return ""

    def render_GET(self, request):
        print "pre", request.prepath, "post", request.postpath, "method", request.method
        return "Hello, world! I am located at %r." % (request.prepath,)

    def render_POST(self, request):
        return "Got your money!"

root = Root()  # /
root.putChild("buy", PaymentRequired())  # /buy
factory = Site(root)
reactor.listenTCP(8080, factory)
reactor.run()
