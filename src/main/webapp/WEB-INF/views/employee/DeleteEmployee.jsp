<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

		 	<div class="hero-unit"style=" position:relative;left:200px;height:200px;width:800px">
<h2> Employee Information</h2>
<form:form method="POST" modelAttribute="Employee" action="DeleteEmployee.html">

<table class="table">
    <tr>
        <td><form:label path="userName">Email Id</form:label></td>
        <td><form:input path="userName" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <button type="submit" class="btn" value="Delete">Delete </button>
        </td>
    </tr>
  </table>
</form:form>
<c:choose>
		<c:when test="${success == '1'}">
			<font color="blue">${message}</font>
			
		</c:when>
		
		<c:otherwise>
			<font color="red">${message}</font>
		</c:otherwise>
		
	</c:choose>
 	</div>
</body>
</html>

