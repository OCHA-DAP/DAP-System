<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath}" var="ctx" scope="request" />
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
</head>
<body>
<jsp:include page="admin-header.jsp" />
<h2>Delete an import and all the associated indicators</h2>

	<form method="POST" action="./delete">	
  		
  		<label for="importToDeleteId">Type</label>
  		<select name="importToDeleteId" id="importToDeleteId">
  			<c:forEach var="importFromCKAN" items="${it}">
  				<option value="${importFromCKAN.id}">${importFromCKAN.id}</option>
  			</c:forEach>
		</select> 
  	
  		<input type="submit" value="submit" />
  	
  	</form>
	
<h2>List of imports</h2>	
	<table>
		<tr>
			<th>Id</th>
			<th>Resource Id</th>
			<th>Revision Id</th>
			<th>Timestamp</th>
		</tr>
		
		<c:forEach var="importFromCKAN" items="${it}">
			<tr>
				<td>${importFromCKAN.id}</td>
				<td>${importFromCKAN.resourceId}</td>
				<td>${importFromCKAN.revisionId}</td>
				<td>${importFromCKAN.timestamp}</td>
			</tr>
		</c:forEach>
		
	</table>
	
</body>
</html>