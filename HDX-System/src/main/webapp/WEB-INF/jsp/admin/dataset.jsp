<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<c:set value="${pageContext.request.contextPath}" var="ctx" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
</head>
<body>
	<jsp:include page="admin-header.jsp" />
	<h2>Content of dataset :</h2>
	<c:if test="${it.success}">
	${it.result.license_title}
		<c:forEach var="resource" items="${it.result.resources}">
			<li><a href="${resource.url}">${resource.url}</a></li>
		</c:forEach>
	</c:if>

	<c:if test="${!it.success}">
	fail
	</c:if>
</body>
</html>