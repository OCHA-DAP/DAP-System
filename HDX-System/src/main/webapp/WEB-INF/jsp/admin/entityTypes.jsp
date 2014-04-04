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
	<jsp:param name="which" value="entityTypes" />
	<jsp:param name="i18n" value="true" />
	<jsp:param name="needs" value="entityTypes,search,columnsearch" />
</jsp:include>
</head>
<body ng-controller="EntityTypesCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div>
		<h3>Add entity type</h3>
		<form novalidate name="createResourceForm" class="css-form">
			<table class="table table-bordered table-hover table-condensed">
				<tr style="font-weight: bold">
					<td style="width: 20%">Code</td>
					<td style="width: 60%">Default name</td>
					<td style="width: 20%">Action</td>
				</tr>
				<tr>
					<td><input type="text" class="form-control" placeholder="Code" id="newResource_code" ng-model="newResource.code" required /></td>
					<td><input type="text" class="form-control" placeholder="Name" id="newResource_name" ng-model="newResource.name" required /></td>
					<td style="white-space: nowrap">
						<button class="btn btn-primary btn-custom-default" ng-click="createEntityType(newResource)">Add</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<search title="Entity types"></search>
	<div ng-controller="I18nCtrl">
		<table class="table table-bordered table-hover table-condensed">
			<tr style="font-weight: bold">
				<td style="width: 20%"><a href="" ng-click="predicate='code'; reverse=!reverse">Code</a><columnsearch param="search.code"></columnsearch></td>
				<td style="width: 20%"><a href="" ng-click="predicate='name'; reverse=!reverse">Default name</a><columnsearch param="search.name"></columnsearch></td>
				<td style="width: 40%">Translations</td>
				<td style="width: 20%">Action</td>
			</tr>
			<tr ng-repeat="entityType in entityTypes | filter:search | orderBy:predicate:reverse">
				<td>
					<!-- non editable code --> <span e-name="code" e-form="rowform"> {{ entityType.code }} </span>
				</td>
				<td>
					<!-- editable name --> <span editable-text="entityType.name" e-class="form-control" e-name="name" e-id="name" e-form="rowform" e-required> {{ entityType.name }} </span>
				</td>
				<td>
                    <jsp:include page="i18n.jsp">
                        <jsp:param name="item" value="entityType" />
                        <jsp:param name="collection" value="entityTypes" />
                    </jsp:include>
				</td>
				<td style="white-space: nowrap">
					<!-- form -->
					<form editable-form name="rowform" onbeforesave="updateEntityType($data, entityType.id)" ng-show="rowform.$visible" class="form-buttons form-inline" shown="inserted == entityType">
						<button type="submit" ng-disabled="rowform.$waiting" class="btn btn-primary btn-custom-default">Save</button>
						<button type="button" ng-disabled="rowform.$waiting" ng-click="rowform.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
					</form>
					<div class="buttons" ng-show="!rowform.$visible">
						<button class="btn btn-primary btn-custom-default" ng-click="rowform.$show()">Edit</button>
						<button class="btn btn-danger btn-custom-danger" ng-click="deleteEntityType(entityType.id)">Delete</button>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div ng-show="showTestZone">
		<h3>Test zone</h3>
		<pre>
		<p>EntityTypes : {{ entityTypes | json }}</p>
		<p>Languages : {{ languages | json }}</p>
	</pre>
	</div>
	
</body>
</html>