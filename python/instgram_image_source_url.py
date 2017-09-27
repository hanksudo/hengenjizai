# -*- coding: utf-8 -*-
#
# Grab instagram image source url
#
from __future__ import print_function
from bs4 import BeautifulSoup

try:
    from urllib.request import urlopen
except ImportError:
    from urllib2 import urlopen

response = urlopen("https://www.instagram.com/p/BACozJ6kUhc/?taken-by=hanksudo")
soup = BeautifulSoup(response.read(), "html.parser")

og_image_url = soup.find("meta", property="og:image")["content"]

for ext in ("", ".txt", ".html"):
    print("{}{}".format(og_image_url, ext))
