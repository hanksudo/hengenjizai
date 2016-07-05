#!/usr/bin/env/ python
# -*- coding: utf-8 -*-
#
# 取得台銀台幣黃金銀行買入賣出價


import re
import time
import urllib2

from BeautifulSoup import BeautifulSoup
from utils import highlight_json


def getGoldPrice():
    url = "http://rate.bot.com.tw/Pages/Static/UIP005.zh-TW.htm"
    data = urllib2.urlopen(url).read()
    soup = BeautifulSoup(data)

    response = {
        "date": time.strftime("%Y-%m-%d", time.localtime())
    }

    prices = soup.findAll("td", {"class": "decimal"}, text=re.compile("^\d+$"))
    response["sell"] = prices[0]
    response["buy"] = prices[1]

    return response


if __name__ == "__main__":
    print highlight_json(getGoldPrice())
