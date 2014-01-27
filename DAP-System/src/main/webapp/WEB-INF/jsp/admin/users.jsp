<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="ctx" scope="request" />
<!DOCTYPE html>
<html ng-app="app">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/css/xeditable.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<script type="text/javascript">
  var dapContextRoot = "${ctx}";
</script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.7/angular.min.js"></script>
<script src="${ctx}/js/xeditable.js"></script>
<script src="${ctx}/js/users.js"></script>

</head>
<body ng-controller="UsersCtrl">
	<jsp:include page="admin-header.jsp" />
	<div>
		<h3>Add user</h3>
		<form novalidate name="addUserForm" class="css-form">
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
					<td><input type="text" ng-model="newuser.id" required /></td>
					<td><select ng-model="newuser.role" ng-options="r.value as r.text for r in roles" ng-class="default" required>
					</select></td>
					<td><input type="password" ng-model="newuser.password" required /></td>
					<td><input type="password" ng-model="newuser.password2" required /></td>
					<td><input type="text" ng-model="newuser.ckanApiKey" required /></td>
					<td style="white-space: nowrap">
						<button class="btn btn-primary" ng-click="addUser(newuser)">Add</button>
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
					<!-- editable role --> <span editable-select="user.role" e-name="role" e-form="rowform" e-ng-options="r.value as r.text for r in roles"> {{ showRole(user) }} 
				</td>
				<td><!-- FIXME This should be changed to a real password field when available in xeditable --><span editable-text="user.password" e-name="password" e-id="password_field" e-form="rowform" onshow="customize('password_field')"> {{ user.password }} </span></td>
				<td><!-- FIXME This should be changed to a real password field when available in xeditable --><span editable-text="user.password2" e-name="password2" e-id="password2_field" e-form="rowform" onshow="customize('password2_field')"> {{ user.password2 }} </span></td>
				<td><span editable-text="user.ckanApiKey" e-name="ckanApiKey" e-form="rowform"> {{ user.ckanApiKey }} </span></td>
				<td style="white-space: nowrap">
					<!-- form -->
					<form editable-form name="rowform" onbeforesave="saveUser($data, user.id)" ng-show="rowform.$visible" class="form-buttons form-inline" shown="inserted == user">
						<button type="submit" ng-disabled="rowform.$waiting" class="btn btn-primary">Save</button>
						<button type="button" ng-disabled="rowform.$waiting" ng-click="user.setEditing(false);rowform.$cancel()" class="btn btn-default">Cancel</button>
					</form>
					<div class="buttons" ng-show="!rowform.$visible">
						<button class="btn btn-primary" ng-click="user.setEditing(true);rowform.$show()">Edit</button>
						<button class="btn btn-danger" ng-click="removeUser(user.id)">Delete</button>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<!-- h3>Test zone</h3>
	<pre>
		<p>Roles : {{ roles | json }}</p>
		<p>Users : {{ users | json }}</p>
	</pre>
	<h2>Add a new User</h2>

	<form method="POST" action="">
		<label for="id">Id</label> <input type="text" name="id" id="id" /> <label for="password">Password</label> <input type="password" name="password" id="password" /> <label for="password2">Confirm
			password</label> <input type="password" name="password2" id="password2" /> <label for="ckanApiKey">CKAN Api Key</label> <input type="text" name="ckanApiKey" id="ckanApiKey" /> <label for="role">Role</label>
		<select name="role" id="role">
			<option value="admin">admin</option>
			<option value="api">api</option>
		</select> <input type="submit" value="submit" onclick="alert('test'); return true;" />

	</form>

	<h2>List of user</h2>
	<table>
		<tr>
			<th>Name</th>
			<th>Role</th>
		</tr>

		<c:forEach var="user" items="${it}">
			<tr>
				<td>${user.id}</td>
				<td>${user.role}</td>
			</tr>
		</c:forEach>

	</table -->

</body>
</html>