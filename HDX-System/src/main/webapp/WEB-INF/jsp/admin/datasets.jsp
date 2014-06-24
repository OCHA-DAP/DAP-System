<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<c:set value="${pageContext.request.contextPath}" var="ctx" scope="request" />
<!DOCTYPE html>
<html ng-app="app">
<head>
<meta charset="UTF-8">
<jsp:include page="css-includes.jsp" />
<jsp:include page="js-includes.jsp">
	<jsp:param name="which" value="datasets" />
</jsp:include>
</head>
<body ng-controller="DatasetsCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div id="content">
		<h3>List of datasets in CKAN</h3>
		<table class="table table-bordered table-hover table-condensed">
			<tr>
				<th style="width:40%"><a href="" ng-click="predicate='title'; reverse=!reverse">Title (name)</a></th>
				<th style="width:10%"><a href="" ng-click="predicate='status'; reverse=!reverse">Status</a>&nbsp;<span style="font-size: smaller;">(<a href="" ng-click="toggleShowHideIgnored()">{{showHideIgnoredMessage}}</a>)</span></th>
				<th style="width:10%"><a href="" ng-click="predicate='configuration.name'; reverse=!reverse">Configuration</a></th>
				<th style="width:40%">Action</th>
			</tr>
			<tr ng-repeat="dataset in ckan.datasets | filter:filterDatasets | orderBy:predicate:reverse">
				<td>{{dataset.title}} ({{dataset.name}})</td>
				<td>{{dataset.status}}</td>
				<td>{{dataset.configuration.name}}</td>
				<td>
					<form class="form-buttons form-inline">
						<select class="form-control" ng-model="dataset.newConfiguration" ng-options="c.name for c in ckan.configurations" ng-class="default")>
							<option value=""></option>
						</select>
						<button class="btn btn-primary btn-custom-default" ng-click="flagForCuration(dataset.name)" ng-disabled="dataset.newConfiguration == null">Flag for curation</button>
						<button class="btn btn-primary btn-custom-default" ng-click="ignoreDataset(dataset.name)" ng-disabled="dataset.status === 'IGNORED'">Ignore</button>
					</form>
				</td>
			</tr>
		</table>
	</div>
	<div ng-show="showTestZone">
		<h3>Test zone</h3>
		<pre>
			<p>Datasets : {{ ckan | json }}</p>
		</pre>
	</div>
</body>
</html>