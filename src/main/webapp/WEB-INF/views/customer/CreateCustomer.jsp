<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
			<div class="hero-unit" style="position:absolute; left:200; height:100%; width:820px">
					<script type="text/JavaScript">
			  function valid(f) {
				  !(/^[A-z&#209;&#241;0-9 ,]*$/i).test(f.value)?f.value = f.value.replace(/[^A-z&#209;&#241;0-9 ,]/ig,''):null;
				  } 

  			</script>
					<form:form method="POST" modelAttribute="Customer" action="CreateCustomer.html">
					
						<table class="table">
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
						        <td><form:input path="dob" placeholder="dd/mm/yyyy"/></td>
						           <td> <form:errors path="dob" cssClass="errorblock"></form:errors></td>
						    </tr>
						    <tr>
						        <td><form:label path="ssn">ssn</form:label></td>
						        <td><form:input path="ssn" onkeyup="valid(this)" onblur="valid(this)"/></td>
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
						        <td><form:input path="address.emailID" /></td>
						           <td> <form:errors path="address.emailID" cssClass="errorblock"></form:errors></td>
						    </tr>
						    <tr>
						    	<td><form:label path="customerType">Customer Type</form:label>s</td>
						        <td>
						        	<form:select path="customerType"> 
		       		 				<form:options items="${customerTypes}"/>
		        					</form:select>
		       					 </td>
							</tr>
						    <tr>
						        <td><form:label path="accounts[0].balance">Balance for Checking</form:label></td>
						        <td><form:input path="accounts[0].balance" /></td>
						           <td> <form:errors path="accounts[0].balance" cssClass="errorblock"></form:errors></td>
						    </tr>
						       <tr>
						        <td><form:label path="accounts[1].balance">Balance for Savings</form:label></td>
						        <td><form:input path="accounts[1].balance" /></td>
						           <td> <form:errors path="accounts[1].balance" cssClass="errorblock"></form:errors></td>
						    </tr>
						    <tr>
						        <td colspan="2">
						             <button type="submit" class="btn" value="Submit"> Submit</button>
						        </td>
						    </tr>
						 </table>
					</form:form>
				</div>
		
			
		</body>
</html>