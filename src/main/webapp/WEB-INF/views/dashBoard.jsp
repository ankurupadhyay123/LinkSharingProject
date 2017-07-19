<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.entities.User" %>
<%@ page import="com.entities.Subscription" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css" rel="stylesheet">
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.1/jquery.form.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/welcomepage.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mystyle.css"/>
    <script>
        history.forward();
        jQuery(document).ready(function () {
            jQuery("div#dynamicPopup").hide();
        });

        var showPopup = function() {
                jQuery("div#dynamicPopup").toggle();
        };
        jQuery(document).on("click", ".popupBox", showPopup);

        $(document).ready (function(){
            $(function () {
                $("#tagName, #headersearchtag, #docTagName").autocomplete({
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

/*
        $(document).ready(function () {
            function createDocumentResource() {
//                var fileUrl = $("#documentFile").val();
                var description = $("#documentDescription").val();
                var topic = $("#docTagName").val();
                $.ajax({
                    type: "POST",
                    url: "createDocumentResource",
                    data: {description: description, topic:topic},
                    success: function (response) {
                        console.log("response", response);
                        alert("data saved");
                    },
                    error: function (r) {
                        console.log(r);
                    }
                });
            }
            jQuery(document).on("click", "#submitdocumentform", createDocumentResource);
        });
*/
        $('#documentForm').ajaxForm({

            success: function (msg) {
                alert(msg);
            },
            error: function (msg) {
                alert(msg);
            }
        });


    </script>
    <style>
        span.spanicons{
            margin-right: 5px;
            font-size:20px;
            margin-top:7px;
        }
        .rowheader{
            margin: 0;
        }
        .ui-front{
            z-index: 10000;
        }
    </style>
</head>
<body class="body" style="background:url('${pageContext.request.contextPath}/resources/assets/mybag.jpg');">
<%! static int flag;%>
<%
    User user = (User) session.getAttribute("UserDetails");
%>
<div class="container" style="width: 70%">
    <!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Create Topic</h4>
                </div>
                <div class="modal-body">
                    <form method="post" action="javascript:void(0)">
                        <div class="form-group">
                            <label>Topic Name</label>
                            <input type="text" id="topicName" class="form-control" name="topicName" aria-describedby="emailHelp" placeholder="Enter topic name" required>
                        </div>
                        <div class="form-group">
                            <label>Topic</label>
                            <select id="topicvisibility" class="form-control" name="visibility">
                                <option>PUBLIC</option>
                                <option>PRIVATE</option>
                            </select>
                        </div>
                        <button id="submitForm" type="submit" class="btn btn-primary">Create</button>
                        <button type="button" class="btn btn-default pull-right" data-dismiss="modal">Close</button>
                    </form>
                </div>
            </div>

        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="linkModal" role="dialog">
        <div class="ui-front modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Share Link</h4>
                </div>

                <div class="modal-body">
                    <form method="post" action="javascript:void(0)">
                        <div class="form-group">
                            <label>Link*:</label>
                            <input type="text" class="form-control" name="fileUrl" id="urllink" aria-describedby="emailHelp" placeholder="Enter link name" required>
                        </div>
                        <div class="form-group">
                            <label>Description*:</label>
                                <textarea id="description" class="form-control">Description</textarea>
                        </div>
                        <div class="form-group">
                            <label>Topic</label>
                            <input type="search" id="tagName">
                        </div>
                        <button id="submitlinkform" type="submit" class="btn btn-primary">Share</button>
                        <button type="button" class="btn btn-default pull-right" data-dismiss="modal">Close</button>
                    </form>
                </div>
            </div>

        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="documentModal" role="dialog">
        <div class="ui-front modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Share Document</h4>
                </div>

                <div class="modal-body">
                    <form method="post" id="documentForm" enctype="multipart/form-data" action="/createDocumentResource">
                        <div class="form-group">
                            <label>Document*:</label>
                            <input type="file" class="form-control" id="documentFile" name="fileUrl" placeholder="Browse file" required>
                        </div>
                        <div class="form-group">
                            <label>Description*:</label>
                            <textarea id="documentDescription" name="description" class="form-control" placeholder="Description"></textarea>
                        </div>
                        <div class="form-group">
                            <label>Topic</label>
                            <input type="search" name="topic"id="docTagName">
                        </div>
                        <button id="submitdocumentform" type="submit" class="btn btn-primary">Share</button>
                        <button type="button" class="btn btn-default pull-right" data-dismiss="modal">Close</button>
                    </form>
                </div>
            </div>

        </div>
    </div>


    <div class="row well" >
        <div>
            <div class="col-md-5 col-sm-4">
                <a class="a1 rowheader" style="text-decoration: none">Link Sharing</a>
            </div>
            <div class="col-md-7 col-sm-7">
                <div class="col-md-4 col-sm-4 searchbox">
                    <div class="input-group ">
                        <span class="input-group-addon glyphicon glyphicon-search"></span>
                        <input id="headersearchtag" type="search" class="form-control" placeholder="search"></input>
                        <span class="input-group-addon glyphicon glyphicon-remove-sign"></span>
                    </div>
                </div>
                <div class="col-md-3 col-sm-3" style="margin-right: 20px">
                    <a href="#" data-toggle="modal" data-target="#myModal">
                        <span class="glyphicon glyphicon-comment spanicons"></span>
                    </a>
                    <a href="#"><span class="glyphicon glyphicon-envelope spanicons"></span></a>
                    <a href="#" data-toggle="modal" data-target="#linkModal">
                        <span class="glyphicon glyphicon-link spanicons"></span>
                    </a>
                    <a href="#" data-toggle="modal" data-target="#documentModal"><span class="glyphicon glyphicon-copy spanicons"></span></a>
                </div>
                <div class="col-md-4 col-sm-4 pull-right" style="padding: 0;style="margin-right: 0">
                    <div class="col-md-4 col-sm-4"></div>
                    <div class="col-md-3 col-sm-3">
                        <a href="#"><span class="glyphicon glyphicon-user spanicons"></span></a>
                    </div>
                    <div class="col-md-5 col-sm-5">
                        <span class="dropdown" style="float: right">
                            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"><%=user.getUserName()%>
                                <span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li><a href="#">HTML</a></li>
                                <li><a href="#">CSS</a></li>
                                <li><a href="#">JavaScript</a></li>
                            </ul>
                        </span>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <div id="division1" class="col-md-5 col-sm-5">
        <div style="padding-left: 0;">
            <div class="dynamicDiv" style="margin-bottom:15px;">
                <% //while (blogIterator.hasNext()){ Blog userblog = blogIterator.next();%>
                <div style="margin-top:10px;margin-left:10px;margin-right:10px;margin-bottom:2px;">
                    <div class="media">
                        <div class="media-left">
                            <img class="media-object" src="getphoto" style="background-size: 100% 100%;  width:90px; height: 90px">
                        </div>
                        <div class="media-body" >
                            <h4 class="media-heading" id="fullNameOfUser" style="margin-right: 5px;margin-bottom: 0px"><%=user.getFirstName()+" "+user.getLastName()%></h4>
                            <small id="timeStampOfBlog" style="margin-top:0px "><%= "@"+user.getUserName()%></small>
                            <p id="userblogData" style="font-size: 15px"><%//=userblog.getBlogdata()%></p>
                            <div id="profileDivision">
                                <div id="subprofileDivision1" class="col-md-6 col-sm-10" style="float: left;margin-bottom: 0px;margin-left: 0">
                                    <p style="margin-bottom: 0">Subscriptions</p>
                                    <a href="#">${subscriptionCount}</a>
                                </div>
                                <div id="subprofileDivision2" class="col-md-6 col-sm-10" style="float: left;margin-bottom: 0px;margin-left: 0">
                                    <p style="margin-bottom: 0">jijdskllkfdjs</p>
                                    <p>jkldjsjfkldsfj</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div style="padding-left: 0;">
            <div class="dynamicDiv" style="margin-bottom:15px;">
                <div class="dynamicDivHead">
                    <span class="phead">Subscription</span>
                    <a style="color: #55ACEE" class="pull-right">View All</a>
                </div>
                <% //while (blogIterator.hasNext()){ Blog userblog = blogIterator.next();%>
                <div style="margin-top:10px;margin-left:10px;margin-right:5px;margin-bottom:10px;">
                    <div class="media">
                        <div class="media-left">
                            <img class="media-object" src="getphoto" style="background-size: 100% 100%;  width:70px; height: 70px">
                        </div>
                        <div class="media-body" >
                            <small style="margin-top:0px ">topic name</small>
                            <div>
                                <div class="col-md-4 col-sm-10" style="float: left;margin-bottom: 0px;margin-left: 0">
                                    <p style="margin-bottom: 0">@uday</p>
                                    <p><a>Unsubscribe</a></p>
                                </div>
                                <div class="col-md-4 col-sm-10" style="float: left;margin-bottom: 0px;margin-left: 0">
                                    <p style="margin-bottom: 0">Subscriptions</p>
                                    <p>50</p>
                                </div>
                                <div class="col-md-4 col-sm-10" style="float: left;margin-bottom: 0px;margin-left: 0">
                                    <p style="margin-bottom: 0">Post</p>
                                    <p>30</p>
                                </div>
                            </div>
                            <div style="margin-left: 10px">
                                <select class="col-md-4 selectpicker">
                                    <option>Mustard</option>
                                    <option>Ketchup</option>
                                    <option>Relish</option>
                                </select>
                                <select class="col-md-4 selectpicker">
                                    <option>Mustard</option>
                                    <option>Ketchup</option>
                                    <option>Relish</option>
                                </select>
                                <div class="col-md-4">
                                    <a href="#"><span class="popupBox glyphicon glyphicon-envelope"></span></a>
                                    <a href="#"><span class="glyphicon glyphicon-pencil"></span></a>
                                    <a href="#"><span class="glyphicon glyphicon-trash"></span></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div style="padding-left: 0;">
            <div class="dynamicDiv" style="margin-bottom:15px;">
                <div class="dynamicDivHead">
                    <span class="phead">Trending Topics</span>
                </div>
                <% //while (blogIterator.hasNext()){ Blog userblog = blogIterator.next();%>
                <div style="margin-top:10px;margin-left:10px;margin-right:5px;margin-bottom:10px;">
                    <div class="media">
                        <div class="media-left">
                            <img class="media-object" src="getphoto" style="background-size: 100% 100%;  width:70px; height: 70px">
                        </div>
                        <div class="media-body" >
                            <small style="margin-top:0px ">topic name</small>
                            <div>
                                <div class="col-md-4 col-sm-10" style="float: left;margin-bottom: 0px;margin-left: 0">
                                    <p style="margin-bottom: 0">@uday</p>
                                    <p><a>Unsubscribe</a></p>
                                </div>
                                <div class="col-md-4 col-sm-10" style="float: left;margin-bottom: 0px;margin-left: 0">
                                    <p style="margin-bottom: 0">Subscriptions</p>
                                    <p>50</p>
                                </div>
                                <div class="col-md-4 col-sm-10" style="float: left;margin-bottom: 0px;margin-left: 0">
                                    <p style="margin-bottom: 0">Post</p>
                                    <p>30</p>
                                </div>
                            </div>
                            <div style="margin-left: 10px">
                                <select class="col-md-4 selectpicker">
                                    <option>Mustard</option>
                                    <option>Ketchup</option>
                                    <option>Relish</option>
                                </select>
                                <select class="col-md-4 selectpicker">
                                    <option>Mustard</option>
                                    <option>Ketchup</option>
                                    <option>Relish</option>
                                </select>
                                <div class="col-md-4">
                                    <a href="#"><span class="popupBox glyphicon glyphicon-envelope"></span></a>
                                    <a href="#"><span class="glyphicon glyphicon-pencil"></span></a>
                                    <a href="#"><span class="glyphicon glyphicon-trash"></span></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <div id="dynamicPopup" style="padding-left: 0;">
        <div class="dynamicDiv" style="margin-bottom:15px;">
            <div class="dynamicDivHead">
                <span class="phead">Send Invitation (Pop-up)</span>
            </div>
            <% //while (blogIterator.hasNext()){ Blog userblog = blogIterator.next();%>
            <form class="form-horizontal" style="margin-top:10px;margin-left:10px;margin-right:5px;margin-bottom:10px;">
                <div class="form-group">
                    <label class="control-label col-sm-4">Email*:</label>
                    <div class="col-sm-8">
                        <input type="email" class="form-control" placeholder="Enter Email" name="email" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-4">Topic*:</label>
                    <div class="col-sm-8">
                        <select class="form-control" id="exampleSelect1">
                            <option>Topic</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">

                </div>
            </form>
        </div>
    </div>


    </div>



    <div id="division2" class="col-md-7 col-sm-7">
        <div style="padding-right: 0;">
            <div class="dynamicDiv" style="margin-bottom:15px;">
                <div class="dynamicDivHead">
                    <p class="phead">Inbox</p>
                </div>
<%--                <div name="userprofileBlogs" style="padding: 7px">

                </div>--%>
                <div style="margin-top:10px;margin-left:10px;margin-right:10px;margin-bottom:2px;">
                    <div class="media">
                        <div class="media-left">
                            <img class="media-object" src="getphoto" style="background-size: 100% 100%;  width:90px; height: 90px">
                        </div>
                        <div class="media-body" >
                            <h4 class="media-heading" id="fullNameOfUser" style="margin-right: 5px;margin-bottom: 0px"><%=user.getFirstName()+" "+user.getLastName()%></h4>
                            <small style="margin-top:0px "><%= "@"+user.getUserName()%></small>
                            <p  style="font-size: 15px"><%//=userblog.getBlogdata()%></p>
                            <div >
                                <div class="col-md-6 col-sm-10" style="float: left;margin-bottom: 0px;margin-left: 0">
                                    <p style="margin-bottom: 0">Subscriptions</p>
                                    <a href="#">${subscriptionCount}</a>
                                </div>
                                <div class="col-md-6 col-sm-10" style="float: left;margin-bottom: 0px;margin-left: 0">
                                    <p style="margin-bottom: 0">jijdskllkfdjs</p>
                                    <p>jkldjsjfkldsfj</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<%--<form action="/getTags" method="post"></form>Search:<input type="text" id="tagName"></input>--%>

</body>
</html>
