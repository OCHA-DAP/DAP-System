<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<c:set value="${pageContext.request.contextPath}" var="ctx" scope="request" />
<!DOCTYPE html>
<html ng-app="app">
<head>
<meta charset="UTF-8">
<jsp:include page="css-includes.jsp" />
<jsp:include page="js-includes.jsp">
	<jsp:param name="which" value="dataset" />
</jsp:include>
</head>
<body ng-controller="DatasetCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div>
		<h3>Content of dataset :</h3>
		<c:if test="${it.success}">
	${it.result.license_title}
		<c:forEach var="resource" items="${it.result.resources}">
				<li><a href="${resource.url}">${resource.url}</a></li>
			</c:forEach>
		</c:if>

		<c:if test="${!it.success}">
	fail
	</c:if>
	</div>
	
	<jsp:include page="admin-footer.jsp" />
</body>
</html>