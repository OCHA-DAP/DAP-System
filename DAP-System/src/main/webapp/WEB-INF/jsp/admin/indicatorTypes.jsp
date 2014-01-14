<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="ctx" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
</head>
<body>
	<jsp:include page="admin-header.jsp" />
	<h2>Add a new indicator type</h2>

	<form method="POST" action="">
		<label for="code">Code</label>
		<input type="text" name="code" id="code" />
		<label for="name">Name</label>
		<input type="text" name="name" id="name" />
		<label for=unit>Unit</label>
		<input type="text" name="unit" id="unit" />
		<input type="submit" value="submit" />

	</form>

	<h2>List of indicator types</h2>
	<table>
		<tr>
			<th>Id</th>
			<th>Code</th>
			<th>Name</th>
			<th>Unit</th>
		</tr>

		<c:forEach var="indicatorType" items="${it}">
			<tr>
				<td>${indicatorType.id}</td>
				<td>${indicatorType.code}</td>
				<td>${indicatorType.name}</td>
				<td>${indicatorType.unit}</td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>