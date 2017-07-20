<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.entities.User" %>
<%@ page import="com.entities.Subscription" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/JSForDashboardPage.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css" rel="stylesheet">
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.1/jquery.form.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dashboard.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css"/>
    <script>
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

    </style>
</head>
<body class="body" style="background:url('${pageContext.request.contextPath}/resources/assets/mybag.jpg');">
<%! static int flag;%>
<%
    User user = (User) session.getAttribute("UserDetails");
%>
<div class="container" style="width: 70%">
    <%@include file="createTopic.jsp"%>
    <%@include file="shareLink.jsp" %>
    <%@include file="shareDocument.jsp"%>
    <%@include file="dashBoardHeader.jsp"%>

    <div id="division1" class="col-md-5 col-sm-5">
        <div style="padding-left: 0;">
            <%@include file="userData.jsp"%>
        </div>

        <div style="padding-left: 0;">
            <div class="dynamicDiv" style="margin-bottom:15px;">
                <div class="dynamicDivHead">
                    <span class="phead">Subscriptions</span>
                    <a style="color: #55ACEE" class="pull-right">View All</a>
                </div>
                <% //while (blogIterator.hasNext()){ Blog userblog = blogIterator.next();%>
                <div style="margin-top:10px;margin-left:10px;margin-right:5px;margin-bottom:10px;">
                    <c:forEach items="${subscribedTopicsList}" var="item">
                        <div class="media">
                            <div class="media-left">
                                <img class="media-object" src="getphoto" style="background-size: 100% 100%;  width:70px; height: 70px">
                            </div>
                            <div class="media-body" >
                                <small style="margin-top:0px ">${item.name}</small>
                                <div>
                                    <div class="col-md-4 col-sm-10" style="float: left;margin-bottom: 0px;margin-left: 0">
                                        <p style="margin-bottom: 0">@${item.createdBy.userName}</p>
                                        <p><a>Unsubscribe</a></p>
                                    </div>
                                    <div class="col-md-4 col-sm-10" style="float: left;margin-bottom: 0px;margin-left: 0">
                                        <a href="#" style="margin-bottom: 0">Subscriptions</a>
                                        <p>${subscriptionCount}</p>
                                    </div>
                                    <div class="col-md-4 col-sm-10" style="float: left;margin-bottom: 0px;margin-left: 0">
                                        <p style="margin-bottom: 0">Post</p>
                                        <p>30</p>
                                    </div>
                                </div>
                                <div style="margin-left: 10px">
                                    <select id="subscriptionSeriousness" name="subscriptionSeriousness" class="col-md-4 selectpicker">
                                        <option class="selectpickerOptions">SERIOUS</option>
                                        <option class="selectpickerOptions">CASUAL</option>
                                        <option class="selectpickerOptions">VERY_SERIOUS</option>
                                    </select>
                                    <select id="topicVisibility" name="topicVisibility" style="margin-left: 15px;width: 80px" class="col-md-4 selectpicker">
                                        <option class="selectpickerOptions">PUBLIC</option>
                                        <option class="selectpickerOptions">PRIVATE</option>
                                    </select>
                                    <div class="col-md-4">
                                        <a href="#"><span class="popupBox glyphicon glyphicon-envelope"></span></a>
                                        <a href="#"><span class="glyphicon glyphicon-pencil"></span></a>
                                        <a href="#"><span class="glyphicon glyphicon-trash"></span></a>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </c:forEach>
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
                                <select class="col-md-4 selectpicker" style="margin-left: 15px;width: 80px">
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
    </div>

    <div id="division2" class="col-md-7 col-sm-7">
        <div style="padding-right: 0;">
            <div class="dynamicDiv" style="margin-bottom:15px;">
                <div class="dynamicDivHead">
                    <p class="phead">Inbox</p>
                </div>
<%--                <div name="userprofileBlogs" style="padding: 7px">

                </div>--%>
                <div style="margin:10px;">
                    <c:forEach items="${inboxResourceList}" var="item">
                        <div class="media">
                            <div class="media-left">
                                <img class="media-object" src="getphoto" style="background-size: 100% 100%;  width:90px; height: 90px">
                            </div>
                            <div class="media-body">
                                <div>
                                    <h4 class="media-heading col-md-8 col-sm-8" id="fullNameOfUser" style="padding-left: 0;margin-right: 5px;margin-bottom: 0px">${item.createdBy.firstName} ${item.createdBy.lastName}</h4>
                                    <a class="col-md-3 col-sm-3 pull-right" href="#" style="margin-right: 0">${item.topic.name}</a>
                                </div>
                                    <small style="margin-top:0px ">${item.createdBy.userName}</small>
                                <div style="margin-bottom: 5px">
                                    ${item.description}
                                </div>
                                <div>
                                    <div class="col-md-3 col-sm-3" style="padding-left: 0;padding-right: 0">
                                        <a href="#" class="fa fa-facebook"></a>
                                        <a href="#" class="fa fa-twitter"></a>
                                        <a href="#" class="fa fa-google"></a>
                                    </div>
                                    <div class="col-md-9 col-sm-9 pull-right" style="padding-right: 0;padding-right: 0">
                                        <a class="inboxLinks" href="#" style="margin-left: 20px">Download</a>
                                        <a class="inboxLinks" href="#">View Full Site</a>
                                        <a class="inboxLinks" href="#">Mark As Read</a>
                                        <a class="inboxLinks" href="#">View Post</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

    </div>
</div>

<%--<form action="/getTags" method="post"></form>Search:<input type="text" id="tagName"></input>--%>

</body>
</html>
