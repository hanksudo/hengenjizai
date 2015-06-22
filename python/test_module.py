class Module(object):
    def __init__(self):
        print 'init'

    def __exit__(self):
        print 'exit'

with Module() as m:
    pass
