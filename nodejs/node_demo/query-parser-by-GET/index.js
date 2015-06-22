var server = require("./server");
var route = require("./route");
var requestHandlers = require("./requestHandlers");

var handlers = {
    "/": requestHandlers.start,
    "/start": requestHandlers.start,
    "/send": requestHandlers.send
}

server.start(route.route, handlers);