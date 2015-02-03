<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>The Bank |Login Page</title>
		<style>
			.errorblock 
			{
				color: #ff0000;
				background-color: #ffEEEE;
				border: 3px solid #ff0000;
				padding: 8px;
				margin: 16px;
			}
		</style>
		<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet" type="text/css" />
	</head>

<body onload='document.f.j_username.focus();'>
	<div class="container">
		<h1><a href="<c:url value="/auth/RedirectPage.html"></c:url>">The Bank</a></h1>
		<div class="navbar">
			<div class="navbar-inner">
				<div class ="container">
					<ul class="nav">
						<li><a href="<c:url value="/auth/RedirectPage.html"></c:url>">Home </a></li>
						<li><a href="<c:url value="/AboutUs.html"></c:url>">About Us</a></li>
    					<li><a href="<c:url value="/ContactUs.html"></c:url>">Contact Us </a></li>
    					<li class="active"><a href="<c:url value="Login.html"></c:url>">Login</a></li>
    				</ul>
				</div>
			</div>
		</div>		
	<h3>Login with Username and Password</h3>
 	<div class="hero-unit" style="overflow:auto">
 		<div class="content">
 			<div class= "row">
 				<div class="login-form">
 					<c:if test="${not empty error}">
							<div class="errorblock">
							Your login attempt was not successful, try again.<br /> Caused :
						${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}				
							</div>
					</c:if>
				<c:if test="${not empty logout}">
							<div class="errorblock">
							 Logged Off.<br />
							</div>
					</c:if>
					<c:if test="${not empty SessionTimeOut}">
							<div class="errorblock">
							 Logged Off<br />
							</div>
					</c:if>
 				<form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
		 			<fieldset>
		 				<div class="clearfix">
		 				UserName: <input type='text' name='j_username' value=''/>
		 				</div>
		 				<br/>
						 <br/>
						 <div class="clearfix">
		 					Password: <input type='password' name='j_password' />
		 				</div>
						<br/>
						<br/>
						 
						 <%
       				  ReCaptcha c = ReCaptchaFactory.newReCaptcha("6LeRJukSAAAAABfYVE8IWk2smeT1UMnibf4mGW22", "6LeRJukSAAAAAMp7LQFkZIaLGCAkk2W0SQBVtZ2x", false);
         out.print(c.createRecaptchaHtml(null, null));
       %>
						 
						 
        				<br/>
				        <br/>
				        <br/>
						<input name="submit" type="submit"	value="submit" />		
					</fieldset>
				</form>
	
				</div>
			</div>
		</div>
	</div>
	</div>
<script src="http://code.jquery.com/jquery-1.10.1.min.js"/></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>
</body>
</html>