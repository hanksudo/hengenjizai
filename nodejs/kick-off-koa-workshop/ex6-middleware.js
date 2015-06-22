var koa = require('koa');

var app = koa();
var port = process.argv[2] || 8080;

app.use(responseTime());
app.use(upperCase());

app.use(function* () {
    this.body = 'hello koa';
});

function responseTime() {
    return function* (next) {
        var start = new Date;
        yield next;
        this.set('X-Response-Time', new Date - start);
    };
}

function upperCase() {
    return function* (next) {
        yield next;
        this.body = this.body.toUpperCase();
    };
}

app.listen(port);
console.log("Server listening on port:", port);
