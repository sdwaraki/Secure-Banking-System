<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
			
			<div class="hero-unit" style="position:absolute; left:200">
			
				<form:form method="POST" modelAttribute="CreateEmployeeBackBean" action="RequestForCreateEmployee.html">
					<table>
    					<tr>
					        <td><form:label path="firstName">firstName</form:label></td>
					        <td><form:input path="firstName" /></td>
					    </tr>
					    <tr>
        					<td><form:label path="lastName">lastName</form:label></td>
        					<td><form:input path="lastName" /></td>
    					</tr>
    					<tr>
    						  						
        						<td><form:label path="dob">dob</form:label></td>
        						<td><form:input path="dob" pattern="MM/DD/YYYY" /></td>
								
   
    					</tr>
    					<tr>
        					<td><form:label path="ssn">ssn</form:label></td>
        					<td><form:input path="ssn" /></td>
    					</tr>
     					<tr>
        					<td><form:label path="doj">dob</form:label></td>
        					<td><form:input path="doj" pattern="MM/DD/YYYY" /></td>
    					</tr>
    					<tr>
        					<td><form:label path="address.addressLine">addressLine</form:label></td>
        					<td><form:input path="address.addressLine" /></td>
    					</tr>
    					<tr>
        					<td><form:label path="address.city">city</form:label></td>
        					<td><form:input path="address.city" /></td>
    					</tr>
    					<tr>
					        <td><form:label path="address.zip">zip</form:label></td>
					        <td><form:input path="address.zip" /></td>
					    </tr>
					    <tr>
	        				<td><form:label path="address.phoneNumber">phoneNumber</form:label></td>
	        				<td><form:input path="address.phoneNumber" /></td>
    					</tr>
    					<tr>
	        				<td><form:label path="address.emailID">emailID</form:label></td>
	        				<td><form:input path="address.emailID" /></td>
	    				</tr>
     					<tr>
	        				<td><form:label path="salary.Amount">Salary</form:label></td>
	        				<td><form:input path="salary.Amount" /></td>
    					</tr>
						<tr>
							<td>department</td>
							<td><select id="deptid" onchange="doAjaxPost()" >
				  				<option value="1">ALL</option>
				  				<option value="2">IT</option>
				  				<option value="3">SALES</option>
				  				<option value="4">TRANSACTION</option>
				  				<option value="5">HR</option>
								</select>
		 					</td>
						</tr>
						<tr>
   							<td> <div id="info" style="color:green;" ></div> </td>
   						</tr>
   
   						<tr>
    						<td>Roles</td>
    						<td><form:select id="selectRoleBox" path="roleid" items="${roleIdNameMap}" disabled="true" /></td>
    					</tr>
   						<tr>
        					<td colspan="2">
            					<input type="submit" value="Submit"/>
        					</td>
    					</tr>
  					</table>
  				</form:form>
			</div>
		</div>
		
	</body>
</html>		





































</body>
</html>