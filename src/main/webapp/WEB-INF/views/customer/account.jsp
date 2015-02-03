<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Provide Account Details here:</h2>
<form:form method="POST" modelAttribute="Account" action="account.html">
   <table>
    <tr>
        <td><form:label path="accountType">Account Type</form:label></td>
        <td><form:input path="accountType" /></td>
    </tr>
    <tr>
        <td><form:label path="customerID">Customer ID</form:label></td>
        <td><form:input path="customerID" /></td>
    </tr>
    <tr>
    	<td><form:label path="balance">Inital Balance</form:label></td>
        <td><form:input path="balance" /></td>
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