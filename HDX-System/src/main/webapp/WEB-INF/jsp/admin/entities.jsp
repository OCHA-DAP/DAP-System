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
	<jsp:param name="which" value="entities" />
	<jsp:param name="i18n" value="true" />
	<jsp:param name="needs" value="entityTypes,search,columnsearch" />
</jsp:include>
</head>
<body ng-controller="EntitiesCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div>
		<h3>Add entity</h3>
		<form novalidate name="createResourceForm" class="css-form">
			<table class="table table-bordered table-hover table-condensed">
				<tr style="font-weight: bold">
					<td style="width: 15%">Entity type</td>
					<td style="width: 15%">Code</td>
					<td style="width: 50%">Default name</td>
					<td style="width: 20%">Action</td>
				</tr>
				<tr>
					<td><select class="form-control" id="newResource_entityType" ng-model="newResource.entityType" ng-options="entityType.name for entityType in entityTypes" ng-class="default">
					</select></td>
					<td><input type="text" class="form-control" placeholder="Code" id="newResource_code" ng-model="newResource.code" required /></td>
					<td><input type="text" class="form-control" placeholder="Name" id="newResource_name" ng-model="newResource.name" required /></td>
					<td style="white-space: nowrap">
						<button class="btn btn-primary btn-custom-default" ng-click="createEntity(newResource)">Add</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<search title="Entities"></search>
	<div ng-controller="I18nCtrl">
		<table class="table table-bordered table-hover table-condensed">
			<tr style="font-weight: bold">
				<td style="width: 15%"><a href="" ng-click="predicate='entityType'; reverse=!reverse">Entity type</a></td>
				<td style="width: 15%"><a href="" ng-click="predicate='code'; reverse=!reverse">Code</a><columnsearch param="search.code"></columnsearch></td>
				<td style="width: 15%"><a href="" ng-click="predicate='name'; reverse=!reverse">Default name</a><columnsearch param="search.name"></columnsearch></td>
				<td style="width: 35%">Translations</td>
				<td style="width: 20%">Action</td>
			</tr>
			<tr ng-repeat="entity in entities | filter:search | orderBy:predicate:reverse">
				<td>
					<!-- non editable entity type (from select box) --> <span> {{ showEntityType(entity) }} </span>
				</td>
				<td>
					<!-- non editable code --> <span e-name="code" e-form="rowform"> {{ entity.code }} </span>
				</td>
				<td>
					<!-- editable name --> <span editable-text="entity.name" e-class="form-control" e-name="name" e-id="name" e-form="rowform" e-required> {{ entity.name }} </span>
				</td>
				<td>
                    <jsp:include page="i18n.jsp">
                        <jsp:param name="item" value="entity" />
                        <jsp:param name="collection" value="entities" />
                    </jsp:include>
				</td>
				<td style="white-space: nowrap">
					<!-- form -->
					<form editable-form name="rowform" onbeforesave="updateEntity($data, entity.id)" ng-show="rowform.$visible" class="form-buttons form-inline" shown="inserted == entity">
						<button type="submit" ng-disabled="rowform.$waiting" class="btn btn-primary btn-custom-default">Save</button>
						<button type="button" ng-disabled="rowform.$waiting" ng-click="rowform.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
					</form>
					<div class="buttons" ng-show="!rowform.$visible">
						<button class="btn btn-primary btn-custom-default" ng-click="rowform.$show()">Edit</button>
						<button class="btn btn-danger btn-custom-danger" ng-click="deleteEntity(entity.id)">Delete</button>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div class="pull-left">
		<span>
			<pre> Page : {{pagination.currentPage}} / {{pagination.nbPages}}<br />Items : {{pagination.fromIndex + 1}} - {{pagination.toIndex}} out of {{pagination.totalNumber}}</pre>
		</span>
	</div>
	<div class="pull-right">
		<div class="pull-left">
			<pagination total-items="pagination.totalNumber" items-per-page="pagination.howMuch" page="pagination.currentPage" max-size="pagination.maxSize" boundary-links="true" rotate="false"
				on-select-page="paginate(page)" style="margin-top: 0px; margin-right: 10px;"></pagination>
		</div>
		<div class="pull-right">
			<select class="form-control" ng-model="howMuch" ng-change="loadEntities()">
				<option value="5">5</option>
				<option value="10">10</option>
				<option value="25">25</option>
				<option value="50">50</option>
				<option value="0">All</option>
			</select>
		</div>
	</div>
	<div ng-show="showTestZone">
		<h3>Test zone</h3>
		<pre>
		<p>Pagination : {{ pagination | json }}</p>
		<p>Entity types : {{ entityTypes | json }}</p>
		<p>Entities : {{ entities | json }}</p>
		<p>Languages : {{ languages | json }}</p>
	</pre>
	</div>
	
	<jsp:include page="admin-footer.jsp" />
</body>
</html>