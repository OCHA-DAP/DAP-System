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
	<jsp:param name="which" value="admin-test" />
</jsp:include>
</head>
<body ng-controller="TestCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div>
		<h3>Test page</h3>
		<div class="form-group">
			<label for="resource">Resource</label> <select class="form-control" ng-model="which" ng-options="a.name for a in available"
				ng-class="default" ng-change="go()">
			</select>
		</div>
		<div class="form-group">
			<button class="form-control" ng-class="default" ng-click="show('Sources')">Show sources</button>
			<button class="form-control" ng-class="default" ng-click="show('Entity types')">Show entity types</button>
			<button class="form-control" ng-class="default" ng-click="show('Resource')">Show resource</button>
		</div>
		<pre>
			<p>Available : {{ available | json }}</p>
			<p>Resource : {{ resource | json }}</p>
		</pre>
	</div>
</body>
</html>