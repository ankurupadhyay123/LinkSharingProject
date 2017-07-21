<%@ page import="java.util.Iterator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Link Sharing</title>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/JSForWelcomePage.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js">
	</script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/common.css"/>
	<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.2.1/jquery.form.min.js"></script>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css" rel="stylesheet">
    <script type="text/javascript">
        history.forward();
	</script>
</head>

<body class="body" style="background:url('${pageContext.request.contextPath}/resources/assets/mybag.jpg');">
	<div class="container" >
<%@include file="homePageHeader.jsp"%>
	<div id="division1" class="col-md-8 col-sm-8">
		<div style="padding-left: 0;">
			<div class="dynamicDiv" style="margin-bottom:15px;">
				<div class="dynamicDivHead">
					<p class="phead">Recent Shares</p>
				</div>
				<div style="margin:10px;">
					<c:forEach items="${recentResourceList}" var="item">
						<div class="media">
							<div class="media-left">
								<img src="imageFetch?email=${item.createdBy.email}" style="background-size: 100% 100%;  width:90px; height: 90px">
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

	<div id="division2" class="col-md-4 col-sm-4">
		<div style="padding-right: 0;">
			<div class="dynamicDiv" style="margin-bottom:15px;">
				<div class="dynamicDivHead">
					<p class="phead">Login</p>
				</div>
				<form class="form-horizontal" id="loginForm" style="padding:10px;" action="/login" method="post">
			    		<div class="form-group">
				      		<label class="control-label col-sm-4">Username:</label>
				      		<div class="col-sm-8">
							<input type="text" class="form-control" placeholder="Enter username" name="uname" required>
			      			</div>
			    		</div>
				    	<div class="form-group">
					      <label class="control-label col-sm-4">Password:</label>
					      <div class="col-sm-8">
							<input type="password" class="form-control" placeholder="Enter password" name="pass" required>
					      </div>
				    	</div>
				    	<div class="form-group">
						<a style="text-decoration:underline;" class="control-label col-sm-5" href="/forgotpassword">Forgot Password</a>
						<div class="col-sm-5 pull-right">
							<button type="submit" class="btn btn-default" form="loginForm">Login</button>
				      		</div>
				      	</div>
				</form>
			</div>
		</div>
		<div style="padding-right: 0;">
			<%@include file="registrationForm.jsp"%>
		</div>

	</div>

	</div>
</body>

</html>
