#!/usr/bin/env/ python
# -*- coding: utf-8 -*-

import json
import re
import time
import urllib2

from BeautifulSoup import BeautifulSoup
from pygments import highlight
from pygments.formatters import Terminal256Formatter
from pygments.lexers.sql import PostgresLexer
from pygments.lexers.web import JsonLexer


'''
取得台銀台幣黃金銀行買入賣出價
buy mean bank buy
sell mean bank sell
'''

def getGoldPrice():
    url = "http://rate.bot.com.tw/Pages/Static/UIP005.zh-TW.htm"
    data = urllib2.urlopen(url).read()
    soup = BeautifulSoup(data)

    response = {
        'date': time.strftime("%Y-%m-%d", time.localtime())
    }

    prices = soup.findAll('td', { 'class' : 'decimal' }, text = re.compile("^\d+$"))
    response['sell'] = prices[0]
    response['buy'] = prices[1]

    return response

def highlight_json(json_str_or_obj):
    """http://pygments.org/docs/"""
    if isinstance(json_str_or_obj, dict):
        json_str_or_obj = json.dumps(json_str_or_obj, indent=4)
        json_str_or_obj = json_str_or_obj.decode("unicode_escape").encode("utf8")
        return highlight(json_str_or_obj, JsonLexer(), Terminal256Formatter())

if __name__ == "__main__":
    print highlight_json(getGoldPrice())
