from twisted.web.server import Site
from twisted.web.resource import Resource
from twisted.internet import reactor
from twisted.web.static import File

root = Resource()
root.putChild("foo", File("/tmp"))
root.putChild("baz", File("/opt"))

factory = Site(root)
reactor.listenTCP(8880, factory)
reactor.run()
