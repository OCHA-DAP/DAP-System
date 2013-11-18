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
<jsp:include page="header.jsp" />
<h2>Add a new source</h2>

	<form method="POST" action="">	
		<label for="code">Code</label>
  		<input type="text" name="code" id="code" />
  	
  		<label for="name">Name</label>
  		<input type="text" name="name" id="name"/>
  	
  		<input type="submit" value="submit" />
  	
  	</form>
	
<h2>List of sources</h2>	
	<table>
		<tr>
			<th>Id</th>
			<th>Code</th>
			<th>Name</th>
		</tr>
		
		<c:forEach var="source" items="${it}">
			<tr>
				<td>${source.id}</td>
				<td>${source.code}</td>
				<td>${source.name}</td>
			</tr>
		</c:forEach>
		
	</table>
	
</body>
</html>