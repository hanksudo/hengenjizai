import time
import module
from module import MyModule
from twisted.python import rebuild
from twisted.internet import reactor


def magic():
    time.sleep(5)
    rebuild.rebuild(module)

mm = MyModule()
print mm.c()
magic()
print mm.c()

reactor.run()
