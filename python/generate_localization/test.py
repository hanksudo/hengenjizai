import re

with open('new.txt', 'r') as f:
    newStr = f.read()
    newStr = '---xxx\n%s---yyy' % newStr

with open('test.txt') as f:
    oldStr = f.read()
    outTxt = re.sub(r"---xxx([\s\S]+?)---yyy", newStr, oldStr, re.M)

    with open('out.txt', 'w') as outfile:
        outfile.write(outTxt)
