var mongoose = require('mongoose')
  , Schema = mongoose.Schema;

// User info
var User = new Schema({
    user        : { type: String, required: true, index: { unique: true } }
  , name        : {
        first   : String
      , last    : String
    }
  , salt        : String
  , password    : String
  , token       : String
  , email       : { type: String, required: true, index: { unique: true } }
  , desc        : String
  , date        : { type: Date, default: Date.now }
  , star        : Number
  , lang        : String
  , geo         : [String]
});

// for social network (Facebook, Twitter...etc)
var social = new Schema({
    uid         : ObjectId
  , type        : String
  , id          : String
  , name        : String
  , token       : String
  , active      : Boolean
});

// Item info
var Item = new Schema({
    images      : [Image]
  , geo         : [String]
  , desc        : String
  , tag         : [Tag]
  , star        : Number
  , price       : Number
  , money       : String
  , date        : { type: Date, default: Date.now }
});

// Image info
var Image = new Schema({
    iid         : ObjectId
  , img         : String
  , size        : String
  , date        : Date
});

// Chat between two users
var Message = new Schema({
    from        : ObjectId
  , to          : ObjectId
  , sendTime    : Date
  , revTime     : Date
  , msg         : String
  , file        : [File]
  , geo         : [String]
});

// Settings for alert "someone follow you", "send newsletter to me", "someone star you"
var Alert = new Schema({
    uid          : ObjectId
  , follow       : Boolean
  , newsletter   : Boolean
  , Star         : Boolean
});

// for iOS Apple push notification
var APN = new Schema({
    uid         : ObjectId
  , token       : String
  , dev         : String
  , date        : { type: Date, default: Date.now }
});

// Mail notificaton to User (Optional)
var mail_notify = new Schema({
    from         : ObjectId
  , to           : ObjectId
  , type         : String
  , date         : { type: Date, default: Date.now }
});