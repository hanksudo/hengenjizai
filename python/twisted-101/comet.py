import urlparse
import logging

from twisted.web import server, resource, xmlrpc
from twisted.internet import reactor

log = logging.getLogger(__name__)


class Observer(object):
    def __init__(self, request):
        self.request = request

    def notify(self, data):
        """Notify observer with data and finish the request

        @param data: data to send to peer
        """
        # XXX
        # exceptions.RuntimeError: Request.finish called on a request after its
        # connection was lost; use Request.notifyFinish to keep track of this.
        if not self.request._disconnected:
            self.request.write(data)
            self.request.finish()


class Subject(resource.Resource):
    """Subject hold observers that wait for event

    @ivar observers: list of clients
    """
    isLeaf = True

    def __init__(self, name, timeout, callback, timeoutResponse=None):
        """

        @param name: name of subject
        @param timeout: timeout of this channel in seconds
        @param callback: callback is called when this subject finished
        """
        resource.Resource.__init__(self)
        self.name = name
        self.timeout = timeout
        self.callback = callback
        self.observers = []
        self.timeoutResponse = timeoutResponse
        self.callId = reactor.callLater(self.timeout, self.onTimeout)

    def addObserver(self, request):
        """Add an observer

        """
        log.info('Subject "%s" add observer from %s',
                 self.name, request.client.host)
        observer = Observer(request)
        self.observers.append(observer)

    def onTimeout(self):
        """Called to handle timeout event

        """
        log.info('Subject %s timeout with %d observers',
                 self.name, len(self.observers))
        for observer in self.observers:
            data = ''
            if self.timeoutResponse:
                data = self.timeoutResponse
            observer.notify(data)
        self.callback(self)

    def notify(self, data):
        """Notify all observers with data

        @param data: data to send to clients
        """
        log.info('Subject %s notified %d observers with data "%s"', self.name, len(self.observers), data)
        self.callId.cancel()
        for observer in self.observers:
            observer.notify(data)
        self.callback(self)

    def render_GET(self, request):
        self.addObserver(request)
        return server.NOT_DONE_YET


class Channel(resource.Resource):
    """Channel is a folder of subjects

    """

    def __init__(self, url):
        """

        @param url: base url of channel
        """
        resource.Resource.__init__(self)
        self.url = url

    def _cbFinish(self, subject):
        """Called when subject finish"""
        log.info('Subject %s finish', subject.name)
        self.delEntity(subject.name)

    def _getSubjectURL(self, subject):
        return urlparse.urljoin(self.url, subject.name)

    def createSubject(self, name, timeout, timeoutResponse):
        """Create a subject and return, if the subject already exist, return it
        directly

        @param name: name of subject
        @param timeout: timeout of subject
        @param timeoutResponse: response of timeout
        @return: url of subject
        """
        subject = self.children.get(name)
        if subject:
            return self._getSubjectURL(subject)
        log.info('Create subject %s', name)
        subject = Subject(name, timeout, self._cbFinish, timeoutResponse)
        self.putChild(name, subject)
        return self._getSubjectURL(subject)

    def notifySubject(self, name, data):
        """Notify subject with data

        @param name: name of subject to notify
        @param data: data to send
        """
        if name in self.children:
            subject = self.children[name]
            subject.notify(data)


class XMLRPCInterface(xmlrpc.XMLRPC):
    """XML-RPC interface for controlling channel and subjects

    """

    def __init__(self, channel):
        """

        @param parent: parent of subjects
        """
        xmlrpc.XMLRPC.__init__(self, True)
        self.channel = channel

    def xmlrpc_createSubject(self, name, timeout, timeoutResponse=''):
        """Create a subject

        @param name: name of subject to create
        @param timeout: timeout of channel
        @param timeoutResponse: response to send when timeout, default is a
            empty string
        @return: URL of subject
        """
        url = self.channel.createSubject(name, timeout, timeoutResponse)
        return url

    def xmlrpc_notify(self, name, data=''):
        """Notify subject with data

        @param name: name of subject to notify
        @param data: data to send to peer
        @return: success or not
        """
        self.channel.notifySubject(name, data)


class Root(resource.Resource):
    def __init__(self, prefix, xmlrpcName='xmlrpc', channelName='subjects'):
        resource.Resource.__init__(self)
        channelURL = urlparse.urljoin(prefix, channelName + '/')
        self.channel = Channel(channelURL)
        self.xmlprc = XMLRPCInterface(self.channel)
        self.putChild(xmlrpcName, self.xmlprc)
        self.putChild(channelName, self.channel)

if __name__ == '__main__':
    logging.basicConfig(level=logging.INFO)

    import twisted.python.log
    loggingObserver = twisted.python.log.PythonLoggingObserver()
    loggingObserver.start()

    site = server.Site(Root('http://127.0.0.1:7070/'))
    reactor.listenTCP(7070, site)
    reactor.run()
