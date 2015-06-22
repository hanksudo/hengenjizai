var koa = require('koa');

var app = koa();
var port = process.argv[2] || 8080;

app.keys = ['k4$J3D$N&6Ov', 'G4XXCwsNC&3o'];
app.use(function* () {
    var count = this.cookies.get('view', { signed: true }) || 0;
    this.cookies.set('view', ++count, { signed: true });
    this.body = count + ' views';
});

app.listen(port);
console.log("Server listening on port:", port);
