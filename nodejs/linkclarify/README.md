# LinkClarify - 將短網址打回原形

Laste Updated: 2013-03-27

## Why do this 為何做

* 在按下不明的短網址前, 可以先預覽看看實際的網址是什麼
* 有些有害的網址會利用短網址的偽裝或是利用JavaScript導向至其他的網站
* 檢查Spyware/Malware
* No similar service check Spyware/Malware
* No similar service support both Asia/West shorten url service

## Feature 特性

* 支援解析多重轉址 Extract Redirect url
* 支援安全性檢查 Security Scan
* 支援瀏覽器插件 Chrome/Firefox/Bookmarklet
* 

## What to do 要做什麼

   * fast Web API (ex: http://linkclarify.info/http://bit.ly/rLfmVj)
   * Chrome Extension [https://developers.google.com/chrome/web-store/docs/publish](https://developers.google.com/chrome/web-store/docs/publish)
   * Firefox Addon [https://addons.mozilla.org/en-US/developers/](https://addons.mozilla.org/en-US/developers/)
   * bookmarklet
   
### Future 未來可做的

* 顯示預覽截圖 (ex: http://linkclarify.info/t/http://bit.ly/rLfmVj)

## How to do 如何做

### Reference 參考

* try to correct links that have comma period or quote on the end as most short links don't use these characters.(by Chris)
* If you could that would be great, a king of browser "firewall". (by Chris)
* [How to Preview Shortened URLs (TinyURL, bit.ly, is.gd, and more)](http://security.thejoshmeister.com/2009/04/how-to-preview-shortened-urls-tinyurl.html)

   
### Security Test (Spyware/Malware) 安全性檢查

* [Google Safe Browsing API](https://developers.google.com/safe-browsing/)
* [Google SafeBrowsing](http://google.com/safebrowsing/diagnostic?site=linkclarity.com)
* [Anatomy of a "feature" - should JavaScript be allowed to change a web link after you click on it?](http://nakedsecurity.sophos.com/2013/03/26/anatomy-of-a-feature-change-link-after-clicking/)

### Powered

* NodeJS
* MongoDB
* JavaScript

### UI refer

* copy link from any entire link
* qtip - http://craigsworks.com/projects/qtip2/demos/
* bootstrap-tooltip.js
* OpenTip - https://github.com/enyo/opentip

### Crisis 危機

* 需要有支援全球性(especially: China)的Server, 而且response要夠快
* 防止惡意的API request(prevent both on Client/Server)

### Similar Service 已有的相似服務 
* [http://www.unshorten.com/](http://www.unshorten.com/)
* [http://linkpeelr.appspot.com/](http://linkpeelr.appspot.com/)
* [http://untiny.me/](http://untiny.me/)

| Site       |Support Asia| Extension  | Security   |Redirect    |
|:-----------|:----------:|:----------:|:----------:|:----------:|
| unshorten  |O           |X           |X           |O           |
| linkpeelr  |X           |O           |X           |X           |
| untiny     |X           |X           |X           |X           |



### Supported Services 將支援的短網址 
1. t.co
1. bit.ly
1. t.cn
1. TinyURL.com
1.
1. …