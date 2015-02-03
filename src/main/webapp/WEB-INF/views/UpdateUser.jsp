<jsp:include page="/WEB-INF/views/MyAccountHeader.jsp"></jsp:include>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
		<script type="text/JavaScript">
			  function valid(f) {
				  !(/^[A-z&#209;&#241;0-9 ,]*$/i).test(f.value)?f.value = f.value.replace(/[^A-z&#209;&#241;0-9 ,]/ig,''):null;
				  } 

  			</script>

		<div class="hero-unit" style="position:relative;left:200px;height:600px;width:800px">
			${successMsg}
			<form:form method="POST" modelAttribute="BankUser" action="UpdateUser.html">
				<form:hidden path="userId" />
					<form:hidden path="address.addressID" />
							<table>
			    					<tr>
								        <td><form:label path="firstName">firstName</form:label></td>
								        <td><form:input path="firstName" onkeyup="valid(this)" onblur="valid(this)"/></td>
								        <td> <form:errors path="firstName" cssClass="errorblock"></form:errors></td>
				
								    </tr>
								    <tr>
			        					<td><form:label path="lastName">lastName</form:label></td>
			        					<td><form:input path="lastName" onkeyup="valid(this)" onblur="valid(this)"/></td>
			        					<td> <form:errors path="lastName" cssClass="errorblock"></form:errors></td>
			    					</tr>
			    					<tr>    						  						
			        						<td><form:label path="dob">dob</form:label></td>
			        						<td><form:input path="dob" id="datepicker" readonly="true"/></td>
			        						<td> <form:errors path="dob" cssClass="errorblock"></form:errors></td>		   
			    					</tr>
			    					<tr>
			        					<td><form:label path="ssn">ssn</form:label></td>
			        					<td><form:input path="ssn" onkeyup="valid(this)" onblur="valid(this)" readonly="true"/></td>
			        					<td> <form:errors path="ssn" cssClass="errorblock"></form:errors></td>
			    					</tr>
			     					<tr>
			        					<td><form:label path="address.addressLine">addressLine</form:label></td>
			        					<td><form:input path="address.addressLine" onkeyup="valid(this)" onblur="valid(this)"/></td>
			        					<td> <form:errors path="address.addressLine" cssClass="errorblock"></form:errors></td>
			    					</tr>
			    					<tr>
			        					<td><form:label path="address.city">city</form:label></td>
			        					<td><form:input path="address.city" onkeyup="valid(this)" onblur="valid(this)"/></td>
			        					<td> <form:errors path="address.city" cssClass="errorblock"></form:errors></td>
			    						
			    					</tr>
			    					<tr>
								        <td><form:label path="address.zip">zip</form:label></td>
								        <td><form:input path="address.zip" onkeyup="valid(this)" onblur="valid(this)"/></td>
										<td> <form:errors path="address.zip" cssClass="errorblock"></form:errors></td>
			    			
								    </tr>
								    <tr>
				        				<td><form:label path="address.phoneNumber">phoneNumber</form:label></td>
				        				<td><form:input path="address.phoneNumber" onkeyup="valid(this)" onblur="valid(this)"/></td>
			    						<td> <form:errors path="address.phoneNumber" cssClass="errorblock"></form:errors></td>
			
			    					</tr>
			    					<tr>
				        				<td><form:label path="address.emailID">emailID</form:label></td>
				        				<td><form:input path="address.emailID"/></td>
				    					<td> <form:errors path="address.emailID" cssClass="errorblock"></form:errors></td>
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
			
			<script src="http://code.jquery.com/jquery-1.10.1.min.js"/></script>
			<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>
			
		</body>
	</html>