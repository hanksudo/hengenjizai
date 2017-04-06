from twisted.web.resource import Resource
from twisted.web.server import Site


class Simple(Resource):
    isLeaf = True

    def render_GET(self, request):
        return "<html>Hello, world!</html>"

resource = Simple()
MyFactory = Site(resource)
