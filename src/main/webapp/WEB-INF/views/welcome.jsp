
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Link Sharing</title>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js">
	</script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/welcomepage.css"/>

    <script type="text/javascript">
        var request;
        function sendUserNameInfo()
        {
			var v = document.getElementById("username").value;
            var url="/checkunique?val="+v;

            if(window.XMLHttpRequest){
                request=new XMLHttpRequest();
            }
            else if(window.ActiveXObject){
                request=new ActiveXObject("Microsoft.XMLHTTP");
            }
            try{
                request.onreadystatechange=getUserNameInfo;
                request.open("POST",url,true);
                request.send();
            }catch(e){alert("Unable to connect to server");}
        }

        function getUserNameInfo(){
            if(request.readyState==4){
                var val=request.responseText;
                document.getElementById('usernameAvailability').innerHTML=val;
            }
        }

        function sendEmailInfo()
        {
            var v = document.getElementById("email").value;
            var url="/checkuniqueemail?val="+v;

            if(window.XMLHttpRequest){
                request=new XMLHttpRequest();
            }
            else if(window.ActiveXObject){
                request=new ActiveXObject("Microsoft.XMLHTTP");
            }

            try{
                request.onreadystatechange=getEmailInfo;
                request.open("POST",url,true);
                request.send();
            }catch(e){alert("Unable to connect to server");}
        }

        function getEmailInfo(){
            if(request.readyState==4){
                var val=request.responseText;
                document.getElementById('emailAvailability').innerHTML=val;
            }
        }

        jQuery(document).ready(function () {
           jQuery("span#myspan").hide();
        });
        var passwordMatches = function() {
            var password = jQuery("#password").val();
            var confirmPassword = jQuery("#confirm_password").val();
            console.log(password, confirmPassword, password == confirmPassword);
            if (password == confirmPassword) {
                jQuery("span#myspan").hide();
                return true
            } else {
                jQuery("span#myspan").show();
                return false
            }
        };

        jQuery(document).on("focus", "#password", passwordMatches);
        jQuery(document).on("focus", "#confirm_password", passwordMatches);
        jQuery(document).on("change", "#confirm_password", passwordMatches);
        jQuery(document).on("blur", "#email", sendEmailInfo);
        jQuery(document).on("blur", "#username", sendUserNameInfo());

        history.forward();
	</script>
</head>

<body class="body" style="background:url('${pageContext.request.contextPath}/resources/assets/backgroundImage.jpg');">
<%  //BlogDao blogDao = new BlogDaoImpl();
	//List<Blog> blogs = blogDao.getAll();
	//Iterator<Blog> blogIterator = blogs.iterator();
%>
	<div class="container" >

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
			</div>
		</div>
	<div id="division1" class="col-md-8 col-sm-8">
		<div style="padding-left: 0;">
			<div class="dynamicDiv" style="margin-bottom:15px;">
				<div class="dynamicDivHead">
					<p class="phead">Recent Shares</p>
				</div>

				<% //while (blogIterator.hasNext()){ Blog userblog = blogIterator.next();%>
				<div style="margin:10px;">
					<div class="media">
						<div class="media-left">
							<img class="media-object" style="background:url('${pageContext.request.contextPath}/resources/assets/images.png');background-size: 100% 100%;  width:90px; height: 90px">
						</div>
						<div class="media-body" >
							<h4 class="media-heading" id="userNameOfBlog" style="margin-right: 5px"><%//=userblog.getUserName()%><small id="timeStampOfBlog" style="margin-left:5px "><%//=userblog.getDoc()%></small></h4>
							<p id="userblogData" style="font-size: 15px"><%//=userblog.getBlogdata()%>></p>
							<a href="#" class="fa fa-facebook"></a>
							<a href="#" class="fa fa-twitter"></a>
							<a href="#" class="fa fa-google"></a>
						</div>
					</div>
				</div>
				<%// }%>
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
			<div class="dynamicDiv">
				<div class="dynamicDivHead">
					<p class="phead">Registration</p>
				</div>
				<form class="form-horizontal" style="padding:10px;" enctype="multipart/form-data" action="/register" method="post">
			    		<div class="form-group">
				      		<label class="control-label col-sm-4" for="firstname">First Name:</label>
				      		<div class="col-sm-8">
							<input type="text" id="firstname" class="form-control" minlength="5" placeholder="Enter name" name="firstName" required></input>
			      			</div>
			    		</div>
			    		<div class="form-group">
				      		<label class="control-label col-sm-4" for="lastname">Last Name:</label>
				      		<div class="col-sm-8">
							<input id="lastname" type="text" class="form-control" minlength="5" placeholder="Enter name" name="lastName" required></input>
			      			</div>
			    		</div>
			    		<div class="form-group">
				      		<label class="control-label col-sm-4">Email:</label>
				      		<div class="col-sm-8">
							<input type="email" onkeyup="sendEmailInfo()" id="email" class="form-control" placeholder="Enter email" name="email" required></input>
								<span id="emailAvailability"></span>
							</div>
			    		</div>
			    		<div class="form-group">
				      		<label class="control-label col-sm-4" for="username">Username:</label>
				      		<div class="col-sm-8">
							<input type="text" onkeyup="sendUserNameInfo()" id="username" class="form-control" minlength="5" placeholder="Enter username" name="userName" required></input>
								<span id="usernameAvailability"></span>
							</div>
			    		</div>
				    	<div class="form-group">
					      <label class="control-label col-sm-3" for="password">Password:</label>
					      <div class="col-sm-9">          
							<input type="password" id="password" class="form-control" minlength="5" placeholder="Enter password" name="password" required></input>
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
							<input type="file" class="form-control" name="phot"></input>
					      </div>
				    	</div>
				    	<div class="form-group">       
						<div class="col-sm-5 pull-right">
							<button type="submit" id="register" class="btn btn-default">Register</button>
				      		</div>
				      	</div>
				</form>
			</div>
		</div>

	</div>

	</div>
</body>

</html>
