from twisted.internet import threads, reactor


def proc1():
    x = 0
    while x < 100:
        print "Proc----------1\n"
        x += 1


def proc2():
    x = 0
    while x < 100:
        print "Proc----------2\n"
        x += 1


def proc3():
    x = 0
    while x < 100:
        print "Proc----------3\n"
        x += 1


d1 = threads.deferToThread(proc1)
d2 = threads.deferToThread(proc2)
d3 = threads.deferToThread(proc3)

reactor.run()
