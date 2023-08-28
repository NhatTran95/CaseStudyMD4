$("#register").hide();
$("#signup-box-link").click(function(){
    $("#login").fadeOut(100);
    $("#register").delay(100).fadeIn(100);
    $("#login-box-link").removeClass("active white-btn");
    $("#signup-box-link").addClass("active white-btn");
});
$("#login-box-link").click(function(){
    $("#login").delay(100).fadeIn(100);;
    $("#register").fadeOut(100);
    $("#login-box-link").addClass("active white-btn");
    $("#signup-box-link").removeClass("active white-btn");

});

