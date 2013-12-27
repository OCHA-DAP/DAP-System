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

	
<h2>List of region dictionaries</h2>	
	<table>
		<tr>
			<th>Entity Type</th>
			<th>Entity name</th>
			<th>Importer</th>
			<th>Unnormalized Name</th>
			<th>Action</th>
		</tr>
		
		<c:forEach var="regionDictionary" items="${it.regionDictionaries}">
			<tr>
				<td>${regionDictionary.entity.type.name}</td>
				<td>${regionDictionary.entity.name}</td>
				<td>${regionDictionary.id.importer}</td>
				<td>${regionDictionary.id.unnormalizedName}</td>
				<td>
					<form method="POST" action="/dictionaries/cjtest">
						<input type="hidden" name="unnormalizedName" value="${regionDictionary.id.unnormalizedName}" />
						<input type="hidden" name="importer" value="${regionDictionary.id.importer}" />
						<!-- TODO Should this be regionDictionary.entity?  -->
						<!-- input type="hidden" name="entity" value="${entity}" --> 
						<input type="submit" value="Delete" />
					</form>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	
</body>
</html>