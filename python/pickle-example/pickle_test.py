#!/usr/bin/env python
# -*- coding: utf-8 -*-

# https://docs.python.org/3/library/pickle.html

import os
import cPickle as pickle
import json

dir_path = os.path.dirname(os.path.realpath(__file__))

obj = {
    "glossary": {
        "GlossDiv": {
            "GlossList": {
                "GlossEntry": {
                    "Abbrev": "ISO 8879:1986",
                    "Acronym": "SGML",
                    "GlossDef": {
                        "GlossSeeAlso": [
                            "GML",
                            "XML"
                        ],
                        "para": "A meta-markup language, used to create markup languages such as DocBook."
                    },
                    "GlossSee": "markup",
                    "GlossTerm": "Standard Generalized Markup Language",
                    "ID": "SGML",
                    "SortAs": "SGML"
                }
            },
            "title": "S"
        },
        "title": "example glossary"
    }
}

# json read / write

with open(dir_path + "/sample.json", "wb") as f:
    f.write(json.dumps(obj))

with open(dir_path + "/sample.json", "r") as f:
    obj = json.loads(f.read())
    print(obj)

# pickle read / write
pickle.dump(obj, open(dir_path + "/sample.p", "wb"))
pickle.load(open(dir_path + "/sample.p", "r"))