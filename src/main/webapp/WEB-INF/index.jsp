<jsp:forward page="/login.html"></jsp:forward>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<meta charset="utf-8">
		<title>The Bank | HomePage</title>
		<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet" type="text/css" />
	</head>
	
	<body>
		<div class="container">
			<h1><a href="index.html">The Bank</a></h1>
			<div class="navbar">
				<div class="navbar-inner">
					<div class ="container">
						<ul class="nav">
							<li class="active"><a href="index.jsp"> Home </a></li>
							<li><a href="AboutUs.jsp">About Us</a></li>
	    					<li><a href="ContactUs.jsp">Contact Us</a></li>
	    					<li><a href="login.html">Login</a></li>
	    				</ul>
					</div>
				</div>
			</div>
			<div class="hero-unit">
				<h1> Its not like your piggy bank :P</h1>
			</div>
		</div>	
		
		<script src="http://code.jquery.com/jquery-1.10.1.min.js"/></script>
		<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>
		
	</body>
</html>
 --%>