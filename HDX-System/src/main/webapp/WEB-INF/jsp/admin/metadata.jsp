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
	<jsp:param name="which" value="metadata" />
	<jsp:param name="needs" value="languages,indicatorTypes" />
</jsp:include>
</head>
<body ng-controller="MetadataCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div>
		<h3>Metadata managements</h3>
		<div style="width: 400px; margin-bottom: 20px;">
			<div class="form-group">
				<label for="indicatorType">Indicator type</label> <select class="form-control" id="indicatorType" ng-model="indicatorType" ng-change="indicatorTypeSelect()"
					ng-options="indicatorType as (indicatorType | showIndicatorType) for indicatorType in indicatorTypes" ng-class="default">
				</select>
			</div>
			<div class="form-group">
				<label for="source">Source</label> <select class="form-control" id="source" ng-disabled="sourceUnavailable" ng-model="source" ng-change="sourceSelect()"
					ng-options="source as (source | showSource) for source in sources" ng-class="default">
				</select>
			</div>
			<button class="btn btn-primary btn-custom-default" ng-click="showMetadata()" ng-disabled="sourceUnavailable">Show metadata</button>
			<button class="btn btn-primary btn-custom-default" ng-click="showTimeParameters()" ng-disabled="sourceUnavailable">Show time parameters</button>
		</div>

		<div style="width: 800px;" ng-show="metadataAvailable">
			<h4>Metadata for indicator type [{{indicatorType.code}}] and source [{{source.code}}]</h4>
			<!-- Nav tabs -->
			<ul class="nav nav-tabs" id="metadataTabs">
				<li class="active"><a href="#DATASET_SUMMARY" data-toggle="tab">Dataset summary</a></li>
				<li><a href="#METHODOLOGY" data-toggle="tab">Methodology</a></li>
				<li><a href="#MORE_INFO" data-toggle="tab">More info</a></li>
				<li><a href="#TERMS_OF_USE" data-toggle="tab">Terms of use</a></li>
			</ul>

			<!-- Tab panes -->
			<!-- Please be careful when changing names of DIVs like DATASET_SUMMARY because these names come from the EntryKey enum and are used to focus on tabs after submitUpdate -->
			<div class="tab-content" style="margin-bottom: 10px;">
				<div class="tab-pane scrollable-tab-pane active" id="DATASET_SUMMARY" style="height: 250px;">
					<textarea editable-textarea="metadata.datasetSummary" e-class="form-control" e-name="datasetSummary" e-id="datasetSummary" e-form="datasetSummaryForm" e-required e-rows="8" e-cols="125" rows="8"
						cols="125" disabled style="padding: 5px;">{{
						metadata.datasetSummary }} </textarea>
					<form editable-form name="datasetSummaryForm" onbeforesave="updateMetadata($data.datasetSummary, 'DATASET_SUMMARY')" style="margin-top: 10px;" ng-show="datasetSummaryForm.$visible"
						class="form-buttons form-inline" shown="inserted == metadata">
						<button type="submit" ng-disabled="datasetSummaryForm.$waiting" class="btn btn-primary btn-custom-default">Save</button>
						<button type="button" ng-disabled="datasetSummaryForm.$waiting" ng-click="datasetSummaryForm.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
					</form>
					<div class="buttons" ng-show="!datasetSummaryForm.$visible" style="margin-top: 10px;">
						<button class="btn btn-primary btn-custom-default" ng-click="datasetSummaryForm.$show()">Edit</button>
					</div>
				</div>
				<div class="tab-pane scrollable-tab-pane" id="METHODOLOGY" style="height: 250px;">
					<textarea editable-textarea="metadata.methodology" e-class="form-control" e-name="methodology" e-id="methodology" e-form="methodologyForm" e-required e-rows="8" e-cols="125" rows="8" cols="125"
						disabled style="padding: 5px;">{{ metadata.methodology }}</textarea>
					<form editable-form name="methodologyForm" onbeforesave="updateMetadata($data.methodology, 'METHODOLOGY')" style="margin-top: 10px;" ng-show="methodologyForm.$visible"
						class="form-buttons form-inline" shown="inserted == metadata">
						<button type="submit" ng-disabled="methodologyForm.$waiting" class="btn btn-primary btn-custom-default">Save</button>
						<button type="button" ng-disabled="methodologyForm.$waiting" ng-click="methodologyForm.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
					</form>
					<div class="buttons" ng-show="!methodologyForm.$visible" style="margin-top: 10px;">
						<button class="btn btn-primary btn-custom-default" ng-click="methodologyForm.$show()">Edit</button>
					</div>
				</div>
				<div class="tab-pane scrollable-tab-pane" id="MORE_INFO" style="height: 250px;">
					<textarea editable-textarea="metadata.moreInfo" e-class="form-control" e-name="moreInfo" e-id="moreInfo" e-form="moreInfoForm" e-required e-rows="8" e-cols="125" rows="8" cols="125" disabled
						style="padding: 5px;">{{metadata.moreInfo}}</textarea>
					<form editable-form name="moreInfoForm" onbeforesave="updateMetadata($data.moreInfo, 'MORE_INFO')" style="margin-top: 10px;" ng-show="moreInfoForm.$visible" class="form-buttons form-inline"
						shown="inserted == metadata">
						<button type="submit" ng-disabled="moreInfoForm.$waiting" class="btn btn-primary btn-custom-default">Save</button>
						<button type="button" ng-disabled="moreInfoForm.$waiting" ng-click="moreInfoForm.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
					</form>
					<div class="buttons" ng-show="!moreInfoForm.$visible" style="margin-top: 10px;">
						<button class="btn btn-primary btn-custom-default" ng-click="moreInfoForm.$show()">Edit</button>
					</div>
				</div>
				<div class="tab-pane scrollable-tab-pane" id="TERMS_OF_USE" style="height: 250px;">
					<textarea editable-textarea="metadata.termsOfUse" e-class="form-control" e-name="termsOfUse" e-id="termsOfUse" e-form="termsOfUseForm" e-required e-rows="8" e-cols="125" rows="8" cols="125"
						disabled style="padding: 5px;">{{metadata.termsOfUse}}</textarea>
					<form editable-form name="termsOfUseForm" onbeforesave="updateMetadata($data.termsOfUse, 'TERMS_OF_USE')" style="margin-top: 10px;" ng-show="termsOfUseForm.$visible"
						class="form-buttons form-inline" shown="inserted == metadata">
						<button type="submit" ng-disabled="termsOfUseForm.$waiting" class="btn btn-primary btn-custom-default">Save</button>
						<button type="button" ng-disabled="termsOfUseForm.$waiting" ng-click="termsOfUseForm.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
					</form>
					<div class="buttons" ng-show="!termsOfUseForm.$visible" style="margin-top: 10px;">
						<button class="btn btn-primary btn-custom-default" ng-click="termsOfUseForm.$show()">Edit</button>
					</div>
				</div>
			</div>
		</div>
		<div style="width: 800px;" ng-show="timeParametersAvailable">
			<h4>Time parameters for indicator type [{{indicatorType.code}}] and source [{{source.code}}]</h4>
			<div style="width: 400px;">
				<form novalidate name="timeParametersForm" class="css-form">
					<div class="form-group">
						<label for="expectedTimeFormat">Expected time format</label> <input type="text" class="form-control" placeholder="Expected time format..." ng-model="timeParameters.expectedTimeFormat"
							id="expectedTimeFormat" />
					</div>
					<div class="form-group">
						<label for="interpretedStartTime">Interpreted start time</label> <input type="text" class="form-control" placeholder="Interpreted start time..." ng-model="timeParameters.interpretedStartTime"
							id="interpretedStartTime" />
					</div>
					<div class="form-group">
						<label for="interpretedEndTime">Interpreted end time</label> <input type="text" class="form-control" placeholder="Interpreted end time..." ng-model="timeParameters.interpretedEndTime"
							id="interpretedEndTime" />
					</div>
					<button class="btn btn-primary btn-custom-default" ng-click="updateTimeParameters()">Save</button>
				</form>
			</div>
		</div>
	</div>
	<div ng-show="showTestZone">
		<h3>Test zone</h3>
		<pre>
		<p>Languages : {{ languages | json }}</p>
		<p>Indicator types : {{ indicatorTypes | json }}</p>
		<p>Sources : {{ sources | json }}</p>
		<p>Metadata : {{ metadata | json }}</p>
	</pre>
	</div>
</body>
</html>