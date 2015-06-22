var koa = require('koa');
var parse = require('co-body');

var app = koa();
var port = process.argv[2] || 8080;

app.use(function *(next) {
    // only accept POST method
    if (this.method !== 'POST') {
        return yield next;
    }

    var body = yield parse(this);
    if (!body.name) {
        this.throw(400, 'Bad request');
    }

    this.body = body.name.toUpperCase();
});

app.listen(port);
console.log("Server listening on port:", port);
