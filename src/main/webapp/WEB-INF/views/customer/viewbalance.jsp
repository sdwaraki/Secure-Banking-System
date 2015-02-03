<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

		 	<div class="hero-unit"style="position:relative;left:200px;height:200px;width:800px">

<table class="table">
	<thead>
		<tr>
			<td>Account</td>
			<td>Balance</td>
		</tr>
	</thead>
	<c:forEach var="account" items="${accountlist}">
		<tr>
			<td>
				<c:out value="${account.accountType}"/>
			</td>
			<td>
				<c:out value="${account.balance}"/>
			</td>
		</tr>
	</c:forEach>
</table>
 	</div>
</body>
</html>