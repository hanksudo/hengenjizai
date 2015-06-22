var WebSocket = require('ws');
var ws = new WebSocket('ws://localhost:8080');

ws.on('open', function() {
    ws.send('test messsage');
});

ws.on('message', function(data, flags) {
    console.log(flags);
});