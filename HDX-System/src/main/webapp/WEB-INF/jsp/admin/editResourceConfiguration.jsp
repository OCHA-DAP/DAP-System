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
	<jsp:param name="needs" value="sources,indicatorTypes" />
</jsp:include>

</head>
<body ng-controller="EditResourceConfigurationCtrl">
	<jsp:include page="admin-menu.jsp" />
		<h3>Resource Configuration Identification</h3>
		<div style="width:50%">
			<table class="table table-bordered table-hover table-condensed"> 
				<tr>
					<th style="width:auto;text-align:right">Id</th>
					<th style="width:auto; text-align:center;">Name</th>
					<th style="white-space: nowrap; width:auto">Actions</th>
				</tr>
				<tr >
					<td style="width:auto;text-align:right">
						<!-- non editable code  -->
						<span e-name="id" e-form="ercform"> {{ editResourceConfiguration.id }} </span>
					</td>
					<td style="width:auto; text-align:center;">
						<!-- editable native name  -->
						<span editable-text="editResourceConfiguration.name" e-class="form-control" e-name="name" e-form="ercform" e-required> {{ editResourceConfiguration.name }} </span>
					</td>
					<td style="white-space: nowrap; width:auto">
						<!-- form -->
						<form  editable-form name="ercform" onbeforesave="updateRC($data, editResourceConfiguration.id)" ng-show="ercform.$visible" class="form-buttons form-inline" shown="inserted == editResourceConfiguration && ercFormVisibility">
							<button type="submit" ng-disabled="ercform.$waiting" class="btn btn-primary btn-custom-default">Save</button>
							<button type="button" ng-disabled="ercform.$waiting" ng-click="ercform.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
						</form>
						<div class="buttons" ng-show="!ercform.$visible">
							<button class="btn btn-primary btn-custom-default" ng-click="ercform.$show()" >Edit</button>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<hr/>
		<h3>General Resource Configurations</h3>
		<div style="width:50%">
			<h4>Add General Resource Configuration</h4>
			<form novalidate name="createGeneralResourceForm" class="css-form">
				<table class="table table-bordered table-hover table-condensed">
					<tr style="font-weight: bold">
						<td style="width: 40%">General Configuration</td>
						<td style="width: 40%">Value</td>
						<td style="width: 20%">Action</td>
					</tr>
					<tr>
						<td>
							<select class="form-control" id="newGenRC_key" ng-model="newGenRC.key" ng-options="generalResource.key for generalResource in editResourceConfiguration.availableGenConfs" ng-class="default"></select>
						</td>
						<td>
							<input type="text" class="form-control" placeholder="Value" id="newGenRC_value" ng-model="newGenRC.value" required />
						</td>
						<td style="white-space: nowrap">
							<button class="btn btn-primary btn-custom-default" ng-click="addGC(newGenRC,editResourceConfiguration)">Add</button>
						</td>
					</tr>
				</table>
			</form>
			<h4>List of General Resource Configurations</h4>
			<table class="table table-bordered table-hover table-condensed">
				<tr style="font-weight: bold">
					<td style="width: 20%"><a href="" ng-click="predicate='key'; reverse=!reverse">Key</td>
					<td style="width: 60%"><a href="" ng-click="predicate='value'; reverse=!reverse">Value</td>
					<td style="width: 20%">Action</td>
				</tr>
				<tr ng-repeat="gc in editResourceConfiguration.generalConfigurations | orderBy:predicate:reverse">
					<td>
						<!-- non editable code  -->
						<span e-name="key" e-form="gcform"> {{ gc.key }} </span>
					</td>
					<td>
						<!-- editable native name  -->
						<span editable-text="gc.value" e-class="form-control" e-name="value" e-form="gcform" e-required> {{ gc.value }} </span>
					</td>
					<td style="white-space: nowrap">
						<form editable-form name="gcform" onbeforesave="updateGC($data, gc)" ng-show="gcform.$visible" class="form-buttons form-inline" shown="inserted == gc">
							<button type="submit" ng-disabled="gcform.$waiting" class="btn btn-primary btn-custom-default">Save</button>
							<button type="button" ng-disabled="gcform.$waiting" ng-click="gcform.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
						</form>
						<div class="buttons" ng-show="!gcform.$visible">
							<button class="btn btn-primary btn-custom-default" ng-click="gcform.$show()" >Edit</button>
							<button class="btn btn-danger btn-custom-danger" ng-click="deleteGC(editResourceConfiguration.id,gc.id)">Delete</button>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<hr/>
		<h3>Indicator Resource Configurations</h3>
		<div style="width:50%">
			<h4>Add Indicator Resource Configuration</h4>
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
						<td>
							<select class="form-control" id="newIndRC_indTypeCode" ng-model="newIndRC.indTypeCode" ng-options="indicatorType.code for indicatorType in editResourceConfiguration.indicatorTypes" ng-class="default"></select>
						</td>
						<td>
							<select class="form-control" id="newIndRC_sourceCode" ng-model="newIndRC.sourceCode" ng-options="source.code for source in editResourceConfiguration.sources" ng-class="default"></select>
						</td>
						<td>
							<select class="form-control" id="newIndRC_indConfKey" ng-model="newIndRC.key" ng-options="indicatorConfiguration.key for indicatorConfiguration in editResourceConfiguration.availableIndConfs" ng-class="default"></select>
						</td>
						<td>
							<input type="text" class="form-control" placeholder="Value" id="newIndRC_value" ng-model="newIndRC.value" required />
						</td>
						<td style="white-space: nowrap">
							<button class="btn btn-primary btn-custom-default" ng-click="addIndicatorRC(newIndRC,editResourceConfiguration)">Add</button>
						</td>
					</tr>
				</table>
			</form>
			<h4>List of Indicator Resource Configurations</h4>
			<table class="table table-bordered table-hover table-condensed">
				<tr style="font-weight: bold">
					<td style="width: auto"><a href="" ng-click="predicate='indType'; reverse=!reverse">Indicator Type</td>
					<td style="width: auto"><a href="" ng-click="predicate='src'; reverse=!reverse">Source</td>
					<td style="width: auto"><a href="" ng-click="predicate='key'; reverse=!reverse">Indicator Configuration</td>
					<td style="width: auto"><a href="" ng-click="predicate='value'; reverse=!reverse">Value</td>
					<td style="width: auto">Action</td>
				</tr>
				<tr ng-repeat="ic in editResourceConfiguration.indicatorConfigurations | orderBy:predicate:reverse">
					<td>
					  <span editable-select="newIndRC.indTypeCode" e-class="form-control" e-name="indType" 
					  		e-id="indType" e-form="icform" 
					  		e-ng-options="v.id as v.code for v in editResourceConfiguration.indicatorTypes" e-required> 
					  		{{ showIndicatorType(ic) }} 
					  </span>
					</td>
					<td>
					  <span editable-select="newIndRC.sourceCode" e-class="form-control" e-name="src" 
					  		e-id="src" e-form="icform" 
					  		e-ng-options="v.id as v.code for v in editResourceConfiguration.sources"> 
					  		{{ showSources(ic) }} 
					  </span>
					</td>
					<td>
						<!-- non editable code  -->
						<span e-name="key" e-form="icform"> {{ ic.key }} </span>
					</td>
					<td>
						<!-- editable native name  -->
						<span editable-text="ic.value" e-class="form-control" e-name="value" e-form="icform" e-required> {{ ic.value }} </span>
					</td>
					<td style="white-space: nowrap">
						<form editable-form name="icform" onbeforesave="updateIC($data, ic)" ng-show="icform.$visible" class="form-buttons form-inline" shown="inserted == ic">
							<button type="submit" ng-disabled="icform.$waiting" class="btn btn-primary btn-custom-default">Save</button>
							<button type="button" ng-disabled="icform.$waiting" ng-click="icform.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
						</form>
						<div class="buttons" ng-show="!icform.$visible">
							<button class="btn btn-primary btn-custom-default" ng-click="icform.$show()" >Edit</button>
							<button class="btn btn-danger btn-custom-danger" ng-click="deleteIC(editResourceConfiguration.id,ic.id)">Delete</button>
						</div>
					</td>
				</tr>
			</table>
		</div>
</body>
</html>