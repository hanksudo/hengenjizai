var mongoose = require('mongoose');
var request = require('request');
var cheerio = require('cheerio');
var goldSchema = require('../schema/GoldSchema').schema;

console.log('Run : ' + new Date());
//setup mongoose
mongoose.connect('mongodb://localhost/finance-thunder');
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function callback () {
    // console.log('mongoose open for finance thunder');
});

request({
    uri: 'http://rate.bot.com.tw/Pages/Static/UIP005.zh-TW.htm',
    encoding: 'binary',
    followRedirect: false
}, function(err, res, body) {
    if (err || res.statusCode != 200) {
        console.log('Error: '+err+' / Status Code: '+res.statusCode);
    } else {
        try {
            $ = cheerio.load(body);
            var data = $('#GoldBankBookForTWD .decimal:nth-last-child(1)');

            var sell = data.eq(0).text();
            var buy = data.eq(1).text();

            console.log('Sell = ' + sell);
            console.log('Buy = ' + buy);

            var Gold = mongoose.model('Gold', goldSchema);
            var gold = new Gold({
                type: 'twd',
                buy: buy,
                sell: sell
            });

            gold.save(function (err, gold) {
                if (err) console.log('data save failed!');
                else console.log('Save gold security.');

                process.exit(1);
            });
        } catch (e) {
            console.log(e);
            process.exit(1);
        }
    }
});
