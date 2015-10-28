from twisted.internet import reactor, defer, threads
from twisted.python import threadpool
import time

pool = threadpool.ThreadPool(minthreads=10, maxthreads=100, name="thread-pool-practice")

task_list = []


def f():
    time.sleep(0.5)

for i in range(100):
    d = threads.deferToThreadPool(reactor, pool, f)
    task_list.append(d)


start_time = time.time()


def _done(_):
    print '_done'
    print time.time() - start_time
    reactor.stop()


d = defer.DeferredList(task_list)
d.addCallback(_done)
reactor.callWhenRunning(lambda _: pool.start(), pool)
reactor.addSystemEventTrigger('after', 'shutdown', pool.stop)
reactor.run()
