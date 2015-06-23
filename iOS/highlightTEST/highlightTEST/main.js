$(document).ready(function() {
});

function highlight(quote) {
    $('body').highlight(quote, {className: '_BM-yours _BM-highlight'});
}

function bindEvent() {
    $('._BM-highlight').on('touchstart', function(e) {                   
                           window.location = "call?touchstart";
                           pressTimer = window.setTimeout(function() {
                                                          window.location = "call?longpress";
                                                          }, 600);
                           });
    
    $('._BM-highlight').on('touchend', function(e) {
                           window.location = "call?touchend";
                           clearTimeout(pressTimer);
                           });
}