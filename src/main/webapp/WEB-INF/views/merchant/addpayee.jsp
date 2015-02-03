<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>


<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    
<script>
function popup(){
	
	alert("please wait while your request is approved by payee");
}

</script>


<div class="hero-unit" style="position:absolute; left:200; width:500px; height:300px; float:left">
<form:form modelAttribute="Customer" method="post" action="addpayee.html">
<table class="table">
	<tr>
		<td>
			Enter Email
		</td>
		<td>
			<form:input path="userName"/>
		</td>
	</tr>
	<tr>
		<td>
			<button type="submit" class="btn" value="add payee" onclick="popup()"> Add Payee </button>
		</td>
	</tr>
</table>
</form:form>


<form:form modelAttribute="MyRequestBackBean" action="result.html" method="post">
<input type="button" value="show Payee">

</form:form>
</div>
</body>
</html>