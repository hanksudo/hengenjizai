var http = require("http");
var url = require("url");

function start(route) {
    function onRequest(req, res) {
        var pathname = url.parse(req.url).pathname;
        console.log("Request for " + pathname + " received.");

        route(pathname);

        res.writeHead(200, {"Content-Type": "text/plain"});
        res.write("Hello World");
        res.end();
    }

    http.createServer(onRequest).listen(1337);
    console.log('Server has started.');
}

exports.start = start;