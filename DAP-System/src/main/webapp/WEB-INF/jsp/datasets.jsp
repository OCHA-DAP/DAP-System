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
<h2>List of datasets in DAP</h2>
	<table>
		<tr>
			<th>name</th>
			<th>status</th>
			<th>type</th>
			<th>Choose a type and flag as to be curated</th>
		</tr>
		
		<c:forEach var="dataset" items="${it}">
			<tr>
				<td>${dataset.name}</td>
				<td>${dataset.status}</td>
				<td>${dataset.type}</td>
				<td>
					<form method="POST" action="">
						<input type="hidden" name="datasetName" value="${dataset.name}" /> 
						<select name="type">
  							<option value="SCRAPPER">SCRAPPER</option>
  							<option value="DUMMY">DUMMY</option>
						</select> 
						<input type="submit" value="flag for curation" />
					</form>
				</td>
			</tr>
		</c:forEach>
		
	</table>
</body>
</html>