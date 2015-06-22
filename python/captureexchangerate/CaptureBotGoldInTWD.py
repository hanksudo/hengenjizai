#!/usr/bin/env/ python
# -*- coding: utf-8 -*-

import urllib2
from BeautifulSoup import BeautifulSoup
import time
import re
'''
取得台銀台幣黃金銀行買入賣出價
buy mean bank buy
sell mean bank sell
'''

def getGoldPrice():
    url = "http://rate.bot.com.tw/Pages/Static/UIP005.zh-TW.htm"
    data = urllib2.urlopen(url).read()
    soup = BeautifulSoup(data)

    dict = {
        'date': time.strftime("%Y-%m-%d", time.localtime())
    }

    prices = soup.findAll('td', { 'class' : 'decimal' }, text = re.compile("^\d+$"))
    dict['sell'] = prices[0]
    dict['buy'] = prices[1]

    return dict

if __name__ == "__main__":
    print getGoldPrice()