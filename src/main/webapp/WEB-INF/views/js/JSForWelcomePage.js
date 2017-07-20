var request;
function sendUserNameInfo()
{
    var v = document.getElementById("username").value;
    var url="/checkunique?val="+v;

    if(window.XMLHttpRequest){
        request=new XMLHttpRequest();
    }
    else if(window.ActiveXObject){
        request=new ActiveXObject("Microsoft.XMLHTTP");
    }
    try{
        request.onreadystatechange=getUserNameInfo;
        request.open("POST",url,true);
        request.send();
    }catch(e){alert("Unable to connect to server");}
}

function getUserNameInfo(){
    if(request.readyState==4){
        var val=request.responseText;
        document.getElementById('usernameAvailability').innerHTML=val;
    }
}

function sendEmailInfo()
{
    var v = document.getElementById("email").value;
    var url="/checkuniqueemail?val="+v;

    if(window.XMLHttpRequest){
        request=new XMLHttpRequest();
    }
    else if(window.ActiveXObject){
        request=new ActiveXObject("Microsoft.XMLHTTP");
    }

    try{
        request.onreadystatechange=getEmailInfo;
        request.open("POST",url,true);
        request.send();
    }catch(e){alert("Unable to connect to server");}
}

function getEmailInfo(){
    if(request.readyState==4){
        var val=request.responseText;
        document.getElementById('emailAvailability').innerHTML=val;
    }
}

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
jQuery(document).on("blur", "#username", sendUserNameInfo());
