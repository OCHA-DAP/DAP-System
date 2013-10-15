<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<c:set value="${pageContext.request.contextPath}" var="ctx" scope="request" />
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
</head>
<body>
<jsp:include page="header.jsp" />
<h2>Detected CKAN Resources</h2>
	
	<table>
		<tr>
			<th>Id</th>
			<th>Revision Id</th>
		</tr>
		
		
		<c:forEach var="ckanResource" items="${it}">
			<tr>
				<td>${ckanResource.id.id}</td>
				<td>${ckanResource.id.revision_id}</td>
			</tr>
		</c:forEach>
		
	</table>
	
</body>
</html>