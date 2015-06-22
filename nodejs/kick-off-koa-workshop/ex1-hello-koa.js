var koa = require('koa');

var app = koa();
var port = process.argv[2] || 8080;
// handlers here
// app.use(handlers);

app.use(function *() {
    this.body = 'hello koa';
});

app.listen(port);
console.log("Server listening on port:", port);
