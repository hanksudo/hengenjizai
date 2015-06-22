#!/usr/bin/env node

var http = require('http');
var databaseUrl = "markthree";
var collections = ["url_counts"];
var db = require("mongojs").connect(databaseUrl, collections);

db.url_counts.find({}, function(err, bookmarks) {
  var urls = [];

  if (err || !bookmarks) console.log("No bookmarks found");
  else bookmarks.forEach( function(bookmark) {
      urls.push(bookmark.url);
  });
  checkAPI(urls.unique());
});

function checkAPI(urls) {
  urls.forEach(function(url) {
    var options = {
      host: 'api.thequeue.org',
      port: 80,
      path: '/v1/clear?format=json&url=' + url
    };

    http.get(options, function(res) {
      var data = '';
      
      res.setEncoding('utf8');
      res.on('data', function (chuck) {
        data += chuck;
      });

      res.on('end', function() {
        var obj = JSON.parse(data);
        if (obj.item.description.length == 0) console.log(url);
      });
    }).on('error', function(e) {
      console.log("Got error: " + e.message);
    });
  });

}

Array.prototype.unique = function() {
  var a = [];
  var l = this.length;
  for(var i=0; i<l; i++) {
    for(var j=i+1; j<l; j++) {
      // If this[i] is found later in the array
      if (this[i] === this[j]) j = ++i;
    }
    a.push(this[i]);
  }
  return a;
};
