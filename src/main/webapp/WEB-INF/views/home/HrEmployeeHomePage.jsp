<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
			<h1><a href="HrEmployeeHomePage.html"> The Bank </a></h1>
			<h3>Username : ${Employee.userName}</h3>
			<div class="navbar">
				<div class="navbar-inner">
					<div class ="container">
						<ul class="nav">
							<li class="active"><a href="<c:url value="HrEmployeeHomePage.html"></c:url>">Home</a></li>
							<li><a href="<c:url value="HrRequestForCreateEmployee.html"></c:url>">Create Request</a></li>
							<li><a href="<c:url value="HrRequestForDeleteEmployee.html"></c:url>">Delete Request</a></li>
	 						<li><a href="<c:url value="HrMyRequests.html"></c:url>">My Requests </a></li>
	 						<li><a href="<c:url value="/MyAccount.html"></c:url>">My Account </a></li>
							<li><a href="<c:url value="/j_spring_security_logout" />" > Logout</a></li>
						</ul>
					</div>
				</div>
			</div>
			
			<div class="hero-unit" style="position:absolute; left:200">
				<h1>Welcome Username : ${Employee.userName} </h1>
				<h2>Employee ID: ${Employee.employeeId}</h2>
			</div>
		</div>
	</body>
</html>
		
		
		
		
		
		
	