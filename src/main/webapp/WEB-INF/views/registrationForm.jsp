<%--
  Created by IntelliJ IDEA.
  User: ankur
  Date: 20/7/17
  Time: 11:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="dynamicDiv">
    <div class="dynamicDivHead">
        <p class="phead">Registration</p>
    </div>
    <form class="form-horizontal" style="padding:10px;" enctype="multipart/form-data" action="/register" method="post">
        <div class="form-group">
            <label class="control-label col-sm-4" for="firstname">First Name:</label>
            <div class="col-sm-8">
                <input type="text" id="firstname" class="form-control" minlength="5" placeholder="Enter name" name="firstName" required/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-4" for="lastname">Last Name:</label>
            <div class="col-sm-8">
                <input id="lastname" type="text" class="form-control" minlength="5" placeholder="Enter name" name="lastName" required/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-4">Email:</label>
            <div class="col-sm-8">
                <input type="email" onkeyup="sendEmailInfo()" id="email" class="form-control" placeholder="Enter email" name="email" required/>
                <span id="emailAvailability"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-4" for="username">Username:</label>
            <div class="col-sm-8">
                <input type="text" id="username" class="form-control" minlength="5" placeholder="Enter username" name="userName" required/>
                <span id="usernameAvailability"></span>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3" for="password">Password:</label>
            <div class="col-sm-9">
                <input type="password" id="password" class="form-control" minlength="5" placeholder="Enter password" name="password" required/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3" for="confirm_password">Confirm Password:</label>
            <div class="col-sm-9">
                <input type="password" id="confirm_password" class="form-control" minlength="5" placeholder="Enter password" required/>
                <span id="myspan" style="color: red;">Please enter same password</span>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-3">Photo:</label>
            <div class="col-sm-9">
                <input type="file" accept="image/*" class="form-control" name="pho"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-5 pull-right">
                <button type="submit" id="register" class="btn btn-default">Register</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>
