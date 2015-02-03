<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
	<head>
	<meta charset="utf-8">
		<title>The Bank | Admin HomePage</title>
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
							<li class="active"><a href="<c:url value="HrRequestForCreateEmployee.html"></c:url>">Create Request</a></li>
							<li><a href="<c:url value="HrRequestForDeleteEmployee.html"></c:url>">Delete Request</a></li>
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
					        <td><form:label path="firstName">firstName</form:label></td>
					        <td><form:input path="firstName" /></td>
					    </tr>
					    <tr>
        					<td><form:label path="lastName">lastName</form:label></td>
        					<td><form:input path="lastName" /></td>
    					</tr>
    					<tr>
    						  						
        						<td><form:label path="dob">dob</form:label></td>
        						<td><form:input path="dob" pattern="MM/DD/YYYY" /></td>
								
   
    					</tr>
    					<tr>
        					<td><form:label path="ssn">ssn</form:label></td>
        					<td><form:input path="ssn" /></td>
    					</tr>
     					<tr>
        					<td><form:label path="doj">dob</form:label></td>
        					<td><form:input path="doj" pattern="MM/DD/YYYY" /></td>
    					</tr>
    					<tr>
        					<td><form:label path="address.addressLine">addressLine</form:label></td>
        					<td><form:input path="address.addressLine" /></td>
    					</tr>
    					<tr>
        					<td><form:label path="address.city">city</form:label></td>
        					<td><form:input path="address.city" /></td>
    					</tr>
    					<tr>
					        <td><form:label path="address.zip">zip</form:label></td>
					        <td><form:input path="address.zip" /></td>
					    </tr>
					    <tr>
	        				<td><form:label path="address.phoneNumber">phoneNumber</form:label></td>
	        				<td><form:input path="address.phoneNumber" /></td>
    					</tr>
    					<tr>
	        				<td><form:label path="address.emailID">emailID</form:label></td>
	        				<td><form:input path="address.emailID" /></td>
	    				</tr>
     					<tr>
	        				<td><form:label path="salary.Amount">Salary</form:label></td>
	        				<td><form:input path="salary.Amount" /></td>
    					</tr>
						<tr>
							<td>department</td>
							<td><select id="deptid" onchange="doAjaxPost()" >
				  				<option value="1">ALL</option>
				  				<option value="2">IT</option>
				  				<option value="3">SALES</option>
				  				<option value="4">TRANSACTION</option>
				  				<option value="5">HR</option>
								</select>
		 					</td>
						</tr>
						<tr>
   							<td> <div id="info" style="color:green;" ></div> </td>
   						</tr>
   
   						<tr>
    						<td>Roles</td>
    						<td><form:select id="selectRoleBox" path="roleid" items="${roleIdNameMap}" disabled="true" /></td>
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





































</body>
</html>