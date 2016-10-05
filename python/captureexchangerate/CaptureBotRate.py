#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
# 取得台銀貨幣價格
#
# Last Updated: 2016/10/05

import json
import urllib2
from bs4 import BeautifulSoup
from datetime import datetime
import time

currencies_list = ["JPY", "GBP", "ZAR", "AUD", "NZD", "CAD", "SEK", "USD", "CNY"]


def getCurrencyRate():
    if isNormalDay():
        response = {
            "date": time.strftime("%Y-%m-%d", time.localtime()),
            "buy_cash": {},
            "sell_cash": {},
            "buy_spot": {},
            "sell_spot": {}
        }

        url = "http://rate.bot.com.tw/xrt"
        data = urllib2.urlopen(url).read()
        soup = BeautifulSoup(data, "html.parser")
        for ele in soup.select('a[href*="/xrt/history/"]'):
            currency = ele["href"][-3:]
            if currency in currencies_list:
                results = ele.parent.find_next_siblings("td")
                response["buy_cash"][currency.lower()] = results[0].encode_contents()
                response["sell_cash"][currency.lower()] = results[1].encode_contents()
                response["buy_spot"][currency.lower()] = results[2].encode_contents()
                response["sell_spot"][currency.lower()] = results[3].encode_contents()

        return response
    else:
        return None


def isNormalDay():
    return datetime.now().weekday() < 5


if __name__ == "__main__":
    print json.dumps(getCurrencyRate())
