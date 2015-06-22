var server = require("./server");
var route = require("./route");
var requestHandlers = require("./requestHandlers");

var handle = {}

handle["/"] = requestHandlers.start;
handle["/start"] = requestHandlers.start;

server.start(route.route, handle);