$(document).ready(function() {
                  alert("123");
    foo();
});

function foo() {
    jQuery("#header").animate({opacity: 0}, {duration: 3000}).animate({opacity: 1.0}, {duration: 3000})
    .animate({opacity: 0}, {duration: 3000})
    .animate({opacity: 1.0}, {duration: 3000, complete: foo})
}