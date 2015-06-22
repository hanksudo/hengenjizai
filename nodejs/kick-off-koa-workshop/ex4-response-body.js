var fs = require('fs');
var koa = require('koa');

var app = koa();
var port = process.argv[2] || 8080;

app.use(function *(next) {
    if (this.path !== '/stream') {
        return yield next;
    }

    // koa will automatically handle errors and leaks
    this.body = fs.createReadStream(process.argv[3]);
});

app.use(function *(next) {
    if (this.path !== '/json') {
        return yield next;
    }

    this.body = {foo: 'bar'};
});

app.listen(port);
console.log("Server listening on port:", port);
