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
	<h2>Report for resource id : ${it.id}, revision : ${it.revisionId}</h2>

	<h3>  Validator : ${it.validator} Status : ${it.status} </h3>
	
	<ul>
		<c:forEach var="entry" items="${it.entries}">
			<li class="${entry.status}">${entry.status} - ${entry.message}</li>
		</c:forEach>
	</ul>
</body>
</html>