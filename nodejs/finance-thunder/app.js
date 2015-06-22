//dependencies
var express = require('express'),
    i18n = require("i18n"),
    mongoStore = require('connect-mongo')(express),
    http = require('http'),
    path = require('path'),
    passport = require('passport'),
    mongoose = require('mongoose');

//create express app
var app = express();

//mongo uri
app.set('mongodb-uri', process.env.MONGOLAB_URI || process.env.MONGOHQ_URL || 'localhost/finance-thunder');

//setup mongoose
app.db = mongoose.createConnection(app.get('mongodb-uri'));
app.db.on('error', console.error.bind(console, 'mongoose connection error: '));
app.db.once('open', function () {
    console.log('mongoose open for finance thunder');
});

//config data models
require('./models')(app, mongoose);

//config i18n
i18n.configure({
    locales:['en', 'zh'],
    cookie: 'locale',
    directory: __dirname + '/locales',
    updateFiles: false
});

//config all
app.configure(function(){
    //settings
    app.disable('x-powered-by');
    app.set('port', process.env.PORT || 8210);
    app.set('views', __dirname + '/views');
    app.set('view engine', 'jade');
    app.set('strict routing', true);
    app.set('project-name', 'Finance Thunder');
    app.set('company-name', 'hanksudo');
    app.set('admin-email', 'drapho@gmail.com');
    app.set('crypto-key', '@##@TG$##RF');

    //email (smtp) settings
    app.set('email-from-name', app.get('project-name'));
    app.set('email-from-address', 'FinanceThunder@gmail.com');
    app.set('email-credentials', {
        user: 'FinanceThunder@gmail.com',
        password: '******',
        host: 'smtp.gmail.com',
        ssl: true
    });

    //twitter settings
    app.set('twitter-oauth-key', '');
    app.set('twitter-oauth-secret', '');

    //github settings
    app.set('github-oauth-key', '');
    app.set('github-oauth-secret', '');

    //facebook settings
    app.set('facebook-oauth-key', '');
    app.set('facebook-oauth-secret', '');

    //middleware
    app.use(express.favicon(__dirname + '/public/favicon.ico'));
    app.use(express.logger('dev'));
    app.use(express.static(path.join(__dirname, 'public')));
    app.use(express.bodyParser());
    app.use(express.methodOverride());
    app.use(express.cookieParser('F@#G#$H'));
    app.use(express.session({
        secret: 'EFh8g32fG',
        store: new mongoStore({ url: app.get('mongodb-uri') })
    }));
    app.use(i18n.init);
    app.use(passport.initialize());
    app.use(passport.session());
    app.use(app.router);

    //error handler
    app.use(require('./views/http/index').http500);

    //locals
    app.locals.projectName = app.get('project-name');
    app.locals.copyrightYear = new Date().getFullYear();
    app.locals.copyrightName = app.get('company-name');
});

//config dev
app.configure('development', function(){
    app.use(express.errorHandler());
});

//config passport
require('./passport')(app, passport);

//route requests
require('./routes')(app, passport);

//utilities
require('./utilities')(app);

//listen up
http.createServer(app).listen(app.get('port'), function(){
  console.log('express server listening on port ' + app.get('port'));
});
