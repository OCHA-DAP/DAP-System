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
	<h2>Add a new SOurce Dictionary</h2>

	<form method="POST" action="">
		<label for="entity">For Entity</label> <select name="source" id="source">
			<c:forEach var="source" items="${it.sources}">
				<option value="${source.id}">${source.name}</option>
			</c:forEach>
		</select> <label for="unnormalizedName">Unnormalized Name</label> <input type="text" name="unnormalizedName" id="unnormalizedName" /> <label
			for="importer">Importer</label> <input type="text" name="importer" id="importer" /> <input type="submit" value="submit" />

	</form>

	<h2>List of region dictionaries</h2>
	<table>
		<tr>
			<th>Source name</th>
			<th>Importer</th>
			<th>Unnormalized Name</th>
		</tr>

		<c:forEach var="sourceDictionary" items="${it.sourceDictionaries}">
			<tr>
				<td>${sourceDictionary.source.name}</td>
				<td>${sourceDictionary.id.importer}</td>
				<td>${sourceDictionary.id.unnormalizedName}</td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>