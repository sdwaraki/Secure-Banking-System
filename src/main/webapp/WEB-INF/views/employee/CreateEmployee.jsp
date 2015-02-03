
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
		<script src="https://code.jquery.com/jquery-1.9.1.js"></script>
 			 <script src="https://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
			<script>
				  $(function() {
				    $( "#datepicker" ).datepicker();
				  });
				  $(function() {
					    $( "#datepicker1" ).datepicker();
					  });

  			</script>
  			<script type="text/JavaScript">
			  function valid(f) {
				  !(/^[A-z&#209;&#241;0-9 ,]*$/i).test(f.value)?f.value = f.value.replace(/[^A-z&#209;&#241;0-9 ,]/ig,''):null;
				  } 

  			</script>
			
		<div class="hero-unit" style="position:absolute; left:200;width:820; overflow:auto"> 		
				<form:form method="POST" modelAttribute="CreateEmployeeBackBean" action="CreateEmployee.html">
					<table class="table">
    					<tr>
    					
					        <td><form:label path="firstName">First Name:</form:label></td>
					        <td><form:input path="firstName" onkeyup="valid(this)" onblur="valid(this)"/></td>
					        <td> <form:errors path="firstName" cssClass="errorblock"></form:errors></td>
					      
					    </tr>
					    <tr>
        					<td><form:label path="lastName">Last Name:</form:label></td>
        					<td><form:input path="lastName" onkeyup="valid(this)" onblur="valid(this)" /></td>
        					   <td> <form:errors path="lastName" cssClass="errorblock"></form:errors></td>
    					</tr>
    					<tr>
    						  						
        						<td><form:label path="dob">Date Of Birth(MM/DD/YYYY)</form:label></td>
        						<td><form:input path="dob" id="datepicker" placeholder="dd/mm/yyyy" /></td>
								  <td> <form:errors path="dob" cssClass="errorblock"></form:errors></td>
   
    					</tr>
    					<tr>
        					<td><form:label path="ssn">SSN</form:label></td>
        					<td><form:input path="ssn" onkeyup="valid(this)" onblur="valid(this)"/></td>
        					  <td> <form:errors path="ssn" cssClass="errorblock"></form:errors></td>
    					</tr>
     					<tr>
        					<td><form:label path="doj">Date Of Joining(MM/DD/YYYY)</form:label></td>
        					<td><form:input path="doj" id="datepicker1" /></td>
        					  <td> <form:errors path="doj" cssClass="errorblock"></form:errors></td>
    					</tr>
    					<tr>
        					<td><form:label path="address.addressLine">Address Line</form:label></td>
        					<td><form:input path="address.addressLine" onkeyup="valid(this)" onblur="valid(this)"/></td>
        					   <td> <form:errors path="address.addressLine" cssClass="errorblock"></form:errors></td>
    					</tr>
    					<tr>
        					<td><form:label path="address.city">City</form:label></td>
        					<td><form:input path="address.city" onkeyup="valid(this)" onblur="valid(this)"/></td>
        					  <td> <form:errors path="address.city" cssClass="errorblock"></form:errors></td>
    					</tr>
    					<tr>
					        <td><form:label path="address.zip">ZIP</form:label></td>
					        <td><form:input path="address.zip" onkeyup="valid(this)" onblur="valid(this)"/></td>
					           <td> <form:errors path="address.zip" cssClass="errorblock"></form:errors></td>
					    </tr>
					    <tr>
	        				<td><form:label path="address.phoneNumber">Phone Number</form:label></td>
	        				<td><form:input path="address.phoneNumber" onkeyup="valid(this)" onblur="valid(this)"/></td>
	        				  <td> <form:errors path="address.phoneNumber" cssClass="errorblock"></form:errors></td>
    					</tr>
    					<tr>
	        				<td><form:label path="address.emailID">Email ID</form:label></td>
	        				<td><form:input path="address.emailID" /></td>
	        				  <td> <form:errors path="address.emailID" cssClass="errorblock"></form:errors></td>
	    				</tr>
     					<tr>
	        				<td><form:label path="salary.Amount">Salary(In $)</form:label></td>
	        				<td><form:input path="salary.Amount" /></td>
	        				  <td> <form:errors path="salary.Amount" cssClass="errorblock"></form:errors></td>
    					</tr>
						<tr>
   							<td> <div id="info" style="color:green;" ></div> </td>
   						</tr>
   
   						<tr>
    						<td>Department-Role</td>
    						<td><form:select id="selectRoleBox" path="roleid" items="${roleIdNameMap}"/></td>
    					</tr>
   						<tr>
        					<td colspan="2">
            					<button type="submit" class="btn" value="Submit"> Submit</button>
        					</td>
    					</tr>
  					</table>
  				</form:form>
			</div>
		
		<!-- <script src="http://code.jquery.com/jquery-1.10.1.min.js"/></script> -->
		<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>
		</div>
	</body>
</html>