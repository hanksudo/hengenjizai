from tasks import add
from time import sleep
from datetime import timedelta, datetime


def arrange_tasks():
    # normal call
    # add(0, 10)

    # send task message
    add.delay(1, 10)
    add.apply_async((2, 10))

    # executes 10 seconds from now
    add.apply_async((3, 10), countdown=5)

    # executes 10 seconds from now - eta
    add.apply_async((4, 10), eta=datetime.utcnow() + timedelta(seconds=6))

    # linking(callbacks / errbacks)
    add.apply_async((5, 10), link=add.s(7))

i = 1
while 1:
    print "Arrange events: %g, %s" % (i, datetime.now())
    arrange_tasks()
    i = i + 1
    sleep(10)
