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
	<jsp:param name="which" value="users" />
	<jsp:param name="needs" value="roles" />
</jsp:include>

</head>
<body ng-controller="UsersCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div>
		<h3>Add user</h3>
		<form novalidate name="createResourceForm" class="css-form">
			<table class="table table-bordered table-hover table-condensed">
				<tr style="font-weight: bold">
					<td style="width: 16%">Id</td>
					<td style="width: 16%">Role</td>
					<td style="width: 16%">Password</td>
					<td style="width: 16%">Password confirmation</td>
					<td style="width: 16%">CKAN API key</td>
					<td style="width: 20%">Action</td>
				</tr>
				<tr>
					<td><input type="text" class="form-control" placeholder="Id" id="newResource_id" ng-model="newResource.id" required /></td>
					<td><select class="form-control" id="newResource_role" ng-model="newResource.role" ng-options="r.value as r.text for r in roles" ng-class="default" required>
					</select></td>
					<td><input type="password" class="form-control" id="newResource_password" ng-model="newResource.password" required /></td>
					<td><input type="password" class="form-control" id="newResource_password2" ng-model="newResource.password2" required /></td>
					<td><input type="text" class="form-control" placeholder="CKAN API key" id="newResource_ckanApiKey" ng-model="newResource.ckanApiKey" required /></td>
					<td style="white-space: nowrap">
						<button class="btn btn-primary btn-custom-default" ng-click="createUser(newResource)">Add</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<h3>Users</h3>
	<div>
		<table class="table table-bordered table-hover table-condensed">
			<tr style="font-weight: bold">
				<td style="width: 16%"><a href="" ng-click="predicate='id'; reverse=!reverse">Id</td>
				<td style="width: 16%"><a href="" ng-click="predicate='role'; reverse=!reverse">Role</td>
				<td style="width: 16%">Password</td>
				<td style="width: 16%">Password confirmation</td>
				<td style="width: 16%">CKAN API key</td>
				<td style="width: 20%">Action</td>
			</tr>
			<tr ng-repeat="user in users | orderBy:predicate:reverse">
				<td>
					<!-- non editable id --> <span e-name="id" e-form="rowform"> {{ user.id }} </span>
				</td>
				<td>
					<!-- editable role --> <span editable-select="user.role" e-class="form-control" e-name="role" e-form="rowform" e-ng-options="r.value as r.text for r in roles"> {{ showRole(user) }} </span> 
				</td>
				<td><span editable-text="user.password" e-class="form-control" e-name="password" e-id="password_field" e-form="rowform" onshow="customize('password_field')"> {{ user.password }} </span></td>
				<td><span editable-text="user.password2" e-class="form-control" e-name="password2" e-id="password2_field" e-form="rowform" onshow="customize('password2_field')"> {{ user.password2 }} </span></td>
				<td><span editable-text="user.ckanApiKey" e-class="form-control" e-name="ckanApiKey" e-form="rowform"> {{ user.ckanApiKey }} </span></td>
				<td style="white-space: nowrap">
					<!-- form -->
					<form editable-form name="rowform" onbeforesave="updateUser($data, user.id)" ng-show="rowform.$visible" class="form-buttons form-inline" shown="inserted == user">
						<button type="submit" ng-disabled="rowform.$waiting" class="btn btn-primary btn-custom-default">Save</button>
						<button type="button" ng-disabled="rowform.$waiting" ng-click="user.setEditing(false);rowform.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
					</form>
					<div class="buttons" ng-show="!rowform.$visible">
						<button class="btn btn-primary btn-custom-default" ng-click="user.setEditing(true);rowform.$show()">Edit</button>
						<button class="btn btn-danger btn-custom-danger" ng-click="deleteUser(user.id)">Delete</button>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div ng-show="showTestZone">
		<pre>
			<p>Roles : {{ roles | json }}</p>
			<p>Users : {{ users | json }}</p>
		</pre>
	</div>
	
	<jsp:include page="admin-footer.jsp" />
</body>
</html>