from zope.interface import implements

from twisted.python import usage
from twisted.plugin import IPlugin
from twisted.web import server, resource
from twisted.application.service import IServiceMaker
from twisted.application import internet


class Simple(resource.Resource):
    isLeaf = True

    def render_GET(self, request):
        return "<html>Hello, world!</html>"


class Options(usage.Options):
    optParameters = [["port", "p", 1235, "The port number to listen on."]]


class MyServiceMaker(object):
    implements(IServiceMaker, IPlugin)
    tapname = "myproject"
    description = "Run this! It'll make your dog happy."
    options = Options

    def makeService(self, options):
        """
        Construct a TCPServer from a factory defined in myproject.
        """
        return internet.TCPServer(int(options["port"]), server.Site(Simple()))

serviceMaker = MyServiceMaker()
