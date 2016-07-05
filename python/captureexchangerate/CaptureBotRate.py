#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
# 取得台銀貨幣價格

import urllib2
from BeautifulSoup import BeautifulSoup
from utils import highlight_json
from datetime import datetime
import time
import re

currencyList = ["JPY", "GBP", "ZAR", "AUD", "NZD", "CAD", "SEK", "USD"]


def getCurrencyRate():
    if isNormalDay():
        url = "http://rate.bot.com.tw/Pages/Static/UIP003.zh-TW.htm"
        data = urllib2.urlopen(url).read()
        soup = BeautifulSoup(data)

        response = {
            "date": time.strftime("%Y-%m-%d", time.localtime()),
            "buy_cash": {},
            "sell_cash": {},
            "buy_spot": {},
            "sell_spot": {}
        }
        for i in soup.findAll("td", {"class": "titleLeft"}):
            title = i.find("img").next.encode("utf-8")
            currency = re.search("\(([\w]+)\)", title).group(1)
            if currency in currencyList:
                response["buy_cash"][currency.lower()] = i.parent.findAll("td")[1].renderContents()
                response["sell_cash"][currency.lower()] = i.parent.findAll("td")[2].renderContents()
                response["buy_spot"][currency.lower()] = i.parent.findAll("td")[3].renderContents()
                response["sell_spot"][currency.lower()] = i.parent.findAll("td")[4].renderContents()

        return response
    else:
        return None


def isNormalDay():
    return datetime.now().weekday() < 5


if __name__ == "__main__":
    print highlight_json(getCurrencyRate())
