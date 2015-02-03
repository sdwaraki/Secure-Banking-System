
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="hero-unit" style="position:relative;left:200px; overflow:auto">
		
		<form:form method="POST"  modelAttribute="MyRequestsBackBean" action="updateTroubleShootRequest.html">
			<table class="table table bordered">
				<tr>
					<!-- <td>Request ID</td> -->
					<td>Request Type</td>
					<td>Request From</td>
					<td>Request Status</td>
					<td>Description</td>
				</tr>
				<c:forEach items="${myRequests}" var="req" varStatus="status">
					<tr>
				 	<%-- 	<td><input  type="hidden" name="myRequests[${status.index}].requestID" value="${req.requestID}" readonly/></td>
				      	<td><input name="myRequests[${status.index}].requestType" value="${req.requestType}" readonly/></td>
				      	<td><input name="myRequests[${status.index}].requestFrom" value="${req.requestFrom}" readonly/></td>
				      	<td><input name="myRequests[${status.index}].requestStatus" value="${req.requestStatus}" readonly/></td> --%>
				      	<td>${req.requestType}</td>
				      	<td>${req.requestFrom}</td>
				      	<%-- <td>"${req.requestStatus}"</td> --%>
				      	
				      	  <td><select name="myRequests[${status.index}].requestStatus" disabled>
				      	<c:forEach items="${requestStatus}" var="req1" varStatus="status1">
				      	<option ${req1 == req.requestStatus ? 'selected=selected' : ''}>
				      	${req1}
				      	</option>
				      	
				      	</c:forEach>
				      	</select ></td>
				      	<td><textarea name="myRequests[${status.index}].request"  rows="10" cols="10" readonly style="max-height:100px;min-height:100px; resize: none"> ${req.request}</textarea></td>
					</tr>
				</c:forEach>
			</table>
		</form:form>	
			</div>
		
</body>
</html>
