<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="hero-unit" style="position:relative;left:200px;width:800px;overflow:auto">
		<script type="text/JavaScript">
			  function valid(f) {
				  !(/^[A-z&#209;&#241;0-9 ,]*$/i).test(f.value)?f.value = f.value.replace(/[^A-z&#209;&#241;0-9 ,]/ig,''):null;
				  } 

  			</script>
<form name="RaiseATroubleShootRequest" method="post" action="processTroubleShootRequest.html">
<table class="table table-bordered">
		<tr>
			<td>Subject</td>
			<td><input name="subject" type="text" value="" required onkeyup="valid(this)" onblur="valid(this)">
		</tr>
		<tr>
			<td>Description of your Issue</td>
			<td><textarea name="description" cols="50" rows="10" style="width:500" maxlength="200" required onkeyup="valid(this)" onblur="valid(this)"></textarea>
		</tr>
		<tr>
			<td>
				<button type="submit" class="btn" value="submit">Submit</button>		
			</td>
		</tr>
		<tr>
		<td>
			${Msg}
		</td>
		
		</tr>
</table>


</form>
</div>
		 	
</body>
</html>