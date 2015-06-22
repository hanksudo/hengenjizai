from twisted.internet import protocol, reactor, endpoints


class Echo(protocol.Protocol):
    def dataReceived(self, data):
        self.transport.write(data)
        self.transport.write("An Apple a day keeps the doctor away\r\n")
        self.transport.loseConnection()


class EchoFactory(protocol.Factory):
    def buildProtocol(self, addr):
        print addr
        return Echo()

endpoints.serverFromString(reactor, "tcp:1234").listen(EchoFactory())
reactor.run()
