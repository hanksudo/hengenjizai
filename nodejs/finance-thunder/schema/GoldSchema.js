var mongoose = require('mongoose');

exports.schema = new mongoose.Schema({
    buy: { type: Number, default: 0 },
    sell: { type: Number, default: 0 },
    type: { type: String, default: ''},
    date: { type: Date, default: Date.now }
});
