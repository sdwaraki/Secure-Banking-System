<%-- <html>
<body>
 <h2>Spring Security</h2>
 
 User principal:
 <%=request.getUserPrincipal()%>
  
 User name:
 <%=request.getRemoteUser()%>
  
 
</body>
</html> --%>
 <jsp:forward page="/login.html"></jsp:forward>
<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
   <html>
<head>
<meta charset="utf-8">
<title> The Bank : Home Page</title>
<link href="<c:url value="/resources/css/bootstrap.css" />"
        rel="stylesheet"  type="text/css" />  
</head>

<body>
<div class="container">
<h1><a href="index.html"> The Bank</a></h1>
<div class="navbar">
	<div class="navbar-inner">
	<div class="container">
	<ul class= "nav">
	<li class="active"><a href="index.html"> Home </a>
	<li><a href="about_us.html">About Us</a></li>
    <li><a href="contact_us.html">Contact Us</a></li>
    <li><a href="login.html">Login</a></li>
    </ul>
    </div>
</div>
</div>
<div class="hero-unit">
    <h1>Marketing stuff!</h1>
 
    <p>This is SPARTA!.</p>
 
    <a href="#" class="btn btn-large btn-success">Get Started</a>
 </div>

</div>


<script src="http://code.jquery.com/jquery-1.10.1.min.js"/></script>
<!-- <script src=”js/bootstrap.js”></script> -->
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>
</body>
</html>
     --%>