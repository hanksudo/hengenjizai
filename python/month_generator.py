from datetime import datetime
from dateutil.relativedelta import relativedelta

start_date = datetime(2014, 6, 1)
end_date = datetime.now()

while start_date < end_date:
    print start_date
    start_date += relativedelta(months=1)
