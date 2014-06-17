<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="ctx" scope="request" />
<!DOCTYPE html>
<html ng-app="app">
<head>
<meta charset="UTF-8">
<jsp:include page="css-includes.jsp" />
<jsp:include page="js-includes.jsp">
	<jsp:param name="which" value="importsFromCKAN" />
</jsp:include>
</head>
<body ng-controller="ImportsFromCKANCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div id="content">
		<h3>Delete an import and all the associated indicators</h3>
		<div>
			<form method="POST" action="./delete" class="css-form" role="form">
				<div class="form-group" style="width: 100px;">
					<label for="importToDeleteId">Type</label> <select class="form-control" name="importToDeleteId" id="importToDeleteId">
						<c:forEach var="importFromCKAN" items="${it}">
							<option value="${importFromCKAN.id}">${importFromCKAN.id}</option>
						</c:forEach>
					</select>
				</div>
				<button type="submit" class="btn btn-primary btn-custom-default">Submit</button>
			</form>
		</div>
		
		<h3>List of imports</h3>
		<table class="table table-bordered table-hover table-condensed">
			<tr>
				<th>Id</th>
				<th>Resource Id</th>
				<th>Revision Id</th>
				<th>Timestamp</th>
			</tr>

			<c:forEach var="importFromCKAN" items="${it}">
				<tr>
					<td>${importFromCKAN.id}</td>
					<td>${importFromCKAN.resourceId}</td>
					<td>${importFromCKAN.revisionId}</td>
					<td>${importFromCKAN.timestamp}</td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>