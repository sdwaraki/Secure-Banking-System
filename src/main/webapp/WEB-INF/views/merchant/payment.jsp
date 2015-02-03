<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>



<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div class="hero-unit" style="position:absolute; left:200; float:left; overflow:auto">
<form:form action="payment.html" method="POST" modelAttribute="PaymentBackBean">

<table class="table">
	<tr>
		<td>
			List of Payee
		</td>
		<td>
			<select name="payee.userName" >
			 <c:forEach items="${payeeList}" var="payee">
			<option>
				${payee}
			</option>
			</c:forEach>  
			</select>
		</td>
						
	</tr>
	
	<tr>
		<td>
			Merchant Account
		</td>
		<td>
		<select name="account.accountID" >
			 <c:forEach items="${accountList}" var="account">
			<option value="${account.accountID}">
				${account.accountType}
			</option>
			</c:forEach>  
			</select>
		</td>
	</tr>
	
	<tr>
		<td>
			Amount
		</td>
		<td>
		<form:input path="amount"/>
		</td>
	</tr>
</table>
<button type="submit" class="btn" value="transfer"> Transfer </button>
</form:form>
</div>

</body>
</html>