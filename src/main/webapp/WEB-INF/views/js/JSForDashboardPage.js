jQuery(document).ready(function () {
    jQuery("div#dynamicPopup").hide();
});

var showPopup = function() {
    jQuery("div#dynamicPopup").toggle();
};
jQuery(document).on("click", ".popupBox", showPopup);

$(document).ready(function () {
    $("#topicName").keyup(function () {
        $.ajax({
            url: "/checkUniqueTopic?topic=" + $("#topicName").val(),
            method: "Post",
            success: function (response) {
                if (response == "Topic with same name exist") {
                    // $("#checkTopicAvailability").css("color", "green");
                    $("#checkTopicAvailability").text(response).css("color", "green");
                    $('#submitForm').attr('disabled', false);
                }
                else {
                    // $("#checkTopicAvailability").css("color", "red");
                    $("#checkTopicAvailability").text(response).css("color", "red");
                    $('#submitForm').attr('disabled', true);
                }
            }
        })
    });
});

$(document).ready(function () {
    function createTopic() {
        var name = $("#topicName").val();
        var visibility = $("#topicvisibility").val();
        $.ajax({
            type: "POST",
            url: "createTitle",
            data: {topicName: name, visibility: visibility},
            success: function (response) {
                if(response){
                    alert("Topic created");
                }else {
                    alert("Topic not created ");
                }
                console.log("response", response);
            },
            error: function (r) {
                console.log(r);
            }
        });
    }
    jQuery(document).on("click", "#submitForm", createTopic);
});

$(document).ready(function () {
    function createLinkResource() {
        var linkUrl = $("#urllink").val();
        var description = $("#description").val();
        var topic = $("#tagName").val();
        $.ajax({
            type: "POST",
            url: "createLinkResource",
            data: {linkUrl: linkUrl, description: description, topic:topic},
            success: function (response) {
                if(response){
                    alert("Link Resource Created");
                }else {
                    alert("Link Resource Not Created");
                }
                console.log("response", response);
            },
            error: function (r) {
                console.log(r);
            }
        });
    }
    jQuery(document).on("click", "#submitlinkform", createLinkResource);
});