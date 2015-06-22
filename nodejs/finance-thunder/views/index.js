exports.init = function(req, res){
    // res.setLocale('zh');
    req.app.db.models.Gold.findOne().sort({date: -1}).exec(function(err, gold) {
        res.render('index', {
            goldBuy: gold.buy,
            goldSell: gold.sell,
            oauthFacebook: !!req.app.get('facebook-oauth-key')
        });
    });
};
