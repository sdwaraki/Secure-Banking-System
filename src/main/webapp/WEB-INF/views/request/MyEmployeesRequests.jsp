<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>My Employees Request</title>
</head>
<body>

		<form:form method="POST"  modelAttribute="MyRequestsBackBean" action="updateTroubleShootRequest.html">
			<table border="1">
				<tr>
					<td>Request ID</td>
					<td>Request Type</td>
					<td>Request From</td>
					<td>Request To</td>
					<td>Request Status</td>
					<td>Description</td>
					
				</tr>
				<c:forEach items="${myRequests}" var="req" varStatus="status">
					<tr>
				 		<td><input name="myRequests[${status.index}].requestID" value="${req.requestID}" readonly/></td>
				      	<td><input name="myRequests[${status.index}].requestType" value="${req.requestType}" readonly/></td>
				      	<td><input name="myRequests[${status.index}].requestFrom" value="${req.requestFrom}" readonly/></td>
				      	<td><input name="myRequests[${status.index}].requestFrom" value="${req.requestTo}" readonly/></td>
				      	<td><input name="myRequests[${status.index}].requestStatus" value="${req.requestStatus}" readonly/></td>
				      	<td><textarea name="myRequests[${status.index}].request"  rows="10" cols="10" readonly style="max-height:100px;min-height:100px; resize: none"> ${req.request}</textarea></td>
					</tr>
				</c:forEach>
			</table>
		</form:form>	
		
		<a href="<c:url value="index.jsp"></c:url>">home</a>
	</body>
</body>
</html>