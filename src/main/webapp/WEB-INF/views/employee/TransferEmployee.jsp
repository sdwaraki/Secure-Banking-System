<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			
		<div class="hero-unit" style="position:absolute; left:200; width:820; height:200 "> 		
				<form:form method="POST"  action="TransferEmployee.html">
				
					<table class="table">
						<tr>
        					 <td>Email ID</td>
		      				 <TD><input name="Email" type="text" id="email" size="20"></td>
   						</tr>
   						<tr>
    						<td>Department-Role</td>
    							      	
				      	  <td><select  id="role" name="role">
			
    							<c:forEach items="${rolesList}" var="req1" varStatus="status1">
				      			<option>
						      	${req1}
						      	</option>
						      </c:forEach>
						      </select>
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
	</body>
</html>