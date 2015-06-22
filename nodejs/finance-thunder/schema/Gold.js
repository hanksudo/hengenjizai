var goldSchema = require('./GoldSchema').schema;

exports = module.exports = function(app, mongoose) {
    app.db.model('Gold', goldSchema);
};
