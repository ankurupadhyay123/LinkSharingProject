
$(document).ready (function(){
    $(function () {
        $("#tagName, #headersearchtag, #docTagName,#dashBoardHeaderSearchTag").autocomplete({
            source:function (request, response) {
                $.ajax({
                    url:"/getTags",
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
                });
            },
        });
    });
});
