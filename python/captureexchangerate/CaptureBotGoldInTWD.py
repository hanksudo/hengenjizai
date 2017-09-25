#!/usr/bin/env/ python
# -*- coding: utf-8 -*-
#
# 取得台銀台幣黃金銀行買入賣出價

from __future__ import print_function
import json
import time
try:
    from urllib.request import urlopen
except ImportError:
    from urllib2 import urlopen

from bs4 import BeautifulSoup


def getGoldPrice():
    url = "http://rate.bot.com.tw/gold"
    data = urlopen(url).read()
    soup = BeautifulSoup(data, "html.parser")

    response = {
        "date": time.strftime("%Y-%m-%d", time.localtime())
    }

    prices = soup.find_all("td", {"class": "rowSP_Ctrl_0_2_2"})
    response["sell"] = prices[0].find_next_sibling().text
    response["buy"] = prices[1].find_next_sibling().text

    return response


if __name__ == "__main__":
    print(json.dumps(getGoldPrice()))
