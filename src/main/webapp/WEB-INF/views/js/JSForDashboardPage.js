jQuery(document).ready(function () {
    jQuery("div#dynamicPopup").hide();
});

var showPopup = function() {
    jQuery("div#dynamicPopup").toggle();
};
jQuery(document).on("click", ".popupBox", showPopup);

$(document).ready(function () {
    function createTopic() {
        var name = $("#topicName").val();
        var visibility = $("#topicvisibility").val();
        $.ajax({
            type: "POST",
            url: "createTitle",
            data: {topicName: name, visibility: visibility},
            success: function (response) {
                console.log("response", response);
                alert("data saved");
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
                console.log("response", response);
                alert("data saved");
            },
            error: function (r) {
                console.log(r);
            }
        });
    }
    jQuery(document).on("click", "#submitlinkform", createLinkResource);
});