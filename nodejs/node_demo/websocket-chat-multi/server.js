var http = require("http");
var url = require("url");
var WebSocketServer = require('websocket').server;

var history = [];
var clients = [];


function htmlEntities(str) {
    return String(str).replace(/&/g, '&amp;').replace(/</g, '&lt;')
                      .replace(/>/g, '&gt;').replace(/"/g, '&quot;');
}

// Array with some colors
var colors = [ 'red', 'green', 'blue', 'magenta', 'purple', 'plum', 'orange' ];
// ... in random order
colors.sort(function(a,b) { return Math.random() > 0.5; } );

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
        var index = clients.push(connection) - 1;
        var userName = false;
        var userColor = false;

        console.log((new Date()) + ' Connection accepted.');

        // send back chat history
        if (history.length > 0) {
            connection.sendUTF(JSON.stringify( { type: 'history', data: history} ));
        }


        connection.on('message', function(message) {
            if (message.type === 'utf8') {

                if (false && userName === false) {
                    // remember user name
                    userName = htmlEntities(message.utf8Data);
                    // get random color and send it back to the user
                    userColor = colors.shift();
                    connection.sendUTF(JSON.stringify({ type:'color', data: userColor }));
                    console.log((new Date()) + ' User is known as: ' + userName
                                + ' with ' + userColor + ' color.');

                } else { // log and broadcast the message
                    console.log((new Date()) + ' Received Message from '
                                + userName + ': ' + message.utf8Data);

                    // we want to keep history of all sent messages
                    var obj = {
                        time: (new Date()).getTime(),
                        text: htmlEntities(message.utf8Data),
                        // author: userName,
                        // color: userColor
                    };
                    history.push(obj);
                    history = history.slice(-100);

                    // broadcast message to all connected clients
                    var json = JSON.stringify({ type:'message', data: obj });
                    for (var i=0; i < clients.length; i++) {
                        clients[i].sendUTF(json);
                    }
                }

                // console.log('Received Message: ' + message.utf8Data);
                // connection.sendUTF(message.utf8Data);
            }
            else if (message.type === 'binary') {
                console.log('Received Binary Message of ' + message.binaryData.length + ' bytes');
                connection.sendBytes(message.binaryData);
            }
        });
        connection.on('close', function(reasonCode, description) {
            if (userName !== false && userColor !== false) {
                console.log((new Date()) + " Peer "
                    + connection.remoteAddress + " disconnected.");
                // remove user from the list of connected clients
                clients.splice(index, 1);
                // push back user's color to be reused by another user
                colors.push(userColor);
            }
        });
    });
}

exports.start = start;