from twisted.web.server import Site
from twisted.web.static import File
from twisted.internet import reactor

resource = File("/tmp")
factory = Site(resource)
reactor.listenTCP(8888, factory)
reactor.run()