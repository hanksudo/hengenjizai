import re
# from ua_parser import user_agent_parser

uas = [
    '1.0.4/Dalvik/1.6.0 (Linux; U; Android 4.1.1; MI 2S MIUI/4.9.26)',
    '1.0.4/Dalvik/1.6.0 (Linux; U; Android 4.2.2; 2013023 MIUI/JHBMIBF18.0)',
    '0724.01/Dalvik/1.6.0 (Linux; U; Android 4.1.2; LT26i Build/6.2.B.1.96)',
    '1.0.7(56-b) (iPhone 5 (Global); iOS 7.1.2; Scale/2.00)'
]

for ua in uas:
    match_device = re.match(r'(.+MIUI)\/.+', ua)
    if match_device:
        print match_device.group(1)
        print match_device.group(1).split('; ')[-1]
