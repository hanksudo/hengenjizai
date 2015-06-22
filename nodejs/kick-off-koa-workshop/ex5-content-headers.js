var koa = require('koa');

var app = koa();
var port = process.argv[2] || 8080;

app.use(function *(next) {
    this.body = 'ok';
    if (this.request.is('application/json')) {
        this.body = {message: 'hi!'};
    }

});

app.listen(port);
console.log("Server listening on port:", port);
