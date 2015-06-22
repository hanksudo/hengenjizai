var querystring = require("querystring");

function start() {
    console.log("Request handler 'start' was called.");
    return "Hello Start";
}

var history = [];

function send(response, query) {
    console.log("Request handler 'send' was called.");
    console.log("Query string is: " + query);

    var parsedstring = querystring.parse(query);

    var obj = {
        message: parsedstring.m,
        username: parsedstring.u,
        timestamp: (new Date()).getTime()
    };
    console.log(obj);
    history.push(obj);

    // Debug
    for (var i = 0; i < history.length; i++) {
        console.log("[" + i + "]" + history[i].message);
    }

    return obj;
}



exports.start = start;
exports.send = send;