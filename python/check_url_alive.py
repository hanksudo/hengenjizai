#!/usr/bin/env python
# -*- coding: utf-8 -*-
try:
    from urllib.request import urlopen, HTTPError
except ImportError:
    from urllib2 import urlopen, HTTPError


def is_url_alive(url):
    try:
        urlopen(url).getcode()
        return True
    except HTTPError:
        return False
    
if __name__ == "__main__":
    assert is_url_alive("https://www.google.com")
    assert not is_url_alive("https://www.google.com/abcde")
