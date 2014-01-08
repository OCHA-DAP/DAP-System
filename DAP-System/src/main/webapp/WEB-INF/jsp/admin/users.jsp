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
	<h2>Add a new User</h2>

	<form method="POST" action="">
		<label for="id">Id</label>
		<input type="text" name="id" id="id" />

		<label for="password">Password</label>
		<input type="password" name="password" id="password" />
		
		<label for="password2">Confirm password</label>
		<input type="password" name="password2" id="password2" />

		<label for="ckanApiKey">CKAN Api Key</label>
		<input type="text" name="ckanApiKey" id="ckanApiKey" />

		<label for="role">Role</label>
		<select name="role" id="role">
				<option value="admin">admin</option>
				<option value="api">api</option>
		</select>
		<input type="submit" value="submit" />

	</form>

	<h2>List of user</h2>
	<table>
		<tr>
			<th>Name</th>
			<th>Role</th>
		</tr>

		<c:forEach var="user" items="${it}">
			<tr>
				<td>${user.id}</td>
				<td>${user.role}</td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>