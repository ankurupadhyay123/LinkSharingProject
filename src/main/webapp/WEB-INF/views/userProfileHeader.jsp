<%--
  Created by IntelliJ IDEA.
  User: ankur
  Date: 20/7/17
  Time: 12:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="row well" >
    <div>
        <div class="col-md-6 col-sm-6">
            <a class="a1 rowheader" style="text-decoration: none">Link Sharing</a>
        </div>
        <div class="col-md-6 col-sm-6">
            <div class="col-md-4 col-sm-4 searchbox">
                <div class="input-group ">
                    <span class="input-group-addon glyphicon glyphicon-search"></span>
                    <input id="headersearchtag" type="search" class="form-control" placeholder="search"></input>
                    <span class="input-group-addon glyphicon glyphicon-remove-sign"></span>
                </div>
            </div>
            <div class="col-md-5 col-sm-4" style="margin-left: 25px;padding-right: 0">
                <a href="#" class="pull-right"><img class="media-object" src="getphoto" style="background-size: 100% 100%;  width:37px; height: 37px"></a>
            </div>
            <div class="col-md-2 col-sm-4 pull-right" style="padding: 0;margin-right: 0">
                    <span class="dropdown" style="float: right">
                        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown"><%=user.getUserName()%>
                        <span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li><a href="logOut">Logout</a></li>
                            </ul>
                    </span>
            </div>
        </div>
    </div>
</div>

</body>
</html>
