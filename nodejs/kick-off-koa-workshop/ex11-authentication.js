var path = require('path');
var koa = require('koa');
var session = require('koa-session');
var parse = require('co-body');
var views = require('co-views');

var app = koa();
app.keys = ['k4$J3D$N&6Ov', 'G4XXCwsNC&3o'];
app.use(session(app));

var render = views(path.join(__dirname, '/views'), {
    ext: 'ejs'
});
var port = process.argv[2] || 8080;

app.use(function* home(next) {
    if (this.path !== '/') {
        return yield next;
    }

    if (!this.session.authenticated) {
        return this.status = 401;
    }

    this.body = 'hello world';
});

app.use(function* login(next) {
    if (this.path !== '/login') {
        return yield next;
    }

    if (this.method === 'POST') {
        var body = yield parse(this);
        if (body.username !== 'username' || body.password !== 'password') {
            return this.status = 400;
        }
        this.session.authenticated = true;
        this.redirect('/');
    } else {
        this.body = yield render('form', {});
    }
});

app.use(function* logout(next) {
    if (this.path !== '/logout') {
        return yield next;
    }
    this.session.authenticated = null;
    this.redirect('/login');
});

app.listen(port);
console.log("Server listening on port:", port);
