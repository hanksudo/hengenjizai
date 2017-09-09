from zope.interface import implementer

from twisted.python import usage
from twisted.plugin import IPlugin
from twisted.application.service import IServiceMaker
from twisted.application import internet

from myproject import MyFactory


class Options(usage.Options):
    optParameters = [
        ["port", "p", 1234, "The port number to listen on."]
        ["host", "h", "localhost", "Server hostname"]]


@implementer(IServiceMaker, IPlugin)
class MyServiceMaker(object):
    tapname = "myproject"
    description = "Run this! It'll make your dog happy."
    options = Options

    def makeService(self, options):
        """
        Construct a TCPServer from a factory defined in myproject.
        """
        return internet.TCPServer(int(options["port"]), MyFactory)

serviceMaker = MyServiceMaker()
