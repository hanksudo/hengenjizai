import csv
import json

unitOfCountry = {}

with open("unit.csv", "rb") as f:
    reader = csv.reader(f, delimiter="\t")
    for i, line in enumerate(reader):
        if i > 0:
            [country, code, unit, _, _] = line[0].split(',')
            unitOfCountry[code] = {
                "country": country,
                "unit": unit
            }

file = open('unit.json', 'w')
file.write(json.dumps(unitOfCountry, indent=4, sort_keys=True))
file.close()
