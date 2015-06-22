import unittest
from datetime import datetime
from weekday import next_weekday, last_weekday

fmt = '%Y-%m-%d'
date = datetime(2015, 5, 1)


class WeekdayTestCase(unittest.TestCase):
    def test_last_week_day(self):
        self.assertEqual(last_weekday(date, 0).strftime(fmt), '2015-04-27')
        self.assertEqual(last_weekday(date, 1).strftime(fmt), '2015-04-28')
        self.assertEqual(last_weekday(date, 2).strftime(fmt), '2015-04-29')
        self.assertEqual(last_weekday(date, 3).strftime(fmt), '2015-04-30')
        self.assertEqual(last_weekday(date, 4).strftime(fmt), '2015-04-24')
        self.assertEqual(last_weekday(date, 5).strftime(fmt), '2015-04-25')
        self.assertEqual(last_weekday(date, 6).strftime(fmt), '2015-04-26')

    def test_next_week_day(self):
        self.assertEqual(next_weekday(date, 0).strftime(fmt), '2015-05-04')
        self.assertEqual(next_weekday(date, 1).strftime(fmt), '2015-05-05')
        self.assertEqual(next_weekday(date, 2).strftime(fmt), '2015-05-06')
        self.assertEqual(next_weekday(date, 3).strftime(fmt), '2015-05-07')
        self.assertEqual(next_weekday(date, 4).strftime(fmt), '2015-05-08')
        self.assertEqual(next_weekday(date, 5).strftime(fmt), '2015-05-02')
        self.assertEqual(next_weekday(date, 6).strftime(fmt), '2015-05-03')

if __name__ == '__main__':
    unittest.main()
