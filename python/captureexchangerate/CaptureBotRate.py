#!/usr/bin/env python
# -*- coding: utf-8 -*-

'''
取得台銀貨幣即期銀行買入價
'''

import urllib2
from BeautifulSoup import BeautifulSoup
from datetime import datetime
import time
import re

currencyList = ['ZAR', 'AUD', 'NZD', 'CAD', 'SEK', 'USD']

def getCurrencyRate():
	if isNormalDay():
		url = "http://rate.bot.com.tw/Pages/Static/UIP003.zh-TW.htm"
		data = urllib2.urlopen(url).read()
		print data
		soup = BeautifulSoup(data)

		dict = {
			'date': time.strftime("%Y-%m-%d", time.localtime())
		}
		for i in soup.findAll('td', { 'class' : 'titleLeft'}):
			title = i.find('img').next.encode('utf-8')
			currency = re.search('\(([\w]+)\)', title).group(1)
			if currency in currencyList:
				dict[currency.lower()] = i.parent.findAll('td')[3].renderContents()

		return dict

	else:
		return None


def isNormalDay():
	return datetime.now().weekday() < 5


if __name__ == "__main__":
	print getCurrencyRate()