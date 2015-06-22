var koa = require('koa');
var session = require('koa-session');

var app = koa();
var port = process.argv[2] || 8080;

app.keys = ['k4$J3D$N&6Ov', 'G4XXCwsNC&3o'];

app.use(session(app));

app.use(function* () {
    var count = this.session.views || 0;
    this.session.views = ++count;
    this.body = count + ' views';
});

app.listen(port);
console.log("Server listening on port:", port);
