#!/usr/bin/env python
# -*- coding: utf-8 -*-

from suds import WebFault
from suds.client import Client
from suds.xsd.doctor import Import, ImportDoctor

import datetime
# enable logging to see transmitted XML
import logging
logging.basicConfig(level=logging.INFO)
logging.getLogger('suds.client').setLevel(logging.DEBUG)

# fix broken wsdl
# add <s:import namespace="http://www.w3.org/2001/XMLSchema"/> to the wsdl
imp = Import('http://www.w3.org/2001/XMLSchema',
             location='http://www.w3.org/2001/XMLSchema.xsd')
imp.filter.add('http://tempuri.org/')

wsdl_url = 'http://wsdlurl/wsdl.asmx?WSDL'
client = Client(wsdl_url, doctor=ImportDoctor(imp))
# print client

sendXML = """<?xml version="1.0" ?>
<soap12:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://www.w3.org/2003/05/soap-envelope">
    <soap12:Body>
        <AddNewMData xmlns="http://tempuri.org/">
        <MeterData>
            <UploadData>
                <GroupID>Test</GroupID>
                <UserID>A1234567892</UserID>
                <MeasureData>
                    <DetailData>
                        <MDataID>0</MDataID>
                        <MDate>2014-11-20</MDate>
                        <MTime>%s</MTime>
                        <MType>1</MType>
                        <MValue1>%d</MValue1>
                        <MNote>Park</MNote>
                    </DetailData>
                </MeasureData>
            </UploadData>
        </MeterData>
        </AddNewMData>
    </soap12:Body>
</soap12:Envelope>
"""


try:
    # now = datetime.datetime.now()
    # for i in range(1, 101):
    #     client.factory.create('AddNewMData')
    #     client.service.AddNewMData(__inject={'msg': sendXML % ((now+datetime.timedelta(seconds=i)).strftime("%H:%M:%S"), i)})
    now = datetime.datetime(2013, 11, 20, 17, 51, 42)
    client.factory.create('AddNewMData')
    result = client.service.AddNewMData(__inject={'msg': sendXML % (now.strftime("%H:%M:%S"), 55)})
    if result != 0:
        print result

    # print client.last_sent()
    # GetMDdata = client.factory.create('GetMDdata')
    # getMData.MeterData
    # result = client.service.GetMDdata()
    # print result
except WebFault, e:
    pass
except Exception, e:
    print e
