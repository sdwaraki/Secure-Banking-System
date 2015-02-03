<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
		<head>
				<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
				<title>HR Employee MyRequests</title>
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
										<li><a href="<c:url value="HrRequestForDeleteEmployee.html"></c:url>">Delete Request</a></li>
				 						<li class="active"><a href="<c:url value="HrMyRequests.html"></c:url>">My Requests </a></li>
				 						<li><a href="<c:url value="MyAccount.html"></c:url>">My Account </a></li>
										<li><a href="<c:url value="/j_spring_security_logout" />" > Logout</a></li>
									</ul>
								</div>
							</div>
						</div>
						
						<div class="hero-unit">
								<table class="table table-condensed">
									<caption> Request Details </caption>
									<thead>
										<tr>
											<th> Request ID </th>
											<th> Description </th>
											<th> Status	</th>
										</tr>
									</thead>
									<tbody>
											<tr> 
												<td>RequestA</td>
												<td>Dummy Request</td>
												<td>Closed</td>
											</tr>
									</tbody>
								</table>
						</div>
				</div>
			<script src="http://code.jquery.com/jquery-1.10.1.min.js"/></script>
			<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>		
	</body>
</html>