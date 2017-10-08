import re

url = "https://github.com/hanksudo"

print re.match(r".+\/(?P<user>\w+)", url).group("user")


