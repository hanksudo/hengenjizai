import re
import boto

from boto import sns


print sns.regions()
arn = "arn:aws:sns:us-east-1:75333967:app/APNS/prod"
region = re.match("arn:aws:.+:(.+-.+-\d+):.+", arn).group(1)
client = sns.connect_to_region(region, **{
    "debug": 1,
    "aws_access_key_id": "",
    "aws_secret_access_key": ""
})

print client.get_platform_application_attributes(arn)
