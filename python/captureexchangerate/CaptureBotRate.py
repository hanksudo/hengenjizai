#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
# 取得台銀貨幣價格
#
# Last Updated: 2016/10/05

import json

from bs4 import BeautifulSoup
from datetime import datetime
import time
try:
    from urllib.request import urlopen
except ImportError:
    from urllib2 import urlopen

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
        data = urlopen(url).read()
        soup = BeautifulSoup(data, "html.parser")
        for ele in soup.select('a[href*="/xrt/history/"]'):
            currency = ele["href"][-3:]
            if currency in currencies_list:
                results = ele.parent.find_next_siblings("td")
                response["buy_cash"][currency.lower()] = results[0].get_text()
                response["sell_cash"][currency.lower()] = results[1].get_text()
                response["buy_spot"][currency.lower()] = results[2].get_text()
                response["sell_spot"][currency.lower()] = results[3].get_text()

        return response
    else:
        return None


def isNormalDay():
    return datetime.now().weekday() < 5


if __name__ == "__main__":
    print(json.dumps(getCurrencyRate()))
