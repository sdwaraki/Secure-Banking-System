<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>MyAccount</title>
			<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet" type="text/css" />
			 <style>
			.errorblock 
			{
				font-size: 0.7em; color:#C00; margin-left: 150px;
				text-align: left;
				margin-top: 0px;
			}
		</style>
	</head>
	<body>
		<div class="container">
		<h1><a href="<c:url value="/auth/RedirectPage.html"></c:url>">The Bank</a></h1>
		<h3>Username : ${UserName}</h3>
		<div class="navbar">
			<div class="navbar-inner">
				<div class ="container">
					<ul class="nav">
							<li><a  href="<c:url value="/auth/RedirectPage.html"></c:url>">Home</a></li>
							<li><a href="UpdateUser.html"> Update User </a></li>
							<li><a href="ChangePasswordForm.html">Change Password</a></li>
							<li><a href="j_spring_security_logout">Logout</a></li>
					</ul>
				</div>
			</div>
		</div>
		</div>
		
		<div class="sidebar-nav" style="position:absolute">
		    <div class="well" style="width:120px; padding: 8px 0; padding-left: 2cm">
				<ul class="nav nav-list"> 
				  <li class="nav-header">MENU</li>        
					<li><a href="<c:url value="/request/TroubleShoot.html"></c:url>">Raise a TroubleShooting Request</a></li>	
					<li><a href="<c:url value="/request/GetPendingRequests.html"></c:url>">My Requests</a></li>		
					<li><a href="<c:url value="/request/AuthorizeRequests.html"></c:url>">AuthOrize Requests</a></li>							
					<li><a href="<c:url value="/MyAccount"></c:url>">MyAccount </a></li>
					<li><a href="<c:url value="/j_spring_security_logout" />" > Logout</a></li>
				 </ul>
		     </div>
	</div>
 	
		
		