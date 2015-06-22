var http = require("http");
var url = require("url");
var WebSocketServer = require('websocket').server;

function start(route, handle) {
    function onRequest(req, res) {
        var pathname = url.parse(req.url).pathname
        var query = url.parse(req.url).query;

        console.log("Request for " + pathname + " received.");
        res.writeHead(200, {"Content-Type": "text/plain"});
        var respond = route(handle, pathname, res, query);
        res.write("Hello World");
        res.end();
    }

    var server = http.createServer(onRequest).listen(1337);
    console.log('Server has started.');

    wsServer = new WebSocketServer({
        httpServer: server,
        autoAcceptConnections: false
    });

    wsServer.on('request', function(request) {

        var connection = request.accept('echo-protocol', request.origin);
        console.log((new Date()) + ' Connection accepted.');
        connection.on('message', function(message) {
            if (message.type === 'utf8') {
                console.log('Received Message: ' + message.utf8Data);
                connection.sendUTF(message.utf8Data);
            }
            else if (message.type === 'binary') {
                console.log('Received Binary Message of ' + message.binaryData.length + ' bytes');
                connection.sendBytes(message.binaryData);
            }
        });
        connection.on('close', function(reasonCode, description) {
            console.log((new Date()) + ' Peer ' + connection.remoteAddress + ' disconnected.');
        });
    });
}

exports.start = start;