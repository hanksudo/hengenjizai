from datetime import timedelta


def next_weekday(date, weekday):
    # Monday = 0 or 7
    day_gap = weekday - date.weekday()
    if day_gap <= 0:
        day_gap += 7
    return date + timedelta(days=day_gap)


def last_weekday(date, weekday):
    day_gap = weekday - date.weekday()
    if day_gap >= 0:
        day_gap -= 7
    return date + timedelta(days=day_gap)
