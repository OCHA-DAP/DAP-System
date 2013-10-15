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
<h2>List of datasets!</h2>
Logged as : ${it.userId}
			<ul>
				<c:forEach var="dataset" items="${it.datasets}">
					<li><a href="${ctx}/dataset/${dataset}">${dataset}</a></li>
				</c:forEach>
			</ul>
</body>
</html>