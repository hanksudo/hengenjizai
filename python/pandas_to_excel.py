import pandas as pd
results = []
results.append([1, 2, 3, 4, 5])
results.append([6, 7, 8, 9, 10])
results.append([11, 12, 13, 14, 15])

writer = pd.ExcelWriter('/tmp/pandas_simple.xlsx', engine='xlsxwriter')


df = pd.DataFrame(results)
df.to_excel(writer, "Sheet 1", index=False, header=False)
writer.save()
