import os
import subprocess

with open("in.txt", "w") as f:
    f.write("123456")

subprocess.call(["zip", "-P", "123", "out.zip", "in.txt"])

try:
    os.remove("in.txt")
except:
    pass