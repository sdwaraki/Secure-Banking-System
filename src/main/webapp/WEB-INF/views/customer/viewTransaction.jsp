<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="hero-unit" style="position:absolute; left:200; width:800px; height:600px; float:left">

<table class="table table-bordered"  align="center" bgcolor="#2B60DE">

<thead>
	
	<tr>
		<td>
		TransactionID
		</td>
		
		<td>
		TransactionType
		</td>
		
		<td>
		From
		</td>
		
		<td>
		To
		</td>
		
		<td>
		status
		</td>
		
	</tr>
	
</thead>
<c:forEach items="${allTransactions}" var="transact">
<tr>
	<td>
		<c:out value="${transact.transactionID}" />
	</td>
	<td>
		<c:out value="${transact.transactionType}" />
	</td>
	<td>
		<c:out value="${transact.accountID}" />
	</td>
	<td>
		<c:out value="${transact.toAccountID}" />
	</td>
	<td>
		<c:out value="${transact.status}" />
	</td>
</tr>
</c:forEach>
</table>

</div>

</body>
</html>