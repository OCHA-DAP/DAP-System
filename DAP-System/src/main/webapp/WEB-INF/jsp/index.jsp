<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<h2>List of datasets!</h2>
Logged as : ${it.userId}
			<ul>
				<c:forEach var="dataset" items="${it.datasets}">
					<li>${dataset}</li>
				</c:forEach>
			</ul>
</body>
</html>