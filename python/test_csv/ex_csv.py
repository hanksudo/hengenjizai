#!/usr/bin/env python
# -*- coding: utf-8 -*-
import csv

with open('data.csv') as f:
    for row in csv.reader(f):
        print row
