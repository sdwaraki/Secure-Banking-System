<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Modify Customer</title>
</head>
<body>
<h2> Update Customer Information</h2>
<form:form method="POST" modelAttribute="Customer" action="ModifyCustomer.html">

<table>
    <tr>
        <td><form:label path="customerID">Customer ID</form:label></td>
        <td><form:input path="customerID" /></td>
    </tr>
    <tr>
        <td><form:label path="address.addressLine">Address Line</form:label></td>
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
        <td><form:label path="customerType">Customer Type</form:label></td>
        <td><form:input path="customerType" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
  </table>
</form:form>
</body>
</html>
