"""
Support module for making a port forwarder with twistd.
"""
from twisted.python import usage
from twisted.application import internet

from myproject import MyFactory


class Options(usage.Options):
    synopsis = "[options]"
    longdesc = "My service."
    optParameters = [
        ["port", "p", 1234, "The port number to listen on."]
        ["host", "h", "localhost", "Server hostname"]]


def makeService(config):
    return internet.TCPServer(int(config["port"]), MyFactory)
