<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" session="true" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
	<head>
	<meta charset="utf-8">
		<title>The Bank </title>
		<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet" type="text/css" />	
		 
		 <script src="http://code.jquery.com/jquery-1.10.1.min.js"/></script>
		
	 <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>
	 <style>
			.errorblock 
			{
				font-size: 0.7em; color:#C00; margin-left: 150px;
				text-align: left;
				margin-top: 0px;
			}
		</style>
		 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	</head>
	<body>
		<div class="container">
			<h1><a href="<c:url value="/auth/RedirectPage.html"></c:url>"> The Bank   </a></h1>
		<sec:authorize access="hasAnyRole('ADMIN','SALES_MANAGER','HR_MANAGER','TRANSACTION_MANAGER','IT_MANAGER','IT_EMPLOYEE','HR_EMPLOYEE','TRANSACTION_EMPLOYEE','INDIVIDUAL_CUSTOMER','MERCHANT_CUSTOMER','SALES_EMPLOYEE')"> 		<h3>Username : ${UserName}</h3></sec:authorize>
			<div class="navbar">
				<div class="navbar-inner">
					<div class ="container">
						<ul class="nav">
						
							<li><a href="<c:url value="/auth/RedirectPage.html"></c:url>">Home</a></li>
							<sec:authorize access="hasAnyRole('SALES_EMPLOYEE','SALES_MANAGER')">
								<li><a href="<c:url value="/customer/CreateCustomer.html"></c:url>">Create Customer </a></li>
								<li><a href="<c:url value="/customer/DeleteCustomer.html"></c:url>">Delete Customer </a></li>
								
								
							 </sec:authorize>
							 <sec:authorize access="hasAnyRole('HR_MANAGER','TRANSACTION_MANAGER','SALES_MANAGER','IT_MANAGER')">
							 <li><a href="<c:url value="/request/viewMyEmployeeRequest.html"></c:url>">My Employee Requests</a></li>
							 </sec:authorize>	
							 <sec:authorize access="hasAnyRole('HR_EMPLOYEE','HR_MANAGER')">
								<li><a href="<c:url value="/employee/CreateEmployee.html"></c:url>">Create Employee </a></li>	
								<li><a href="<c:url value="/employee/DeleteEmployee.html"></c:url>">Delete Employee </a></li>
								</sec:authorize> 						 		 					 
					<%-- 		 <sec:authorize access="hasAnyRole('SALES_MANAGER','HR_MANAGER','TRANSACTION_MANAGER','TRANSACTION_EMPLOYEE','IT_EMPLOYEE')">
								 <li><a href="<c:url value="/request/AuthorizeRequests.html"></c:url>">Complete Requests </a></li>
							</sec:authorize>
					 --%>		<sec:authorize access="hasAnyRole('INDIVIDUAL_CUSTOMER','MERCHANT_CUSTOMER')">				
								<li><a href="<c:url value="/customer/addreciepient.html"></c:url>">Add reciepient</a></li>
								<li><a href="<c:url value="/transfer/fundtransfer.html"></c:url>">Fund Transfer</a></li>
								<li><a href="<c:url value="/customer/viewbalance.html"></c:url>">View Balance</a></li>							
							</sec:authorize>
							
							<sec:authorize access="hasAnyRole('MERCHANT_CUSTOMER')">	
								<li><a href="<c:url value="/merchant/addpayee.html"></c:url>">Add payee</a></li>
								<li><a href="<c:url value="/merchant/payment.html"></c:url>">Payment</a></li>
								<li><a href="<c:url value="/merchant/viewTransaction.html"></c:url>">view Transactions</a></li>
							</sec:authorize>	
							
							<sec:authorize access="hasAnyRole('INDIVIDUAL_CUSTOMER')">	
								<li><a href="<c:url value="/customer/viewTransaction.html"></c:url>">view Transactions</a></li>
							</sec:authorize>	
							
							
						<%-- 	<sec:authorize access="hasAnyRole('HR_MANAGER','HR_EMPLOYEE')">
								<li><a href="<c:url value="RequestForCreateEmployee.html"></c:url>">Create Request</a></li>
								<li><a href="<c:url value="RequestForDeleteEmployee.html"></c:url>">Delete Request</a></li>	 					
							</sec:authorize>
							 --%>

							<sec:authorize access="hasAnyRole('SALES_MANAGER','HR_MANAGER','TRANSACTION_MANAGER','IT_MANAGER')">
								<li><a href="<c:url value="/employee/TransferEmployee.html"></c:url>">Transfer Employee</a></li>
							</sec:authorize >	

							<sec:authorize access="hasAnyRole('ADMIN')"> 	
								
								<li><a href="<c:url value="/system/ViewSyslog.html"></c:url>">View Sys Log</a></li>
																																																															
		 					</sec:authorize>	
		 						
						</ul>
					</div>
				</div>
			</div>	 
 		<br>
 	</div>
 					<sec:authorize access="hasAnyRole('ADMIN','SALES_MANAGER','HR_MANAGER','TRANSACTION_MANAGER','IT_MANAGER','IT_EMPLOYEE','HR_EMPLOYEE','TRANSACTION_EMPLOYEE','INDIVIDUAL_CUSTOMER','MERCHANT_CUSTOMER','SALES_EMPLOYEE','CORPORATE_EMPLOEE')"> 	
 	<div class="sidebar-nav" style="position:absolute; text-align:center">
		    	<div class="well" style="width:120px; padding:8px 0; padding-left:2cm">
				  	<ul class="nav nav-pills nav-stacked "> 
		
				  <li class="nav-header">MENU</li> 
				  
		       
					<li><a href="<c:url value="/request/TroubleShoot.html"></c:url>">Raise a TroubleShooting Request</a></li>	
					<li><a href="<c:url value="/request/GetPendingRequests.html"></c:url>">My Requests</a></li>		
					<li><a href="<c:url value="/request/AuthorizeRequests.html"></c:url>">AuthOrize Requests</a></li>							
					<li><a href="<c:url value="/MyAccount"></c:url>">MyAccount </a></li>
					<li><a href="<c:url value="/j_spring_security_logout" />" > Logout</a></li>
				
				 </ul>
		     </div>
		    </div>

 			</sec:authorize>