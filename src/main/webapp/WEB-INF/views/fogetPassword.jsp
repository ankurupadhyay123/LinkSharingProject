<%--
  Created by IntelliJ IDEA.
  User: ankur
  Date: 13/7/17
  Time: 9:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <title>Title</title>

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/boo
		tstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/welcomepage.css"/>
    <script>
/*
        $("#sendOTP").click(function () {
           $.ajax(({
               url:"sendOTP",
               data:{email:$("#email_id").val()},
               type:"post",
               success: function (r) {
                   if (r === "true"){
                       alert("OTP sent to \n Please use it to reset password");
                   }
               },
               error:function (e) {
                   console.log(e);
               }
           }));
        });

*/

    $(document).ready(function () {
      // $("#passwordEntryform").s();
        /*$("#emailform").onsubmit(function () {
            $("#emailform").hide();
        });
        */$("#passwordEntryform").show();
    });
/*function myfunction() {
    var x = document.getElementById('myspan');
    if (x.style.display === 'none') {
        x.style.display = 'block';
    } else {
        x.style.display = 'none';
    }

}*/
    </script>
</head>
<body class="body" style="background:url('${pageContext.request.contextPath}/resources/assets/backgroundImage.jpg');">

<form id="emailform" class="form-inline" method="post" action="/sendOTP">

    <div class="form-group">
        <label for="email_id" class="control-label">Email</label>
        <input type="email" class="form-control" id="email_id" name="email_name" placeholder="name@domain.com">
    </div>

    <div class="form-group">
        <input id="#sendOTP" type="submit" form="emailform" class="btn btn-primary">Submit</input>
    </div>
</form>

<form id="passwordEntryform" class="form-inline" action="/forgotpasswordform" method="post">
    <div class="form-group">
        <label  class="control-label">email</label>
        <input type="email" class="form-control" name="email" placeholder="enter email">
    </div>
    <div class="form-group">
        <label  class="control-label">OTP</label>
        <input type="text" class="form-control" name="otp" placeholder="enter otp">
    </div>
    <div class="form-group">
        <label  class="control-label">password</label>
        <input type="password" class="form-control"name="password" placeholder="enter password again">
    </div>
    <div class="form-group">
        <label  class="control-label">confirm password</label>
        <input type="password" class="form-control"name="confirm_password" placeholder="enter password again">
    </div>

    <div class="form-group">
        <input type="submit" form="passwordEntryform" class="btn btn-primary">Submit</input>
    </div>
</form>
</body>
</html>
