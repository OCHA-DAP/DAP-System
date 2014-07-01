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
	<jsp:param name="needs" value="importers,search" />
</jsp:include>
</head>
<body ng-controller="DatasetsCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div class="modal fade" id="chooseImporterAndConfigModal" tabindex="-1" role="dialog" aria-labelledby="chooseImporterAndConfigModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" ng-click="resetSelectedConfiguration()" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="chooseImporterAndConfigModalLabel">Choose importer and configuration</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="source">Please choose an importer to associate with dataset <b>{{selectedDataset.name}}</b></label> 
						<select style="margin-bottom: 6px;" class="form-control" id="importerForDataset" ng-model="selectedImporter" ng-options="i.name as i.name for i in importers" ng-class="default" ng-change="resetSelectedConfiguration()">
						</select>
						<select class="form-control" id="configurationForDataset" ng-model="selectedConfiguration" ng-disabled="selectedImporter !== 'SCRAPER_CONFIGURABLE'" ng-options="c.id as c.name for c in ckan.configurations" ng-class="default">
						</select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="resetSelectedConfiguration()">Cancel</button>
					<button type="button" class="btn btn-primary" ng-click="setImportAndConfiguration()" ng-disabled="setImportAndConfigurationDisabled()">OK</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="importerAndConfigChosenModal" tabindex="-1" role="dialog" aria-labelledby="importerAndConfigChosenModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" ng-click="resetSelectedConfiguration()" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="importerAndConfigChosenModalLabel">Importer and configuration chosen.</h4>
				</div>
				<div class="modal-body">
					<span>Importer and configuration chosen for dataset <b>{{selectedDataset.name}}</b>.
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" ng-click="resetSelectedConfiguration()">Close</button>
				</div>
			</div>
		</div>
	</div>
	<div id="content">
		<search title="List of datasets in CKAN"></search>
		<form class="form-buttons form-inline">
			<table class="table table-bordered table-hover table-condensed">
				<tr>
					<th style="width: 40%"><a href="" ng-click="predicate='title'; reverse=!reverse">Title (name)</a></th>
					<th style="width: 10%"><a href="" ng-click="predicate='status'; reverse=!reverse">Status</a>&nbsp;<span style="font-size: smaller;">
							(<a href="" ng-click="toggleShowHideIgnored()">{{showHideIgnoredMessage}}</a>)
						</span></th>
					<th style="width: 20%"><a href="" ng-click="predicate='type'; reverse=!reverse">Importer and configuration</a></th>
					<th style="width: 30%">Action</th>
				</tr>
				<tr ng-repeat="dataset in ckan.datasets | filter:search | filter:filterDatasets | orderBy:predicate:reverse">
					<td>{{dataset.title}} ({{dataset.name}})</td>
					<td>{{dataset.status}}</td>
					<td>{{showImporter(dataset)}}
					</td>
					<td>
						<button class="btn btn-primary btn-custom-default" ng-click="importAndConfigSetup(dataset)" data-toggle="modal" data-target="#chooseImporterAndConfigModal">Choose importer & config</button>
						<button class="btn btn-primary btn-custom-default" ng-click="flagForCuration(dataset.name)" ng-disabled="'' === dataset.type || ('SCRAPER_CONFIGURABLE' === dataset.type && (!dataset.configuration || !dataset.configuration.name))">Flag for curation</button>
						<button class="btn btn-primary btn-custom-default" ng-click="ignoreDataset(dataset.name)" ng-disabled="dataset.status === 'IGNORED'">Ignore</button></td>
				</tr>
			</table>
		</form>
	</div>
	<div ng-show="showTestZone">
		<h3>Test zone</h3>
		<pre>
			<p>Importers : {{ importers | json }}</p>
			<p>Datasets : {{ ckan | json }}</p>
		</pre>
	</div>
</body>
</html>