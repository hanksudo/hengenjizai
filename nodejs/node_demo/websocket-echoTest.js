var WebSocket = require('ws');
// var ws = new WebSocket('ws://echo.websocket.org/');
var ws = new WebSocket('ws://localhost:8080');
ws.on('open', function() {
    console.log('connected');
    ws.send(Date.now().toString(), {mask: true});
});

ws.on('message', function(data, flags) {
    console.log(data);
    console.log('Roundtrip time: ' + (Date.now() - parseInt(data)) + 'ms', flags);
    setTimeout(function() {
        ws.send(Date.now().toString(), {mask: true});
    }, 500);
});

ws.on('close', function() {
    console.log('disconnected');
});