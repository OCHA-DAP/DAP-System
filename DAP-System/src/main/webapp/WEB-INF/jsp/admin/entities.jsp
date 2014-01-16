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
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" />
<link href="${ctx}/css/xeditable.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
<script src="${ctx}/js/xeditable.js"></script>

</head>
<body>
	<jsp:include page="admin-header.jsp" />
	<h2>Add a new entity</h2>

	<form method="POST" action="">
		<label for="code">Code</label>
		<input type="text" name="code" id="code" />
		<label for="name">Name</label>
		<input type="text" name="name" id="name" />
		<label for="entityTypeCode">Type</label>
		<select name="entityTypeCode" id="entityTypeCode">
			<c:forEach var="entityType" items="${it.entityTypes}">
				<option value="${entityType.code}">${entityType.name}</option>
			</c:forEach>
		</select>
		<input type="submit" value="submit" />

	</form>

	<h2>List of entity</h2>
	<table>
		<tr>
			<th>Id</th>
			<th>Code</th>
			<th>Default display Name</th>
			<th>Type</th>
		</tr>

		<c:forEach var="entity" items="${it.entities}">
			<tr>
				<td>${entity.id}</td>
				<td>${entity.code}</td>
				<td>${entity.name.defaultValue}</td>
				<td>${entity.type.name}</td>
				<td>
					<form method="POST" action="submitdelete">
						<input type="hidden" name="entityId" value="${entity.id}" />
						<input type="submit" value="Delete" />
					</form>
				</td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>