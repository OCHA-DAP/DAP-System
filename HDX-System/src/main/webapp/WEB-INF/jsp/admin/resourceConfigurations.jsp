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
	<jsp:param name="which" value="resourceConfigurations" />
</jsp:include>

</head>
<body ng-controller="ResourceConfigurationsCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div>
		<h3>Add Resource Configuration</h3>
		<form novalidate name="createResourceForm" class="css-form">
			<table class="table table-bordered table-hover table-condensed">
				<tr style="font-weight: bold">
					<td style="width: 80%">Name</td>
					<td style="width: 20%">Action</td>
				</tr>
				<tr>
					<td><input type="text" class="form-control" placeholder="Name" id="newResource_name" ng-model="newResource.name" required /></td>
					<td style="white-space: nowrap">
						<button class="btn btn-primary btn-custom-default" ng-click="createRC(newResource)">Add</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<h3>Resource Configurations</h3>
	<div>
		<table class="table table-bordered table-hover table-condensed">
			<tr style="font-weight: bold">
				<td style="width: 20%"><a href="" ng-click="predicate='id'; reverse=!reverse">Id</td>
				<td style="width: 60%"><a href="" ng-click="predicate='name'; reverse=!reverse">Name</td>
				<td style="width: 20%">Action</td>
			</tr>
			<tr ng-repeat="rc in resourceConfigurations | orderBy:predicate:reverse">
				<td>
					<!-- non editable code  -->
					<span > {{ rc.id }} </span>
				</td>
				<td>
					<!-- editable native name  -->
					<span > {{ rc.name }} </span>
				</td>
				<td style="white-space: nowrap">
					<div class="buttons" ng-show="!rowform.$visible">
						<button class="btn btn-primary btn-custom-default" ng-click="editRC(rc.id)" >Edit</button>
						<button class="btn btn-danger btn-custom-danger" ng-click="deleteRC(rc.id)">Delete</button>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div ng-show="showTestZone">
		<h3>Test zone</h3>
		<pre>
			<p>Languages : {{ languages | json }}</p>
		</pre>
	</div>
	
	<jsp:include page="admin-footer.jsp" />
</body>
</html>