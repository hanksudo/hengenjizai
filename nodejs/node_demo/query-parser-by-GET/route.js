function route(handle, pathname, response, query) {
    // console.log("About to route a request for " + pathname);
    if (typeof handle[pathname] === 'function') {
        return handle[pathname](response, query);
    } else {
        console.log("No request handler found for " + pathname);
        return "Not found";
    }
}

exports.route = route;