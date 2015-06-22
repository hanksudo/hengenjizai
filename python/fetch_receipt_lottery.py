#!/usr/bin/env python
# -*- coding: UTF-8 -*-

"""
Demo: Use BeautifulSoup parse HTML

@author: Hank
@version: 2011/1/15
@updated: 2013/3/11
"""

import urllib2, re
from BeautifulSoup import BeautifulSoup

def remove_html_tags(data):
    p = re.compile(r'<.*?>')
    return p.sub('', data)

def http_download(url):
    try:
        headers = {
            "Accept" : "text/html",
            "User-Agent" : "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.160 Safari/537.22"
        }
        request = urllib2.Request(url, headers=headers)
        return urllib2.urlopen(request).read()
    except urllib2.HTTPError, e:
        exit()
    except urllib2.URLError, e:
        exit()


url = 'http://www.etax.nat.gov.tw/etwmain/front/ETW183W2?id=13c705555ae000008b1e384e209a4729'
response = http_download(url)
soup = BeautifulSoup(response)
results = [i.renderContents().strip() for i in soup.findAll('span', {'class': 't18Red'})]

print '特別奬'
for item in results[0:1]: print item

print '特獎'
for item in results[1:2]: print item

print '頭獎'
for item in results[2:3]: print item

print '增開六獎'
for item in results[3:4]: print item