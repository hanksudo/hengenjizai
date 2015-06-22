var request = require('request');
var async = require('async');
var _ = require('underscore');
var assert = require('assert');


var urls = {
    "http://t.co/37JSViHnaC": "http://blog.evernote.com/tech/2013/03/07/new-javascript-sdk-for-evernote-developers/",
    "http://t.cn/zYDf5xY": "http://www.kuailiyu.com/article/2000.html",
    "http://bit.ly/ZtWrRG": "http://techorange.com/2013/03/20/what-your-facebook-likes-say-about-you/?utm_source=twitterfeed&utm_medium=twitter&utm_campaign=Feed%3A+techorange+%28TechOrange%29",
    "http://bit.ly/14fvCWs": "http://linkclarity.com/",
    //"http://bit.ly/ZCwzmS": "http://playsc.com/forum/data/attachment/forum/201303/18/2314092xzyxsr3s3xzp212.jpg", // many redirect
};


// async.forEach([0,1,2,3,4,5,6,7,8,9], function(item, callback) {
//   setTimeout(function() {
//     console.log('>', item);
//     callback();
//   }, 2 * Math.random() * 1000);
// }, function(err) {
//   console.log('> done');
// });

// console.log(':)');

// describe('Array', function(){
//   describe('#indexOf()', function(){
//     it('should return -1 when the value is not present', function(){
//       assert.equal(-1, [1,2,3].indexOf(5));
//       assert.equal(-1, [1,2,3].indexOf(0));
//     })
//   })
// })


describe("Expander Test", function() {
    it('$Test url result\n', function(done) {
        this.timeout(60*1000);
        async.each(_.keys(urls), function(url, callback) {
            expandUrl(url, function(expandedUrl) {
                assert.equal(urls[url], expandedUrl);
                callback();
            });
        }, function(err){
            done();
        });
    });
});


function expandUrl(url, callback) {
    console.log('Expanding url : '+url);
    request({
        method: "HEAD",
        url: url,
        followAllRedirects: true
    }, function(err, res) {
        if (err) {
            callback(err);
        } else {
            var expandedUrl = res.request.href;
            console.log('Expanded Url : ' + expandedUrl);
            callback(expandedUrl);
        }
    });
}
