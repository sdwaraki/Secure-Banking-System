<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

		 	<div class="hero-unit"style="position:relative;left:200px;height:200px;width:800px">
<script>
	$(document).ready(function() {
	    activeTable = $('.dataTable').dataTable({
	    	"bJQueryUI" : true,
			"sPaginationType" : "full_numbers",
			"bAutoWidth" : false
	    });
	} );
	function onClick(){
		
		location.href='bankapp/customer/addrecipient.html';	
	}
</script>

	<form:form method="POST" modelAttribute="Customer" action="addreciepient.html">
		<form:label path="userName">Enter Email</form:label>
		<form:input path="userName" readonly="${Add==0 }"/>
		<br><br>
		<c:if test="${Add == 1}">
		<button type="submit" class="btn" value="add recipient"> Add Recipient </button>
		</c:if>
	</form:form>
		<c:choose>
			<c:when test="${success == '1' && Add==0}">
				<form method="POST" action="confirmPasscode.html">
					<table class="table">
						<tr>
				        	<td>Passcode</td>
							<td>  <input name="passCode" type="password" id="passCode" size="20"></td>
						</tr>
						<tr>
						<td><button type="submit" class="btn" value="validate">Validate</button> </td>
						</tr>
					</table>
				</form>
			<font color="blue">${message}</font>
		
		</c:when>
		
		<c:otherwise>
		<c:if test="${success == '1'}">
			<font color="blue">${message}</font>
		</c:if>
		<c:if test="${success == '0'}">
			<font color="red">${message}</font>
		</c:if>
		</c:otherwise>
		
	</c:choose>
	</div>
</body>
</html>