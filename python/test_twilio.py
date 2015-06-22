# -*- coding: utf-8 -*-
from twilio.rest import TwilioRestClient

# put your own credentials here
ACCOUNT_SID = "**SID**"
AUTH_TOKEN = "**TOKEN**"

client = TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN)

message = client.messages.create(
    to="+8615116940530",
    from_="+12563983348",
    body="測試"
)

print message.sid
