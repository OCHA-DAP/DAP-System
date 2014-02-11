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
<link rel="stylesheet" href="${ctx}/css/datepicker.css">
<jsp:include page="js-includes.jsp">
	<jsp:param name="which" value="indicators" />
</jsp:include>
<script src="${ctx}/js/bootstrap-datepicker.js"></script>
</head>
<body ng-controller="IndicatorsCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div>
		<h3>Add indicator</h3>
		<!-- form novalidate name="addIndicatorForm" class="css-form">
			<table class="table table-bordered table-hover table-condensed">
				<tr style="font-weight: bold">
					<td style="width: 80%">Indicator data</td>
					<td style="width: 20%">Action</td>
				</tr>
				<tr>
					<td>
						<div>
							<fieldset class="test">
								<legend class="test">Type</legend>
								<div class="form-group">
									<label for="newResource_source">Source</label> <select id="newResource_source" class="form-control" ng-model="newResource.source" ng-options="type.name for type in types"
										ng-class="default">
									</select>
								</div>
								<div class="form-group">
									<label for="newResource_entityType">Entity type</label> <select class="form-control" ng-model="newResource.entityType" ng-options="type.name for type in types" ng-class="default">
									</select>
								</div>
								<div class="form-group">
									<label for="newResource_entity">Entity</label> <select class="form-control" ng-model="newResource.entity" ng-options="type.name for type in types" ng-class="default">
									</select>
								</div>
								<div class="form-group">
									<label for="newResource_indicatorType">Indicator type</label> <select class="form-control" ng-model="newResource.indicatorType" ng-options="type.name for type in types" ng-class="default">
									</select>
								</div>
							</fieldset>
							<fieldset class="test">
								<legend class="test">Time</legend>
								<div class="form-group">
									<label for="newResource_startDate">Start date</label> <input type="date" class="form-control" placeholder="Start date" ng-model="newResource.startDate" required />
								</div>
								<div class="form-group">
									<label for="newResource_endDate">End date</label> <input type="date" class="form-control" placeholder="End date" ng-model="newResource.endDate" required />
								</div>
								<div class="form-group">
									<label for="newResource_periodicity">Periodicity</label> <input type="text" class="form-control" placeholder="Periodicity" ng-model="newResource.periodicity" required />
								</div>
							</fieldset>
							<fieldset class="test">
								<legend class="test">Values</legend>
								<div class="form-group">
									<label for="newResource_valueType">Value type</label> <select id="newResource_valueType" class="form-control" ng-model="newResource.valueType" ng-options="type.name for type in types"
										ng-class="default">
									</select>
								</div>
								<div class="form-group">
									<label for="newResource_value">Value</label> <input type="text" class="form-control" placeholder="Value" ng-model="newResource.value" required />
								</div>
								<div class="form-group">
									<label for="newResource_initialValue">Initial value</label> <input type="text" class="form-control" placeholder="Initial value" ng-model="newResource.initialValue" required />
								</div>
							</fieldset>
							<fieldset class="test">
								<legend class="test">Time</legend>
								<div class="form-group">
									<label for="newResource_startDate">Start date</label> <input type="date" class="form-control" placeholder="Start date" ng-model="newResource.startDate" required />
								</div>
								<div class="form-group">
									<label for="newResource_endDate">End date</label> <input type="date" class="form-control" placeholder="End date" ng-model="newResource.endDate" required />
								</div>
								<div class="form-group">
									<label for="newResource_periodicity">Periodicity</label> <input type="text" class="form-control" placeholder="Periodicity" ng-model="newResource.periodicity" required />
								</div>
							</fieldset>
						</div>
					</td>
					<td style="white-space: nowrap">
						<button class="btn btn-primary btn-custom-default" ng-click="addIndicator(newResource)">Add</button>
					</td>
				</tr>
			</table>
		</form -->
		<form novalidate name="createResourceForm" class="css-form">
			<table class="table table-bordered table-hover table-condensed">
				<tr style="font-weight: bold">
					<td style="width: 30%">Type</td>
					<td style="width: 30%">Time</td>
					<td style="width: 30%">Values</td>
					<td style="width: 10%">Action</td>
				</tr>
				<tr>
					<td>
						<div class="form-group">
							<label for="newResource_source">Source</label> <select class="form-control" id="newResource_source" ng-model="newResource.source" ng-options="source.name for source in sources"
								ng-class="default" required>
							</select>
						</div>
						<div class="form-group">
							<label for="newResource_entityType">Entity type</label> <select class="form-control" id="newResource_entityType" ng-model="newResource.entityType"
								ng-options="entityType.name for entityType in entityTypes" ng-class="default" ng-change="refreshEntities()">
							</select>
						</div>
						<div class="form-group">
							<label for="newResource_entity">Entity</label> <select class="form-control" id="newResource_entity" ng-model="newResource.entity"
								ng-options="entity.name for entity in filteredEntities | orderBy:'name'" ng-class="default" ng-disabled="!showEntities()" required>
							</select>
						</div>
						<div class="form-group">
							<label for="newResource_indicatorType">Indicator type</label> <select class="form-control" id="newResource_indicatorType" ng-model="newResource.indicatorType"
								ng-options="indicatorType.name for indicatorType in indicatorTypes" ng-class="default" required>
							</select>
						</div>
					</td>
					<td>
						<div class="form-group">
							<label for="newResource_startDate">Start date</label> <input type="text" class="form-control datepicker" placeholder="Start date" id="newResource_startDate" ng-model="newResource.startDate"
								required />
						</div>
						<div class="form-group">
							<label for="newResource_endDate">End date</label> <input type="text" class="form-control datepicker" placeholder="End date" id="newResource_endDate" ng-model="newResource.endDate" />
						</div>
						<div class="form-group">
							<label for="newResource_periodicity">Periodicity</label> <select class="form-control" id="newResource_periodicity" ng-model="newResource.periodicity" ng-options="v.value as v.text for v in periodicities"
								ng-class="default" required>
							</select>
						</div>
					</td>
					<td>
						<div class="form-group">
							<label for="newResource_valueType">Value type</label> <select id="newResource_valueType" class="form-control" id="newResource_valueType" ng-model="newResource.valueType"
								ng-options="valueType.name as valueType.text for valueType in valueTypes" ng-class="default" required>
							</select>
						</div>
						<div class="form-group">
							<label for="newResource_value">Value</label> <input type="text" class="form-control" placeholder="Value" id="newResource_value" ng-model="newResource.value" required />
						</div>
						<div class="form-group">
							<label for="newResource_initialValue">Initial value</label> <input type="text" class="form-control" placeholder="Initial value" id="newResource_initialValue" ng-model="newResource.initialValue"
								required />
						</div>
					</td>
					<td style="white-space: nowrap">
						<button class="btn btn-primary btn-custom-default" ng-click="createIndicator(newResource)">Add</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<h3>Indicators</h3>
	<div>
		<table class="table table-bordered table-hover table-condensed">
			<tr style="font-weight: bold">
				<td style="width: 9%"><a href="" ng-click="predicate='id'; reverse=!reverse">Id</a></td>
				<td style="width: 9%"><a href="" ng-click="predicate='source'; reverse=!reverse">Source</a></td>
				<td style="width: 9%"><a href="" ng-click="predicate='indicatorType'; reverse=!reverse">Indicator type</a></td>
				<td style="width: 9%"><a href="" ng-click="predicate='start'; reverse=!reverse">Start</a></td>
				<td style="width: 9%"><a href="" ng-click="predicate='end'; reverse=!reverse">End</a></td>
				<td style="width: 9%"><a href="" ng-click="predicate='periodicity'; reverse=!reverse">Periodicity</a></td>
				<td style="width: 9%"><a href="" ng-click="predicate='valueType'; reverse=!reverse">Value type</a></td>
				<td style="width: 9%"><a href="" ng-click="predicate='value'; reverse=!reverse">Value</a></td>
				<td style="width: 9%"><a href="" ng-click="predicate='initialValue'; reverse=!reverse">Initial value</a></td>
				<td style="width: 9%"><a href="" ng-click="predicate='importFromCkan'; reverse=!reverse">Import from CKAN</a></td>
				<td style="width: 10%">Action</td>
			</tr>
			<tr ng-repeat="indicator in indicators | orderBy:predicate:reverse">
				<td>
					<!-- non editable id --> <span e-name="id" e-form="rowform"> {{ indicator.id }} </span>
				</td>
				<td>
					<!-- editable source --> <span editable-select="indicator.source" e-class="form-control" e-name="source" e-id="source" e-form="rowform" e-ng-options="v.id as v.name for v in sources"> {{
						showSource(indicator) }} </span>
				</td>
				<td>
					<!-- editable indicator type --> <span editable-select="indicator.indicatorType" e-class="form-control" e-name="indicatorType" e-id="indicatorType" e-form="rowform"
						e-ng-options="v.id as v.name for v in indicatorTypes"> {{ showIndicatorType(indicator) }} </span>
				</td>
				<td>
					<!-- editable start date --> <span editable-text="indicator.startDate" e-class="form-control datepicker" e-name="startDate" e-form="rowform" required> {{ indicator.parsedStartDate }} </span>
				</td>
				<td>
					<!-- editable end date --> <span editable-text="indicator.endDate" e-class="form-control datepicker" e-name="endDate" e-form="rowform" required> {{ indicator.parsedEndDate }} </span>
				</td>
				<td>
					<!-- editable periodicity --> <span editable-select="indicator.periodicity" e-class="form-control" e-name="periodicity" e-id="periodicity" e-form="rowform"
						e-ng-options="v.value as v.text for v in periodicities"> {{ indicator.processedPeriodicity }} </span>
				</td>
				<td>
					<!-- editable value type --> <span editable-select="indicator.valueType" e-class="form-control" e-name="valueType" e-id="valueType" e-form="rowform"
						e-ng-options="v.value as v.text for v in valueTypes"> {{ showValueType(indicator) }} </span>
				</td>
				<td>
					<!-- editable value --> <span editable-text="indicator.value" e-class="form-control" e-name="value" e-form="rowform" required> {{ indicator.value }} </span>
				</td>
				<td>
					<!-- editable initial value --> <span editable-text="indicator.initialValue" e-class="form-control" e-name="initialValue" e-form="rowform" required> {{ indicator.initialValue }} </span>
				</td>
				<td>
					<!-- editable import from CKAN --> <span editable-text="indicator.importFromCkan" e-class="form-control" e-name="importFromCkan" e-form="rowform" required> {{ indicator.importFromCkan }} </span>
				</td>
				<td style="white-space: nowrap">
					<!-- form -->
					<form editable-form name="rowform" onbeforesave="updateIndicator($data, indicator.id)" ng-show="rowform.$visible" class="form-buttons form-inline" shown="inserted == indicator">
						<button type="submit" ng-disabled="rowform.$waiting" class="btn btn-primary btn-custom-default">Save</button>
						<button type="button" ng-disabled="rowform.$waiting" ng-click="rowform.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
					</form>
					<div class="buttons" ng-show="!rowform.$visible">
						<button class="btn btn-primary btn-custom-default" ng-click="rowform.$show()">Edit</button>
						<button class="btn btn-danger btn-custom-danger" ng-click="deleteIndicator(indicator.id)">Delete</button>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div ng-show="showTestZone">
		<h3>Test zone</h3>
		<pre>
		<p>Indicators : {{ indicators | json }}</p>
		<p>New indicator : {{ newResource | json }}</p>
		<p>Entity types : {{ entityTypes | json }}</p>
		<p>Entities : {{ entities | json }}</p>
		<p>Filtered entities : {{ filteredEntities | json }}</p>
		<p>Indicator types : {{ indicatorTypes | json }}</p>
		<p>Languages : {{ languages | json }}</p>
	</pre>
	</div>
</body>
</html>