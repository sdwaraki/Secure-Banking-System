<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
	<head>
	<meta charset="utf-8">
		<title>The Bank | Delete User HR Request</title>
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
							<li><a href="<c:url value="HrEmployeeHomePage.html"></c:url>">Home</a></li>
							<li><a href="<c:url value="HrRequestForCreateEmployee.html"></c:url>">Create Request</a></li>
							<li class="active"><a href="<c:url value="HrRequestForDeleteEmployee.html"></c:url>">Delete Request</a></li>
	 						<li><a href="<c:url value="HrMyRequests.html"></c:url>">My Requests </a></li>
	 						<li><a href="<c:url value="MyAccount.html"></c:url>">My Account </a></li>
							<li><a href="<c:url value="/j_spring_security_logout" />" > Logout</a></li>
						</ul>
					</div>
				</div>
			</div>
			
			<div class="hero-unit">
			
				<form:form method="POST" modelAttribute="CreateEmployeeBackBean" action="HrRequestForCreateEmployee.html">
					<table>
    					<tr>
					        <td><form:label path="userName">Enter Username</form:label></td>
					        <td><form:input path="userName" /></td>
					    </tr>
					    <tr>
        					<td colspan="2">
            					<input type="submit" value="Submit"/>
        					</td>
    					</tr>
  					</table>
  				</form:form>
			</div>
		</div>
		<script src="http://code.jquery.com/jquery-1.10.1.min.js"/></script>
		<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>
	</body>
</html>		