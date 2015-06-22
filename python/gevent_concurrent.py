import sys
import gevent
from gevent import monkey

urls = ['http://www.google.com', 'http://github.com', 'http://www.python.org']

# patches stdlib (including socket and ssl modules) to cooperate with other greenlets
monkey.patch_all()


if sys.version_info[0] == 3:
    from urllib.request import urlopen
else:
    from urllib2 import urlopen


def print_head(url):
    print "Starting %s" % url
    data = urlopen(url).read()
    print "%s: %s bytes: %r" % (url, len(data), data[:20])

jobs = [gevent.spawn(print_head, url) for url in urls]

gevent.wait(jobs)
