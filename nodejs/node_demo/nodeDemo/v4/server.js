var http = require("http");
var url = require("url");

function start(route, handle) {
    function onRequest(req, res) {
        var pathname = url.parse(req.url).pathname;
        console.log("Request for " + pathname + " received.");

        res.writeHead(200, {"Content-Type": "text/plain"});
        var respond = route(handle, pathname);
        res.write(respond);
        res.end();
    }

    http.createServer(onRequest).listen(1337);
    console.log('Server has started.');
}

exports.start = start;
