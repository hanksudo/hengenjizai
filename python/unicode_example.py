# -*- coding: utf-8 -*-
s = u'\u843d\u65bc\u76ee\u6a19\u8840\u7cd6\u503c\u7bc4\u570d\u5167'.encode('utf-8')
a = '\u843d\u65bc\u76ee\u6a19\u8840\u7cd6\u503c\u7bc4\u570d\u5167'

print u'\u843d\u65bc\u76ee\u6a19\u8840\u7cd6\u503c\u7bc4\u570d\u5167' == unicode('\u843d\u65bc\u76ee\u6a19\u8840\u7cd6\u503c\u7bc4\u570d\u5167')

print s, type(s)
print a, type(a)
print a.encode('utf-8'), type(a.encode('utf-8'))
print a.decode('unicode_escape').encode('utf-8')
print str(a), type(str(a))

ss = u'中文'.encode('utf-8')
print ss, type(ss)
