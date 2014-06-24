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
		<h3>List of imports from CKAN</h3>
		<table class="table table-bordered table-hover table-condensed">
			<tr>
				<th style="width: 25%"><a href="" ng-click="predicate='resourceId'; reverse=!reverse">Resource ID</a></th>
				<th style="width: 25%"><a href="" ng-click="predicate='revisionId'; reverse=!reverse">Revision ID</a></th>
				<th style="width: 25%"><a href="" ng-click="predicate='timestamp'; reverse=!reverse">Timestamp</a></th>
				<th style="width: 15%"><a href="" ng-click="predicate='indicatorCount'; reverse=!reverse">Indicator count</a></th>
				<th style="width: 10%">Action</th>
			</tr>
			<tr ng-repeat="importFromCKAN in importsFromCKAN | orderBy:predicate:reverse">
				<td>{{importFromCKAN.resourceId}}</td>
				<td>{{importFromCKAN.revisionId}}</td>
				<td>{{importFromCKAN.timestamp}}</td>
				<td>{{importFromCKAN.indicatorCount}}</td>
				<td><button class="btn btn-danger btn-custom-danger" ng-click="deleteImport(importFromCKAN.id)">Delete</button></td>
			</tr>
		</table>
	</div>
	<div ng-show="showTestZone">
		<h3>Test zone</h3>
		<pre>
			<p>Imports from CKAN : {{ importsFromCKAN | json }}</p>
		</pre>
	</div>
</body>
</html>