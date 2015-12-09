import glob
import csv
import xlwt
import os

wb = xlwt.Workbook()
for i, filename in enumerate(glob.glob("/tmp/*.csv")):
    (f_path, f_name) = os.path.split(filename)
    (f_short_name, f_extension) = os.path.splitext(f_name)
    ws = wb.add_sheet(str(i))
    spamReader = csv.reader(open(filename, 'rb'))
    for rowx, row in enumerate(spamReader):
        for colx, value in enumerate(row):
            ws.write(rowx, colx, value)
wb.save("/tmp/out.xls")
