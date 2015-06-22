var koa = require('koa');

var app = koa();
var port = process.argv[2] || 8080;

app.use(errorHandler());

app.use(function* () {
    if (this.path === '/error') {
        throw new Error('ooops');
    }
    this.body = 'OK';
});

function errorHandler() {
    return function* (next) {
        try {
            yield next;
        } catch(err) {
            this.status = 500;
            this.body = 'internal server error';

            // can emit on app for log
            this.app.emit('error', err, this);
        }
    };
}

app.on('error', function(err, context) {
    console.log(err);
});

app.listen(port);
console.log("Server listening on port:", port);
