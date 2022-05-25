import os
import sys
file = open("/home/spy/dev/JavaGameEngine/docs/docs/src/JavaExampels/"+sys.argv[1]+".java","r")

string =""
for line in file.readlines():
    string+=(line).replace("\n","\\n")
print(string)