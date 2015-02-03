<jsp:include page="/WEB-INF/views/MyAccountHeader.jsp"></jsp:include>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<script language="javascript">
	function fncSubmit()
	{
	
		if(document.ChangePasswordForm.OldPassword.value == "")
		{
			alert('Please input old password');
			document.ChangePasswordForm.OldPassword.focus();
			return false;
		}	
	
		if(document.ChangePasswordForm.newpassword.value == "")
		{
			alert('Please input Password');
			document.ChangePasswordForm.newpassword.focus();		
			return false;
		}	
	
		if(document.ChangePasswordForm.conpassword.value == "")
		{
			alert('Please input Confirm Password');
			document.ChangePasswordForm.conpassword.focus();		
			return false;
		}	
	
		if(document.ChangePasswordForm.newpassword.value != document.ChangePasswordForm.conpassword.value)
		{
			alert('Confirm Password Not Match');
			document.ChangePasswordForm.conpassword.focus();		
			return false;
		}	
	
			document.ChangePasswordForm.submit();
	}
	</script>
	
		<div class="hero-unit" style="position:relative;left:200px;height:200px;width:800px">	
		<form name="ChangePasswordForm" method="post" action="processChangePWD" OnSubmit="return fncSubmit();">
		 
		  <table class="table table-bordered"  align="center" bgcolor="#2B60DE">
		    
		      <tr>
		        <td>OLD PASSWORD</td>
		       <TD><input name="OldPassword" type="password" id="OLDpwd" size="20"></td>
		      </tr>
		      <tr>
		        <td>NewPassword</td>
		        <td><input name="newpassword" type="password" id="newpassword">
		        </td>
		      </tr>
		      <tr>
		        <td>Confirm Password</td>
		        <td><input name="conpassword" type="password" id="conpassword">
		        </td>
		      </tr>
		      <tr>
		      <td>&nbsp;</td>
		      <td><button type="submit" class="btn" value="Save">Submit </button></td>
		      </tr>
		   
		  </table>
		  </form>
		  <c:if test="${not empty Error}">
					<div class="errorblock">
						Could Not Update the Password.<br /> Caused :
						${Error}				
			</div>
</c:if>
<c:if test="${not empty Success}">
	
		${Success}				
	
</c:if>
	</div>
	
	<script src="http://code.jquery.com/jquery-1.10.1.min.js"/></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>	  
	</body>
</html>