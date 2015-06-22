#!/usr/bin/env python
# -*- coding: utf-8 -*-

import re
from lxml import etree
import dateutil.parser

import utility

#url = 'http://www.dailymotion.com/video/xf3lhc'
url = 'http://www.dailymotion.com/video/xghybe'

data = {}
soup = utility.fetch_html(url)

data['description'] = soup.find('div', id='video_description').renderContents()

tags = []
for t in soup.find('div', id='video_infos_tags').findAll('span'):
    if t.renderContents() != '':
        tags.append(t.renderContents())

xml = str(soup.find(text=re.compile('PageMap'))).replace('<!--', '').replace('-->', '')
tree = etree.fromstring(xml)

for child in tree.iterdescendants():
    if child.tag=='Attribute':
        data[child.get('name')] = child.text

print '[Title] = ', data['title']
print '[Description] = ', data['description']
print '[uploaded_by] = ', data['owner']
print '[uploaded_on] = ', dateutil.parser.parse(data['date'])
print '[channel] = ', data['channel']
print '[tags] = ', tags

#aspect = False
#video.aspect_width = (4,16)[aspect]
#video.aspect_height = (3,9)[aspect]


#thumb_name = video.name.split('%2F')[0]
#video.thumbnail_low = 'http://www.metacafe.com/thumb/' + thumb_name + '.jpg'
#video.thumbnail_high = 'http://www.metacafe.com/thumb/' + thumb_name + '.jpg'

