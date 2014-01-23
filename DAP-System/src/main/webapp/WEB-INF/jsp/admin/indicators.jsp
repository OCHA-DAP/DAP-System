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
	<h2>Add a new indicator</h2>

	<form method="POST" action="">
		<label for="sourceCode">Source</label>
		<select name="sourceCode" id="sourceCode">
			<c:forEach var="source" items="${it.sources}">
				<option value="${source.code}">${source.name}</option>
			</c:forEach>
		</select>
		<label for="entityId">Entity</label>
		<select name="entityId" id="entityId">
			<c:forEach var="entity" items="${it.entities}">
				<option value="${entity.id}">${entity.name.defaultValue}(${entity.type.name})</option>
			</c:forEach>
		</select>
		<label for="indicatorTypeCode">Indicator type</label>
		<select name="indicatorTypeCode" id="indicatorTypeCode">
			<c:forEach var="indicatorType" items="${it.indicatorTypes}">
				<option value="${indicatorType.code}">${indicatorType.name.defaultValue}</option>
			</c:forEach>
		</select>
		<br />
		<label for="start">Start date (yyyy-mm-dd)</label>
		<input type="text" name="start" id="start" />
		<label for="end">End date (yyyy-mm-dd)</label>
		<input type="text" name="end" id="end" />
		<label for="periodicity">Periodicity</label>
		<select name="periodicity" id="periodicity">
			<c:forEach var="periodicity" items="${it.periodicities}">
				<option value="${periodicity}">${periodicity}</option>
			</c:forEach>
		</select>
		<label for="valueType">Value Type </label>
		<select name="valueType" id="valueType">
			<c:forEach var="valueType" items="${it.valueTypes}">
				<option value="${valueType}">${valueType}</option>
			</c:forEach>
		</select>
		<br />
		<label for="value">Value</label>
		<input type="text" name="value" id="value" />
		<label for="initialValue">Initial value</label>
		<input type="text" name="initialValue" id="initialValue" />
		<input type="submit" value="submit" />

	</form>

	<h2>List of last 100 indicators</h2>
	<table>
		<tr>
			<th>Id</th>
			<th>Source</th>
			<th>Entity</th>
			<th>Type</th>
			<th>Start</th>
			<th>End</th>
			<th>Periodicity</th>
			<th>Value</th>
			<th>Initial value</th>
			<th>Import from CKAN</th>
			<th>Action</th>
		</tr>

		<c:forEach var="indicator" items="${it.indicators}">
			<tr>
				<td>${indicator.id}</td>
				<td>${indicator.source.name}</td>
				<td>${indicator.entity.name.defaultValue}</td>
				<td>${indicator.type.name.defaultValue}</td>
				<td><fmt:formatDate value="${indicator.start}" pattern="yyyy-MM-dd" /></td>
				<td><fmt:formatDate value="${indicator.end}" pattern="yyyy-MM-dd" /></td>
				<td>${indicator.periodicity}</td>
				<td>${indicator.value}</td>
				<td>${indicator.initialValue}</td>
				<td>${indicator.importFromCKAN.id}</td>
				<td>
					<form method="POST" action="submitdelete">
						<input type="hidden" name="indicatorId" value="${indicator.id}" />
						<input type="submit" value="Delete" />
					</form>
				</td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>