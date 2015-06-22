var http = require("http");
var url = require("url");

function start(route, handle) {
    function onRequest(req, res) {
        var pathname = url.parse(req.url).pathname
        var query = url.parse(req.url).query;

        console.log("Request for " + pathname + " received.");

        res.writeHead(200, {"Content-Type": "text/json"});
        var respond = route(handle, pathname, res, query);
        res.write(JSON.stringify(respond));
        res.end();
    }

    var server = http.createServer(onRequest).listen(1337);
    console.log('Server has started.');

}

exports.start = start;