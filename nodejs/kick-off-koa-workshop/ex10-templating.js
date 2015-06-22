var path = require('path');
var koa = require('koa');
var views = require('co-views');

var app = koa();
var render = views(path.join(__dirname, '/views'), {
    ext: 'ejs'
});
var port = process.argv[2] || 8080;
var user = {
    name: {
        first: 'Tobi',
        last: 'Holowaychuk'
    },
    species: 'ferret',
    age: 3
};
app.use(function* () {
    this.body = yield render('user', {
        user: user
    });
});

app.listen(port);
console.log("Server listening on port:", port);
