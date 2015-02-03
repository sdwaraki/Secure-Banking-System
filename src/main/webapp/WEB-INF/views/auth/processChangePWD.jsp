<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<body>
<c:if test="${not empty Error}">
	<div class="errorblock">
		Could Not Update the Password.<br /> Caused :
		${Error}				
	</div>
</c:if>
<c:if test="${not empty Success}">
	
		${Success}				
	
</c:if>

<a href="<c:url value="index.jsp"></c:url>">home</a>


</body>
</html>