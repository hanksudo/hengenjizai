
```bash
uwsgi --http :9090 --wsgi-file foobar.py
uwsgi --http :9090 --wsgi-file foobar.py --master --process 4 --threads 2
uwsgi --http :9090 --wsgi-file foobar.py --master --processes 4 --threads 2 --stats 127.0.0.1:9191

uwsgi --http :9090 --wsgi-file foobar.py --master --processes 4 --threads 2 --stats /tmp/stats.socket

uwsgitop /tmp/stats.socket


uwsgi --socket 127.0.0.1:3031 --wsgi-file foobar.py --master --processes 4 --threads 2 --stats /tmp/stats.socket

uwsgi --http-socket 127.0.0.1:3031 --wsgi-file foobar.py --master --processes 4 --threads 2 --stats /tmp/stats.socket

# Deploying Django 
uwsgi --socket 127.0.0.1:3031 --chdir /vagrant/django/mysite/ --wsgi-file mysite/wsgi.py --master --processes 4 --threads 2 --stats /tmp/stats.socket

# Deploying Flask
uwsgi --socket 127.0.0.1:3031 --wsgi-file /vagrant/flaskapp.py --callable app --processes 4 --threads 2 --stats /tmp/stats.socket
```

**mysite.ini**
```
[uwsgi]
socket = 127.0.0.1:3031
chdir = /vagrant/django/mysite/
wsgi-file = mysite/wsgi.py
processes = 4
threads = 2
stats = /tmp/stats.socket
```

`uwsgi mysite.ini`
