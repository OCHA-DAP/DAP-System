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
	<jsp:param name="which" value="dataSeries" />
	<jsp:param name="needs" value="languages,indicatorTypes,sources,periodicities,dataValidators" />
</jsp:include>
</head>
<body ng-controller="DataSeriesCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div class="modal fade" id="createDataSeriesModal" tabindex="-1" role="dialog" aria-labelledby="createDataSeriesModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="createDataSeriesModalLabel">Create a Data Series</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="source">Please choose a source to associate with indicator type {{indicatorType.code}} ({{indicatorType.name}})</label> <select class="form-control" id="sourceForDataSeries" ng-model="sourceForDataSeries" ng-options="s.code for s in filteredSources = (sources | filter:filterDataSeriesSources)" ng-class="default" ng-init="sourceForDataSeries=((filteredSources && 0 < filteredSources.length) ? filteredSources[0] : {})">
						</select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" ng-click="createDataSeries()">Create</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="dataSeriesCreatedModal" tabindex="-1" role="dialog" aria-labelledby="dataSeriesCreatedModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="dataSeriesCreatedModalLabel">Data Series created</h4>
				</div>
				<div class="modal-body">
					<span>Data Series created for indicator type <b>{{indicatorType.code}}</b> ({{indicatorType.name}}) with source <b>{{source.code}}</b> ({{source.name}}).</span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" ng-click="editDataSeries()">Edit metadata</button>
				</div>
			</div>
		</div>
	</div>
	<div id="content">
		<h3>Data Series management</h3>
		<div style="width: 600px;">
			<div class="form-group">
				<label for="indicatorType">Indicator type</label><select class="form-control" id="indicatorType" ng-model="indicatorType" ng-change="indicatorTypeSelect()"
					ng-options="indicatorType as (indicatorType | showIndicatorType) for indicatorType in indicatorTypes" ng-class="default">
				</select>
			</div>
			<div class="form-group">
				<label for="source">Source</label> <select class="form-control" id="source" ng-disabled="sourceUnavailable" ng-model="source" ng-change="sourceSelect()"
					ng-options="s as (s | showSource) for s in sourcesForIndicatorType" ng-class="default">
				</select>
				<div style="font-size: smaller; margin-top: 6px; margin-left: 6px;">
					No source for this indicator type or you want to add a new one ? <a href="#" data-toggle="modal" data-target="#createDataSeriesModal">Create a data series</a>
				</div>
			</div>
		</div>
		<div style="width: 800px; margin-bottom: 20px;">
			<button class="btn btn-primary btn-custom-default" ng-click="showMetadata()" ng-disabled="sourceUnavailable">Show metadata</button>
			<button class="btn btn-primary btn-custom-default" ng-click="showMetadata('CURATED_DATA_VALIDATORS')" ng-disabled="sourceUnavailable">Show curated data validators</button>
			<button class="btn btn-primary btn-custom-default" ng-click="showMetadata('VALIDATION_NOTES')" ng-disabled="sourceUnavailable">Show validation notes and comments</button>
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
				<div class="tab-pane scrollable-tab-pane metadata-tab active" id="DATASET_SUMMARY">
					<div>
						<div class="pull-left">
							<h5 id="DATASET_SUMMARY_default">Default value</h5>
						</div>
						<div class="pull-right">
							Go to
							<span ng-repeat="alanguage in languages">
								<a href="" ng-href="#DATASET_SUMMARY_{{alanguage.code}}" target="_self">{{alanguage.code}}</a>
							</span>
						</div>
						<textarea editable-textarea="metadata.datasetSummary" onbeforesave="applyChange($data, 'DATASET_SUMMARY', 'default')" e-class="form-control" e-name="datasetSummary" e-id="datasetSummary"
							e-form="datasetSummaryForm_default" e-required e-rows="8" e-cols="125" rows="8" cols="125" disabled style="padding: 5px;">{{
						metadata.datasetSummary }} </textarea>
						<form editable-form name="datasetSummaryForm_default" onbeforesave="updateMetadata('DATASET_SUMMARY', 'default')" style="margin-top: 10px;" ng-show="datasetSummaryForm_default.$visible"
							class="form-buttons form-inline" shown="inserted == metadata">
							<button type="submit" ng-disabled="datasetSummaryForm_default.$waiting" class="btn btn-primary btn-custom-default">Save</button>
							<button type="button" ng-disabled="datasetSummaryForm_default.$waiting" ng-click="datasetSummaryForm_default.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
						</form>
						<div class="buttons" ng-show="!datasetSummaryForm_default.$visible" style="margin-top: 10px;">
							<button class="btn btn-primary btn-custom-default" ng-click="datasetSummaryForm_default.$show()">Edit</button>
						</div>
					</div>
					<div ng-repeat="language in languages" style="margin-top: 30px;">
						<div class="pull-left">
							<h5 id="DATASET_SUMMARY_{{language.code}}">Translation : {{language.native_name}} ({{language.code}})</h5>
						</div>
						<div class="pull-right">
							Go to <a href="" ng-href="#DATASET_SUMMARY_default" target="_self">default</a> or
							<span ng-repeat="alanguage in languages | filter:filterLanguage(language)">
								<a href="" ng-href="#DATASET_SUMMARY_{{alanguage.code}}" target="_self">{{alanguage.code}}</a>
							</span>
						</div>
						<textarea editable-textarea="metadata.datasetSummaryTranslations[language.code]" onbeforesave="applyChange($data, 'DATASET_SUMMARY', language.code)" e-class="form-control"
							e-name="datasetSummary_{{language.code}}" e-id="datasetSummary_{{language.code}}" e-form="datasetSummaryForm" e-rows="8" e-cols="125" rows="8" cols="125" disabled style="padding: 5px;">{{
						metadata.datasetSummaryTranslations[language.code] }} </textarea>
						<form editable-form name="datasetSummaryForm" onbeforesave="updateMetadata('DATASET_SUMMARY', language.code)" style="margin-top: 10px;" ng-show="datasetSummaryForm.$visible"
							class="form-buttons form-inline" shown="inserted == metadata">
							<button type="submit" ng-disabled="datasetSummaryForm.$waiting" class="btn btn-primary btn-custom-default">Save</button>
							<button type="button" ng-disabled="datasetSummaryForm.$waiting" ng-click="datasetSummaryForm.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
							<!-- 
							<span style="font-style: italic;">
								(&nbsp;copy from <a href="#" ng-click="copyMetadata('DATASET_SUMMARY', 'default', language.code)">default</a> or
								<span ng-repeat="copylanguage in languages | filter:filterLanguage(language)">
									<a href="#" ng-click="copyMetadata('DATASET_SUMMARY', copylanguage.code, language.code)">{{copylanguage.code}}</a> 
								</span>)
							</span>
 							-->
						</form>
						<div class="buttons" ng-show="!datasetSummaryForm.$visible" style="margin-top: 10px;">
							<button class="btn btn-primary btn-custom-default" ng-click="datasetSummaryForm.$show()">Edit</button>
						</div>
					</div>
				</div>
				<div class="tab-pane scrollable-tab-pane metadata-tab" id="METHODOLOGY">
					<div>
						<div class="pull-left">
							<h5 id="METHODOLOGY_default">Default value</h5>
						</div>
						<div class="pull-right">
							Go to
							<span ng-repeat="alanguage in languages">
								<a href="" ng-href="#METHODOLOGY_{{alanguage.code}}" target="_self">{{alanguage.code}}</a>
							</span>
						</div>
						<textarea editable-textarea="metadata.methodology" onbeforesave="applyChange($data, 'METHODOLOGY', 'default')" e-class="form-control" e-name="methodology" e-id="methodology"
							e-form="methodologyForm_default" e-required e-rows="8" e-cols="125" rows="8" cols="125" disabled style="padding: 5px;">{{
						metadata.methodology }} </textarea>
						<form editable-form name="methodologyForm_default" onbeforesave="updateMetadata('METHODOLOGY', 'default')" style="margin-top: 10px;" ng-show="methodologyForm_default.$visible"
							class="form-buttons form-inline" shown="inserted == metadata">
							<button type="submit" ng-disabled="methodologyForm_default.$waiting" class="btn btn-primary btn-custom-default">Save</button>
							<button type="button" ng-disabled="methodologyForm_default.$waiting" ng-click="methodologyForm_default.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
						</form>
						<div class="buttons" ng-show="!methodologyForm_default.$visible" style="margin-top: 10px;">
							<button class="btn btn-primary btn-custom-default" ng-click="methodologyForm_default.$show()">Edit</button>
						</div>
					</div>
					<div ng-repeat="language in languages" style="margin-top: 30px;">
						<div class="pull-left">
							<h5 id="METHODOLOGY_{{language.code}}">Translation : {{language.native_name}} ({{language.code}})</h5>
						</div>
						<div class="pull-right">
							Go to <a href="" ng-href="#METHODOLOGY_default" target="_self">default</a> or
							<span ng-repeat="alanguage in languages | filter:filterLanguage(language)">
								<a href="" ng-href="#METHODOLOGY_{{alanguage.code}}" target="_self">{{alanguage.code}}</a>
							</span>
						</div>
						<textarea editable-textarea="metadata.methodologyTranslations[language.code]" onbeforesave="applyChange($data, 'METHODOLOGY', language.code)" e-class="form-control"
							e-name="methodology_{{language.code}}" e-id="methodology_{{language.code}}" e-form="methodologyForm" e-rows="8" e-cols="125" rows="8" cols="125" disabled style="padding: 5px;">{{
						metadata.methodologyTranslations[language.code] }} </textarea>
						<form editable-form name="methodologyForm" onbeforesave="updateMetadata('METHODOLOGY', language.code)" style="margin-top: 10px;" ng-show="methodologyForm.$visible"
							class="form-buttons form-inline" shown="inserted == metadata">
							<button type="submit" ng-disabled="methodologyForm.$waiting" class="btn btn-primary btn-custom-default">Save</button>
							<button type="button" ng-disabled="methodologyForm.$waiting" ng-click="methodologyForm.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
							<!-- 
							<span style="font-style: italic;">
								(&nbsp;copy from <a href="#" ng-click="copyMetadata('METHODOLOGY', 'default', language.code)">default</a> or
								<span ng-repeat="copylanguage in languages | filter:filterLanguage(language)">
									<a href="#" ng-click="copyMetadata('METHODOLOGY', copylanguage.code, language.code)">{{copylanguage.code}}</a> 
								</span>)
							</span>
 							-->
						</form>
						<div class="buttons" ng-show="!methodologyForm.$visible" style="margin-top: 10px;">
							<button class="btn btn-primary btn-custom-default" ng-click="methodologyForm.$show()">Edit</button>
						</div>
					</div>
				</div>
				<div class="tab-pane scrollable-tab-pane metadata-tab" id="MORE_INFO">
					<div>
						<div class="pull-left">
							<h5 id="MORE_INFO_default">Default value</h5>
						</div>
						<div class="pull-right">
							Go to
							<span ng-repeat="alanguage in languages">
								<a href="" ng-href="#MORE_INFO_{{alanguage.code}}" target="_self">{{alanguage.code}}</a>
							</span>
						</div>
						<textarea editable-textarea="metadata.moreInfo" onbeforesave="applyChange($data, 'MORE_INFO', 'default')" e-class="form-control" e-name="moreInfo" e-id="moreInfo" e-form="moreInfoForm_default"
							e-required e-rows="8" e-cols="125" rows="8" cols="125" disabled style="padding: 5px;">{{
						metadata.moreInfo }} </textarea>
						<form editable-form name="moreInfoForm_default" onbeforesave="updateMetadata('MORE_INFO', 'default')" style="margin-top: 10px;" ng-show="moreInfoForm_default.$visible"
							class="form-buttons form-inline" shown="inserted == metadata">
							<button type="submit" ng-disabled="moreInfoForm_default.$waiting" class="btn btn-primary btn-custom-default">Save</button>
							<button type="button" ng-disabled="moreInfoForm_default.$waiting" ng-click="moreInfoForm_default.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
						</form>
						<div class="buttons" ng-show="!moreInfoForm_default.$visible" style="margin-top: 10px;">
							<button class="btn btn-primary btn-custom-default" ng-click="moreInfoForm_default.$show()">Edit</button>
						</div>
					</div>
					<div ng-repeat="language in languages" style="margin-top: 30px;">
						<div class="pull-left">
							<h5 id="MORE_INFO_{{language.code}}">Translation : {{language.native_name}} ({{language.code}})</h5>
						</div>
						<div class="pull-right">
							Go to <a href="" ng-href="#MORE_INFO_default" target="_self">default</a> or
							<span ng-repeat="alanguage in languages | filter:filterLanguage(language)">
								<a href="" ng-href="#MORE_INFO_{{alanguage.code}}" target="_self">{{alanguage.code}}</a>
							</span>
						</div>
						<textarea editable-textarea="metadata.moreInfoTranslations[language.code]" onbeforesave="applyChange($data, 'MORE_INFO', language.code)" e-class="form-control"
							e-name="moreInfo_{{language.code}}" e-id="moreInfo_{{language.code}}" e-form="moreInfoForm" e-rows="8" e-cols="125" rows="8" cols="125" disabled style="padding: 5px;">{{
						metadata.moreInfoTranslations[language.code] }} </textarea>
						<form editable-form name="moreInfoForm" onbeforesave="updateMetadata('MORE_INFO', language.code)" style="margin-top: 10px;" ng-show="moreInfoForm.$visible" class="form-buttons form-inline"
							shown="inserted == metadata">
							<button type="submit" ng-disabled="moreInfoForm.$waiting" class="btn btn-primary btn-custom-default">Save</button>
							<button type="button" ng-disabled="moreInfoForm.$waiting" ng-click="moreInfoForm.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
							<!-- 
							<span style="font-style: italic;">
								(&nbsp;copy from <a href="#" ng-click="copyMetadata('MORE_INFO', 'default', language.code)">default</a> or
								<span ng-repeat="copylanguage in languages | filter:filterLanguage(language)">
									<a href="#" ng-click="copyMetadata('MORE_INFO', copylanguage.code, language.code)">{{copylanguage.code}}</a> 
								</span>)
							</span>
 							-->
						</form>
						<div class="buttons" ng-show="!moreInfoForm.$visible" style="margin-top: 10px;">
							<button class="btn btn-primary btn-custom-default" ng-click="moreInfoForm.$show()">Edit</button>
						</div>
					</div>
				</div>
				<div class="tab-pane scrollable-tab-pane metadata-tab" id="TERMS_OF_USE">
					<div>
						<div class="pull-left">
							<h5 id="TERMS_OF_USE_default">Default value</h5>
						</div>
						<div class="pull-right">
							Go to
							<span ng-repeat="alanguage in languages">
								<a href="" ng-href="#TERMS_OF_USE_{{alanguage.code}}" target="_self">{{alanguage.code}}</a>
							</span>
						</div>
						<textarea editable-textarea="metadata.termsOfUse" onbeforesave="applyChange($data, 'TERMS_OF_USE', 'default')" e-class="form-control" e-name="termsOfUse" e-id="termsOfUse"
							e-form="termsOfUseForm_default" e-required e-rows="8" e-cols="125" rows="8" cols="125" disabled style="padding: 5px;">{{
						metadata.termsOfUse }} </textarea>
						<form editable-form name="termsOfUseForm_default" onbeforesave="updateMetadata('TERMS_OF_USE', 'default')" style="margin-top: 10px;" ng-show="termsOfUseForm_default.$visible"
							class="form-buttons form-inline" shown="inserted == metadata">
							<button type="submit" ng-disabled="termsOfUseForm_default.$waiting" class="btn btn-primary btn-custom-default">Save</button>
							<button type="button" ng-disabled="termsOfUseForm_default.$waiting" ng-click="termsOfUseForm_default.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
						</form>
						<div class="buttons" ng-show="!termsOfUseForm_default.$visible" style="margin-top: 10px;">
							<button class="btn btn-primary btn-custom-default" ng-click="termsOfUseForm_default.$show()">Edit</button>
						</div>
					</div>
					<div ng-repeat="language in languages" style="margin-top: 30px;" id="{{language.code}}">
						<div class="pull-left">
							<h5 id="TERMS_OF_USE_{{language.code}}">Translation : {{language.native_name}} ({{language.code}})</h5>
						</div>
						<div class="pull-right">
							Go to <a href="" ng-href="#TERMS_OF_USE_default" target="_self">default</a> or
							<span ng-repeat="alanguage in languages | filter:filterLanguage(language)">
								<a href="" ng-href="#TERMS_OF_USE_{{alanguage.code}}" target="_self">{{alanguage.code}}</a>
							</span>
						</div>
						<textarea editable-textarea="metadata.termsOfUseTranslations[language.code]" onbeforesave="applyChange($data, 'TERMS_OF_USE', language.code)" e-class="form-control"
							e-name="termsOfUse_{{language.code}}" e-id="termsOfUse_{{language.code}}" e-form="termsOfUseForm" e-rows="8" e-cols="125" rows="8" cols="125" disabled style="padding: 5px;">{{
						metadata.termsOfUseTranslations[language.code] }} </textarea>
						<form editable-form name="termsOfUseForm" onbeforesave="updateMetadata('TERMS_OF_USE', language.code)" style="margin-top: 10px;" ng-show="termsOfUseForm.$visible"
							class="form-buttons form-inline" shown="inserted == metadata">
							<button type="submit" ng-disabled="termsOfUseForm.$waiting" class="btn btn-primary btn-custom-default">Save</button>
							<button type="button" ng-disabled="termsOfUseForm.$waiting" ng-click="termsOfUseForm.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
							<!-- 
							<span style="font-style: italic;">
								(&nbsp;copy from <a href="#" ng-click="copyMetadata('TERMS_OF_USE', 'default', language.code)">default</a> or
								<span ng-repeat="copylanguage in languages | filter:filterLanguage(language)">
									<a href="#" ng-click="copyMetadata('TERMS_OF_USE', copylanguage.code, language.code)">{{copylanguage.code}}</a> 
								</span>)
							</span>
 							-->
						</form>
						<div class="buttons" ng-show="!termsOfUseForm.$visible" style="margin-top: 10px;">
							<button class="btn btn-primary btn-custom-default" ng-click="termsOfUseForm.$show()">Edit</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div style="width: 800px;" ng-show="dataValidatorsAvailable">
			<h4>Data validators for indicator type [{{indicatorType.code}}] and source [{{source.code}}]</h4>
			<div style="width: 800px;" class="css-form">
				<div>
					<h5>Add data validator</h5>
					<form novalidate name="createDataValidatorForm" class="css-form">
						<table class="table table-bordered table-hover table-condensed">
							<tr style="font-weight: bold">
								<td style="width: 30%">Validator</td>
								<td style="width: 50%">Value</td>
								<td style="width: 20%">Action</td>
							</tr>
							<tr>
								<td><select class="form-control" id="dataValidatorNewResource_name" ng-disabled="!enableAddDataValidatorForm" ng-model="dataValidatorNewResource.name"
									ng-options="dv.name for dv in dataValidators | filter:filterDataValidator" ng-class="default" required></select></td>
								<td><input type="text" class="form-control" placeholder="Value" id="dataValidatorNewResource_value" ng-disabled="!enableAddDataValidatorForm" ng-model="dataValidatorNewResource.value"
									required /></td>
								<td style="white-space: nowrap">
									<button class="btn btn-primary btn-custom-default" ng-disabled="!enableAddDataValidatorForm" ng-click="createDataValidator(dataValidatorNewResource)">Add</button>
								</td>
							</tr>
						</table>
					</form>
				</div>
				<h5>Data validators</h5>
				<div>
					<table class="table table-bordered table-hover table-condensed">
						<tr style="font-weight: bold">
							<td style="width: 30%"><a href="" ng-click="dataValidatorPredicate='name'; reverse=!reverse">Validator</a> <columnsearch param="search.name"></columnsearch></td>
							<td style="width: 50%"><a href="" ng-click="dataValidatorPredicate='value'; reverse=!reverse">Value</a> <columnsearch param="search.value"></columnsearch></td>
							<td style="width: 20%">Action</td>
						</tr>
						<tr ng-repeat="dataValidator in metadata.dataValidators | filter:search | orderBy:dataValidatorPredicate:reverse">
							<td>
								<!-- non editable name --> <span e-name="name" e-form="rowform"> {{ dataValidator.name }} </span>
							</td>
							<td>
								<!-- editable value --> <span editable-text="dataValidator.value" e-class="form-control" e-name="value" e-id="value" e-form="rowform" e-required> {{ dataValidator.value }} </span>
							</td>
							<td style="white-space: nowrap">
								<!-- form -->
								<form editable-form name="rowform" onbeforesave="updateDataValidator($data, dataValidator.name)" ng-show="rowform.$visible" class="form-buttons form-inline" shown="inserted == dataValidator">
									<button type="submit" ng-disabled="rowform.$waiting" class="btn btn-primary btn-custom-default">Save</button>
									<button type="button" ng-disabled="rowform.$waiting" ng-click="rowform.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
								</form>
								<div class="buttons" ng-show="!rowform.$visible">
									<button class="btn btn-primary btn-custom-default" ng-click="rowform.$show()">Edit</button>
									<button class="btn btn-danger btn-custom-danger" ng-click="deleteDataValidator(dataValidator.id)">Delete</button>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div style="width: 800px;" ng-show="validationNotesAvailable">
			<h4>Validation notes and comments for indicator type [{{indicatorType.code}}] and source [{{source.code}}]</h4>
			<textarea editable-textarea="validationNotes" onbeforesave="applyChange($data, 'VALIDATION_NOTES', language.code)" e-class="form-control" e-name="validationNotes" e-id="validationNotes"
				e-form="validationNotesForm" e-required e-rows="10" e-cols="120" rows="10" cols="120" disabled style="padding: 5px;">{{ validationNotes }} </textarea>
			<form editable-form name="validationNotesForm" onbeforesave="updateMetadata('VALIDATION_NOTES', 'default')" style="margin-top: 10px;" ng-show="validationNotesForm.$visible"
				class="form-buttons form-inline" shown="inserted == metadata">
				<button type="submit" ng-disabled="validationNotesForm.$waiting" class="btn btn-primary btn-custom-default">Save</button>
				<button type="button" ng-disabled="validationNotesForm.$waiting" ng-click="validationNotesForm.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
			</form>
			<div class="buttons" ng-show="!validationNotesForm.$visible" style="margin-top: 10px;">
				<button class="btn btn-primary btn-custom-default" ng-click="validationNotesForm.$show()">Edit</button>
			</div>
		</div>
	</div>
	<div ng-show="showTestZone">
		<h3>Test zone</h3>
		<pre>
		<p>Source : {{ source | json }}</p>
		<p>Metadata : {{ metadata | json }}</p>
		<p>Data validators : {{ dataValidators | json }}</p>
		<p>Updated data validator name : {{ updatedDataValidatorName }}</p>
		<p>Updated data validator value : {{ updatedDataValidatorValue }}</p>
		<p>Periodicities : {{ periodicities | json }}</p>
		<p>Languages : {{ languages | json }}</p>
		<p>Indicator types : {{ indicatorTypes | json }}</p>
		<p>Sources for indicator type : {{ sourcesForIndicatorType | json }}</p>
		<p>Sources : {{ sources | json }}</p>
	</pre>
	</div>
</body>
</html>