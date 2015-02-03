 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

		 	<div class="hero-unit"style="position:relative;left:200px;height:200px;width:800px">
		 	
			<form:form modelAttribute="FundTransferBackBean" method="POST" action="transfer.html">
				
				<table class="table">
					<tr>
						<td>
								Recipent:
						</td>
						<td>
							<select name="Chosenrecipeint.userName">
					      	<c:forEach items="${recipeints}" var="req1" varStatus="status1">
						      	<option>
							      	${req1.userName}
					      		</option>		  	
					      	</c:forEach>
					      	</select>
						</td>
					</tr>
					<tr>
						<td>
							Account:
						</td>
						<td>
							<select name="ChosenAccount.accountID">
				      		<c:forEach items="${accounts}" var="req1" varStatus="status1">
				      			<option  value="${req1.accountID}">
				      				${req1.accountType}
				      			</option>
				      		</c:forEach>
				      		</select >
						</td>
					</tr>
					<tr>
						 <td><form:label path="Amount">Amount</form:label></td>
						  <td><form:input path="Amount" /></td>
					</tr>
					<tr>
						<td>
							<button type="submit" class="btn" value="Transfer"> Transfer </button>
						</td>
					</tr>
				</table>
			</form:form>
		 	
		 	</div>
		 	
</body>
</html>