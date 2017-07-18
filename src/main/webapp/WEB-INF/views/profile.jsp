<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.entities.User" %>
<%@ page import="com.entities.Subscription" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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


        //alert (dataString);return false;
        $(document).ready(function () {
            function  createTopic() {
                alert("herere");
                var name = $("#topicName").val();
                var visibility = $("#topicvisibility").val();
                var dataString = 'name='+ name + '&visibility=' + visibility;
                $.ajax({
                    type: "POST",
                    url: "/createTitle",
                    data: dataString,
                    contentType : "application/json",
                    dataType : 'json',
                    success: function(response) {
                        console.log("response" ,response);
                        alert("data saved");
                    }
                });
            }
            jQuery(document).on("click","#submitForm",createTopic);
        });
    </script>
</head>
<body class="body" style="background:url('${pageContext.request.contextPath}/resources/assets/backgroundImage.jpg');">
<%! static int flag;%>
<%
    User user = (User) session.getAttribute("UserDetails");
    Subscription subscription = new Subscription();
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
                            <label>Visibility</label>
                            <select id="topicvisibility" class="form-control" name="visibility">
                                <option>PUBLIC</option>
                                <option>PRIVATE</option>
                            </select>
                        </div>
                        <button id="submitForm" type="submit" class="btn btn-primary">Submit</button>
                        <button type="button" class="btn btn-default pull-right" data-dismiss="modal">Close</button>
                    </form>
                </div>
            </div>

        </div>
    </div>

<%--
    <script type="text/javascript"> window.onload = alertName; </script>

    <script type="text/javascript">
        var Msg ='<%=request.getAttribute("error")%>';
        if (Msg == "alert") {
            function alertName(){
                alert("Topic with same name exist... Try another!!!");
            }
        }
    </script>
--%>

    <div class="row well" >
        <div>
            <a class="pull-left a1" style="text-decoration: none">Link Sharing</a>
            <div class="col-md-2 col-sm-3 searchbox">
                <div class="input-group ">
                    <span class="input-group-addon glyphicon glyphicon-search"></span>
                    <input type="search" class="form-control" placeholder="search"></input>
                    <span class="input-group-addon glyphicon glyphicon-remove-sign"></span>
                </div>
            </div>
            <a href="#" class="pull-right" data-toggle="modal" data-target="#myModal">
                <span class="glyphicon glyphicon-comment" style="font-size:20px; margin-top:7px;"></span>
            </a>
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
                <div name="userprofileBlogs" style="padding: 7px">

                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>
