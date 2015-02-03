<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="java.util.ArrayList"%>     
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

<div class="hero-unit" style="position:relative;left:200px;height:500px;width:800px;overflow:auto">
<h2>Syslog</h2>
<textarea rows="20" style="width:800" readonly="readonly">
<c:forEach var="i" items="${audits}">
	AUDIT ID:<c:out value="${i.getAuditID()}"/>
	AUDIT TYPE:<c:out value="${i.getAuditType()}"/>
	AUDIT CREATOR:<c:out value="${i.getUserID()}"/>
	AUDIT TIME:<c:out value="${i.getTimeOfAudit()}"/>
	AUDIT TRAIL:<c:out value="${i.getAuditTrail()}"/>
</c:forEach>
</textarea>
</div>
</body>
</html>