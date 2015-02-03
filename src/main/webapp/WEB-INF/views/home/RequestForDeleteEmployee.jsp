<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

			
			<div class="hero-unit" style="position:relative;left:200px;height:200px;width:800px">
			
				<form:form method="POST" modelAttribute="CreateEmployeeBackBean" action="RequestForDeleteEmployee.html">
					<table>
    					<tr>
					        <td><form:label path="userName">Enter Username</form:label></td>
					        <td><form:input path="userName" /></td>
					    </tr>
					    <tr>
        					<td colspan="2">
            					<input type="submit" value="Submit"/>
        					</td>
    					</tr>
  					</table>
  				</form:form>
			</div>
		
	</body>
</html>		