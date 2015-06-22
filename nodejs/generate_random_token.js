var crypto = require('crypto');

crypto.randomBytes(48, function(ex, buf) {
  var token = buf.toString('hex');
  console.log(token);
});



var buf = crypto.randomBytes(24);
console.log(buf.toString('hex'));
