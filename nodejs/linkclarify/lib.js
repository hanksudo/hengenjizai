var request = require('request');

exports.expandUrl = function(url, callback) {
    console.log('Expanding url : '+url);
    request({
        method: "HEAD",
        url: url,
        followAllRedirects: true
    }, function(err, res) {
        if (!err && res.statusCode == 200) return callback(res.request.href);
    });
}
