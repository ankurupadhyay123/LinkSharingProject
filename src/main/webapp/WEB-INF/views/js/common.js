
$(document).ready (function(){
    $(function () {
        $("#tagName, #headersearchtag, #invitationservicetagName, #docTagName").autocomplete({
            source:function (request, response) {
                $.ajax({
                    url:"/getAllTags",
                    type:"POST",
                    data:{ term:request.term},
                    dataType:"json",

                    success:function (data) {
                        console.log(data);
                        response($.map(data, function (v) {
                            return{
                                label:v,
                                value:v
                            };
                        }));
                    },
                    error:function (r) {
                        console.log(r);
                    }
                });
            }
        });
    });
});

$(document).ready (function(){
    $(function () {
        $("#dashBoardHeaderSearchTag").autocomplete({
            source:function (request, response) {
                $.ajax({
                    url:"/getAllPublicTags",
                    type:"POST",
                    data:{ term:request.term},
                    dataType:"json",

                    success:function (data) {
                        console.log(data);
                        response($.map(data, function (v) {
                            return{
                                label:v,
                                value:v
                            };
                        }));
                    },
                    error:function (r) {
                        console.log(r);
                    }
                });
            }
        });
    });
});
