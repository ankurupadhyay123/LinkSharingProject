$(document).ready(function () {
    $("#username").keyup(function () {
        $.ajax({
            url: "/checkUniqueUserName?name=" + $("#username").val(),
            method: "Post",
            success: function (response) {
                if (response == "Username available") {
                    // $("#usernameAvailability").css("color", "green");
                    $("#usernameAvailability").text(response).css("color", "green");
                    $('#register').attr('disabled', false);
                }
                else {
                    // $("#usernameAvailability").css("color", "red");
                    $("#usernameAvailability").text(response).css("color", "red");
                    $('#register').attr('disabled', true);
                }
            }
        })
    });
});

$(document).ready(function () {
    $("#username").keyup(function () {
        $.ajax({
            url: "/checkUniqueEmail?mail=" + $("#email").val(),
            method: "Post",
            success: function (response) {
                if (response == "Username available") {
                    // $("#emailAvailability").css("color", "green");
                    $("#emailAvailability").text(response).css("color", "green");
                    $('#register').attr('disabled', false);
                }
                else {
                    // $("#emailAvailability").css("color", "red");
                    $("#emailAvailability").text(response).css("color", "red");
                    $('#register').attr('disabled', true);
                }
            }
        })
    });
});

jQuery(document).ready(function () {
    jQuery("span#myspan").hide();
    jQuery("p#invalidUserNotify").hide();
});
var passwordMatches = function() {
    var password = jQuery("#password").val();
    var confirmPassword = jQuery("#confirm_password").val();
    console.log(password, confirmPassword, password == confirmPassword);
    if (password == confirmPassword) {
        jQuery("span#myspan").hide();
        return true
    } else {
        jQuery("span#myspan").show();
        return false
    }
};

jQuery(document).on("focus", "#password", passwordMatches);
jQuery(document).on("focus", "#confirm_password", passwordMatches);
jQuery(document).on("change", "#confirm_password", passwordMatches);
jQuery(document).on("blur", "#email", sendEmailInfo);

