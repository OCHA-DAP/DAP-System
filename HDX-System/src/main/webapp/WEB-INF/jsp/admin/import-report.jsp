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
	Work in progress
	<h2>Report for resource id : ${it.id}, revision : ${it.revisionId}</h2>
	<h3>Total stats</h3>
	
	<ul>
		<li>Total Number of Records: ${it.totalNbOfRecords} </li>
		<li>Number of New Records: ${it.nbOfNewRecords} </li>
		<li>Number of Already Existing Records: ${it.nbOfAlreadyExistingRecords} </li>
		<li>Number of Records with Errors: ${it.nbOfRecordsInError} </li>
	</ul>
	
	<table border="1">
		<tr>
			<th>Indicator Code</th>
			<th>Source Code</th>
			<th>Total Number of Records</th>
			<th>Number of New Records</th>
			<th>Number of Already Existing Records</th>
			<th>Number of Records with Errors</th>
		</tr>
		<c:forEach var="entry" items="${it.entries}">
			<tr>
				<td>${entry.key.indicatorCode}</td>
				<td>${entry.key.sourceCode}</td>
				<td>${entry.value.totalNbOfRecords}</td>
				<td>${entry.value.nbOfNewRecords}</td>
				<td>${entry.value.nbOfAlreadyExistingRecords}</td>
				<td>${entry.value.nbOfRecordsInError}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>