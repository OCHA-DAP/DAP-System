<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<c:set value="${pageContext.request.contextPath}" var="ctx" scope="request" />
<!DOCTYPE html>
<html ng-app="app">
<head>
<meta charset="UTF-8">
<jsp:include page="css-includes.jsp" />

</head>
<body >
	<jsp:include page="admin-menu.jsp" />
	<div id="content">
		<form method="post" enctype="multipart/form-data" action="">
			<div>
				<br/><br/>
				<input type="text" id="resourceName" name="resourceName" />
				<input type="file" id="resourceFile" name="resourceFile" />
				<select name="resourceConfigurationId" >
					<c:forEach var="config" items="${it.configs}">
						<option value="${config.id}">${config.name}</option>
					</c:forEach>
				</select>
				<input type="submit" value="Send" /> 
			</div>
		</form>
	</div>
</body>
</html>