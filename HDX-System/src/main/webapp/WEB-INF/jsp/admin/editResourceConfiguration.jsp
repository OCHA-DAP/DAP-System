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
	<jsp:param name="which" value="editResourceConfiguration" />
	<jsp:param name="needs" value="sources,indicatorTypes,entities,importers" />
</jsp:include>
</head>
<body ng-controller="EditResourceConfigurationCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div id="content">
		<h3>{{ editResourceConfiguration.name }} configuration</h3>
		<div class="row">
			<div class="col-sm-2">
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a href="#mainconfig" data-toggle="tab">Configuration name</a></li>
					<li><a href="#file" data-toggle="tab">File pre-validation</a></li>
					<li><a href="#dataseries" data-toggle="tab">Data Series Configuration</a></li>
					<li><a href="#region" data-toggle="tab">Region Dictionaries</a></li>
					<li><a href="#source" data-toggle="tab">Sources Dictionaries</a></li>
					<li><a href="#indicatortype" data-toggle="tab">Indicator Type Dictionaries</a></li>
					<li><a href="${ctx}/admin/misc/configurations/">Back to Configurations</a></li>
				</ul>
			</div>
			<div class="col-sm-10">
				<div class="tab-content">
					<div class="tab-pane active" id="mainconfig">
						<h4>Configuration name</h4>
						<!-- editable config name  -->
						<input class="form-control" disabled type="text" style="width: 300px;" e-style="width:300px;" editable-text="editResourceConfiguration.name" e-class="form-control" e-name="name" e-form="ercform"
							value="{{ editResourceConfiguration.name }}" e-required></input>
						<form editable-form name="ercform" onbeforesave="updateRC($data, editResourceConfiguration.id)" ng-show="ercform.$visible" class="form-buttons form-inline"
							shown="inserted == editResourceConfiguration && ercFormVisibility" style="margin-top: 10px;">
							<button type="submit" ng-disabled="ercform.$waiting" class="btn btn-primary btn-custom-default">Save</button>
							<button type="button" ng-disabled="ercform.$waiting" ng-click="ercform.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
						</form>
						<div class="buttons" ng-show="!ercform.$visible" style="margin-top: 10px;">
							<button class="btn btn-primary btn-custom-default" ng-click="ercform.$show()">Edit</button>
						</div>
					</div>
					<div class="tab-pane" id="file">
						<h4>Minimum number of columns</h4>
						<!-- editable number of columns -->
						<input class="form-control" disabled type="text" style="width: 300px;" e-style="width:300px;" editable-text="minimumNumberOfColumns" e-class="form-control" e-name="minimumNumberOfColumns"
							e-form="minimumNumberOfColumnsForm" value="{{ minimumNumberOfColumns }}"></input>
						<form editable-form name="minimumNumberOfColumnsForm" onbeforesave="updateGC($data.minimumNumberOfColumns, minimumNumberOfColumnsKey, minimumNumberOfColumnsId)"
							ng-show="minimumNumberOfColumnsForm.$visible" class="form-buttons form-inline" shown="inserted == editResourceConfiguration && minimumNumberOfColumnsFormVisibility" style="margin-top: 10px;">
							<button type="submit" ng-disabled="minimumNumberOfColumnsForm.$waiting" class="btn btn-primary btn-custom-default">Save</button>
							<button type="button" ng-disabled="minimumNumberOfColumnsForm.$waiting" ng-click="minimumNumberOfColumnsForm.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
						</form>
						<div class="buttons" ng-show="!minimumNumberOfColumnsForm.$visible" style="margin-top: 10px;">
							<button class="btn btn-primary btn-custom-default" ng-click="minimumNumberOfColumnsForm.$show()">Edit</button>
						</div>

						<h4 style="margin-top: 36px;">Indicator type codes</h4>
						<!-- editable indicator type codes -->
						<!-- input class="form-control" disabled type="text" style="width: 300px;" e-style="width:300px;" editable-text="allowedIndicatorTypeCodes" e-class="form-control" e-name="allowedIndicatorTypeCodes"
							e-form="allowedIndicatorTypeCodesForm" value="{{ allowedIndicatorTypeCodes }}"></input>
						<form editable-form name="allowedIndicatorTypeCodesForm" onbeforesave="updateGC($data.allowedIndicatorTypeCodes, allowedIndicatorTypeCodesKey, allowedIndicatorTypeCodesId)"
							ng-show="allowedIndicatorTypeCodesForm.$visible" class="form-buttons form-inline" shown="inserted == editResourceConfiguration && allowedIndicatorTypeCodesFormVisibility"
							style="margin-top: 10px;">
							<button type="submit" ng-disabled="allowedIndicatorTypeCodesForm.$waiting" class="btn btn-primary btn-custom-default">Save</button>
							<button type="button" ng-disabled="allowedIndicatorTypeCodesForm.$waiting" ng-click="allowedIndicatorTypeCodesForm.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
						</form>
						<div class="buttons" ng-show="!allowedIndicatorTypeCodesForm.$visible" style="margin-top: 10px;">
							<button class="btn btn-primary btn-custom-default" ng-click="allowedIndicatorTypeCodesForm.$show()">Edit</button>
						</div -->
						<div class="pull-left" style="width: 200px;">
							<h5>Available</h5>
							<select class="form-control" id="itFrom" ng-model="availableIndicatorTypeCodesSelected" ng-options="indicatorType.code for indicatorType in editResourceConfiguration.indicatorTypes | filter:filterAvailableITC | orderBy:'code'" ng-class="default" multiple size="10"></select>
						</div>
						<div class="pull-left" style="width: 100px; text-align: center; padding-top: 50px;">
							<button class="btn btn-primary btn-custom-default" ng-disabled="0 === availableIndicatorTypeCodesSelected.length" ng-click="move('right')">&#8594;</button>
							<br>
							<button class="btn btn-primary btn-custom-default" ng-disabled="0 === allowedIndicatorTypeCodesSelected.length" ng-click="move('left')" style="margin-top: 24px;">&#8592;</button>
						</div>
						<div class="pull-left" style="width: 200px;">
							<h5>Allowed</h5>
							<select class="form-control" id="itTo" ng-model="allowedIndicatorTypeCodesSelected" ng-options="indicatorType for indicatorType in allowedIndicatorTypeCodes | orderBy:''" ng-class="default" multiple size="10"></select>
						</div>
					</div>
					<div class="tab-pane" id="dataseries">
						<h4>Add Data Series Configuration</h4>
						<form novalidate name="createIndicatorResourceForm" class="css-form">
							<table class="table table-bordered table-hover table-condensed">
								<tr style="font-weight: bold">
									<td style="width: auto">Indicator Type</td>
									<td style="width: auto">Source</td>
									<td style="width: auto">Indicator Configuration</td>
									<td style="width: auto">Value</td>
									<td style="width: auto">Action</td>
								</tr>
								<tr>
									<td><select class="form-control" id="newIndRC_indTypeCode" ng-model="newIndRC.indTypeCode" ng-options="indicatorType.code for indicatorType in editResourceConfiguration.indicatorTypes"
										ng-class="default"></select></td>
									<td><select class="form-control" id="newIndRC_sourceCode" ng-model="newIndRC.sourceCode" ng-options="source.code for source in editResourceConfiguration.sources" ng-class="default"></select>
									</td>
									<td><select class="form-control" id="newIndRC_indConfKey" ng-model="newIndRC.key"
										ng-options="indicatorConfiguration.key for indicatorConfiguration in editResourceConfiguration.availableIndConfs" ng-class="default"></select></td>
									<td><input type="text" class="form-control" placeholder="Value" id="newIndRC_value" ng-model="newIndRC.value" required /></td>
									<td style="white-space: nowrap">
										<button class="btn btn-primary btn-custom-default" ng-click="addIndicatorRC(newIndRC,editResourceConfiguration)">Add</button>
									</td>
								</tr>
							</table>
						</form>
						<h4>List of Data Series Configurations</h4>
						<table class="table table-bordered table-hover table-condensed">
							<tr style="font-weight: bold">
								<td style="width: auto"><a href="" ng-click="predicate='indType'; reverse=!reverse">Indicator Type </td>
								<td style="width: auto"><a href="" ng-click="predicate='src'; reverse=!reverse">Source </td>
								<td style="width: auto"><a href="" ng-click="predicate='key'; reverse=!reverse">Indicator Configuration </td>
								<td style="width: auto"><a href="" ng-click="predicate='value'; reverse=!reverse">Value </td>
								<td style="width: auto">Action</td>
							</tr>
							<tr ng-repeat="ic in editResourceConfiguration.indicatorConfigurations | orderBy:predicate:reverse">
								<td><span editable-select="ic.indTypeId" e-class="form-control" e-name="indType" e-id="indType" e-form="icform"
										e-ng-options="v.id as v.code for v in editResourceConfiguration.indicatorTypes" e-required> {{ showIndicatorType(ic) }} </span></td>
								<td><span editable-select="ic.srcId" e-class="form-control" e-name="src" e-id="src" e-form="icform" e-ng-options="v.id as v.code for v in editResourceConfiguration.sources"> {{
										showSources(ic) }} </span></td>
								<td>
									<!-- non editable code  --> <span e-name="key" e-form="icform"> {{ ic.key }} </span>
								</td>
								<td class="td_value">
									<div>
										<!-- editable native name  -->
										<span editable-text="ic.value" e-class="form-control" e-name="value" e-form="icform" e-required> {{ ic.value }} </span>
									</div>
								</td>
								<td style="white-space: nowrap">
									<form editable-form name="icform" onbeforesave="updateIC($data, ic)" ng-show="icform.$visible" class="form-buttons form-inline" shown="inserted == ic">
										<button type="submit" ng-disabled="icform.$waiting" class="btn btn-primary btn-custom-default">Save</button>
										<button type="button" ng-disabled="icform.$waiting" ng-click="icform.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
									</form>
									<div class="buttons" ng-show="!icform.$visible">
										<button class="btn btn-primary btn-custom-default" ng-click="icform.$show()">Edit</button>
										<button class="btn btn-danger btn-custom-danger" ng-click="deleteIC(editResourceConfiguration.id,ic.id)">Delete</button>
									</div>
								</td>
							</tr>
						</table>
					</div>
					<div class="tab-pane" id="region" ng-controller="RegionDictionariesCtrl">
						<h4>Add a new Region Dictionary</h4>
						<form novalidate name="createResourceForm" class="css-form">
							<table class="table table-bordered table-hover table-condensed">
								<tr style="font-weight: bold">
									<td style="width: auto">Entity</td>
									<td style="width: auto">Unnormalized Name</td>
									<td style="width: auto">Action</td>
								</tr>
								<tr>
									<td><select class="form-control" id="newResource_entity" ng-model="newResource.entityId" ng-options="entity.id as entity.entityTypeName+'/'+entity.name for entity in entities"
										ng-class="default" required></select></td>
									<td><input type="text" class="form-control" placeholder="Value" id="newResource_unnormalizedName" ng-model="newResource.unnormalizedName" required /></td>
									<td style="white-space: nowrap">
										<button class="btn btn-primary btn-custom-default" ng-click="createDictionary(newResource)">Add</button>
									</td>
								</tr>
							</table>
						</form>
						<h4>List of region dictionaries</h4>
						<table class="table table-bordered table-hover table-condensed">
							<tr>
								<th>Entity Type</th>
								<th>Entity name</th>
								<th>Unnormalized Name</th>
								<th>Action</th>
							</tr>
							<tr ng-repeat="rd in regionDictionaries ">
								<td>{{ rd.entityType }}</td>
								<td>{{ rd.entityName }}</td>
								<td>{{ rd.unnormalizedName }}</td>
								<td>
									<button class="btn btn-danger btn-custom-danger" ng-click="deleteDictionary(rd.id)">Delete</button>
								</td>
							</tr>
						</table>

					</div>
					<div class="tab-pane" id="source" ng-controller="SourceDictionariesCtrl">
						<h4>Add a new Source Dictionary</h4>
						<form novalidate name="createResourceForm" class="css-form">
							<table class="table table-bordered table-hover table-condensed">
								<tr style="font-weight: bold">
									<td style="width: auto">Source</td>
									<td style="width: auto">Unnormalized Name</td>
									<!-- td style="width: auto">Importer</td -->
									<td style="width: auto">Action</td>
								</tr>
								<tr>
									<td><select class="form-control" id="newResource_source" ng-model="newResource.sourceId" ng-options="source.id as source.code for source in sources" ng-class="default"></select></td>
									<td><input type="text" class="form-control" placeholder="Value" id="newResource_unnormalizedName" ng-model="newResource.unnormalizedName" required /></td>
									<td style="white-space: nowrap">
										<button class="btn btn-primary btn-custom-default" ng-click="createDictionary(newResource)">Add</button>
									</td>
								</tr>
							</table>
						</form>
						<h4>List of source dictionaries</h4>
						<table class="table table-bordered table-hover table-condensed">
							<tr>
								<th>Source name</th>
								<th>Unnormalized Name</th>
								<th>Action</th>
							</tr>
							<tr ng-repeat="sd in sourceDictionaries ">
								<td>{{ sd.sourceName }}</td>
								<td>{{ sd.unnormalizedName }}</td>
								<td>
									<button class="btn btn-danger btn-custom-danger" ng-click="deleteDictionary(sd.id)">Delete</button>
								</td>
							</tr>
						</table>
					</div>
					<div class="tab-pane" id="indicatortype" ng-controller="IndicatorTypeDictionariesCtrl">
						<h4>Add a new Indicator Type Dictionary</h4>
						<form novalidate name="createResourceForm" class="css-form">
							<table class="table table-bordered table-hover table-condensed">
								<tr style="font-weight: bold">
									<td style="width: auto">Indicator Type</td>
									<td style="width: auto">Unnormalized Name</td>
									<!-- td style="width: auto">Importer</td -->
									<td style="width: auto">Action</td>
								</tr>
								<tr>
									<td><select class="form-control" id="newResource_source" ng-model="newResource.indicatorTypeId" ng-options="it.id as it.name for it in indicatorTypes" ng-class="default"></select></td>
									<td><input type="text" class="form-control" placeholder="Value" id="" ng-model="newResource.unnormalizedName" required /></td>
									<td style="white-space: nowrap">
										<button class="btn btn-primary btn-custom-default" ng-click="createDictionary(newResource)">Add</button>
									</td>
								</tr>
							</table>
						</form>
						<h4>List of indicator type dictionaries</h4>
						<table class="table table-bordered table-hover table-condensed">
							<tr>
								<th>Indicator Type name</th>
								<th>Unnormalized Name</th>
								<th>Action</th>
							</tr>
							<tr ng-repeat="itd in indicatorTypeDictionaries ">
								<td>{{ itd.indicatorTypeName }}</td>
								<td>{{ itd.unnormalizedName }}</td>
								<td>
									<button class="btn btn-danger btn-custom-danger" ng-click="deleteDictionary(itd.id)">Delete</button>
								</td>
							</tr>
						</table>

					</div>
				</div>
			</div>
		</div>

	</div>

	<div ng-show="showTestZone">
		<h3>Test zone</h3>
		<pre>
		<p>AllowedIndicatorTypeCodesSelected : {{ allowedIndicatorTypeCodesSelected }}</p>
		<p>AvailableIndicatorTypeCodesSelected : {{ availableIndicatorTypeCodesSelected }}</p>
		<p>EditResourceConfiguration : {{ editResourceConfiguration | json }}</p>
	</pre>
	</div>
</body>
</html>