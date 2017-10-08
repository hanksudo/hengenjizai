import urllib2

request = urllib2.Request('http://github.com')
response = urllib2.urlopen(request)
print response.getcode()
print response.read()
