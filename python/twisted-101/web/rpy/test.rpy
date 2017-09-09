from twisted.web.resource import Resource


class MyResource(Resource):
    def render_GET(self, request):
        return "<html>Hello, world!</html>"

resource = MyResource()
