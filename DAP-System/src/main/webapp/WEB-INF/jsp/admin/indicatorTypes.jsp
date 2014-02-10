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
	<jsp:param name="which" value="indicatorTypes" />
	<jsp:param name="i18n" value="true" />
</jsp:include>
</head>
<body ng-controller="IndicatorTypesCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div>
		<h3>Add indicator type</h3>
		<form novalidate name="createResourceForm" class="css-form">
			<table class="table table-bordered table-hover table-condensed">
				<tr style="font-weight: bold">
					<td style="width: 12%">Code</td>
					<td style="width: 12%">Default name</td>
					<td style="width: 12%">Unit</td>
					<td style="width: 44%">Value type</td>
					<td style="width: 20%">Action</td>
				</tr>
				<tr>
					<td><input type="text" class="form-control" placeholder="Code" id="newResource_code" ng-model="newResource.code" required /></td>
					<td><input type="text" class="form-control" placeholder="Name" id="newResource_name" ng-model="newResource.name" required /></td>
					<td><input type="text" class="form-control" placeholder="Unit" id="newResource_unit" ng-model="newResource.unit" required /></td>
					<td><select class="form-control" id="newResource_unit" ng-model="newResource.valueType" ng-options="v.value as v.text for v in valueTypes" ng-class="default" required>
					</select></td>
					<td style="white-space: nowrap">
						<button class="btn btn-primary btn-custom-default" ng-click="createIndicatorType(newResource)">Add</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<h3>Indicator types</h3>
	<div ng-controller="I18nCtrl">
		<table class="table table-bordered table-hover table-condensed">
			<tr style="font-weight: bold">
				<td style="width: 12%"><a href="" ng-click="predicate='code'; reverse=!reverse">Code</a></td>
				<td style="width: 12%"><a href="" ng-click="predicate='name'; reverse=!reverse">Default name</a></td>
				<td style="width: 12%"><a href="" ng-click="predicate='unit'; reverse=!reverse">Unit</a></td>
				<td style="width: 12%"><a href="" ng-click="predicate='valueType'; reverse=!reverse">Value type</a></td>
				<td style="width: 32%">Translations</td>
				<td style="width: 20%">Action</td>
			</tr>
			<tr ng-repeat="indicatorType in indicatorTypes | orderBy:predicate:reverse">
				<td>
					<!-- non editable code --> <span e-name="code" e-form="rowform"> {{ indicatorType.code }} </span>
				</td>
				<td>
					<!-- editable name --> <span editable-text="indicatorType.name" e-class="form-control" e-name="name" e-id="name" e-form="rowform" e-required> {{ indicatorType.name }} </span>
				</td>
				<td>
					<!-- editable unit --> <span editable-text="indicatorType.unit" e-class="form-control" e-name="unit" e-id="unit" e-form="rowform" e-required> {{ indicatorType.unit }} </span>
				</td>
				<td>
					<!-- editable value type --> <span editable-select="indicatorType.valueType" e-class="form-control" e-name="valueType" e-id="valueType" e-form="rowform"
						e-ng-options="v.value as v.text for v in valueTypes"> {{ showValueType(indicatorType) }} </span>
				</td>
				<td>
					<div ng-controller="TranslationsCtrl">
						<table class="table table-bordered table-hover table-condensed">
							<tr style="font-weight: bold">
								<td style="width: 20%"><a href="" ng-click="t_predicate='code'; t_reverse=!t_reverse">Language</a></td>
								<td style="width: 55%"><a href="" ng-click="t_predicate='value'; t_reverse=!t_reverse">Translation</a></td>
								<td style="width: 25%">Action</td>
							</tr>
							<tr ng-repeat="translation in indicatorType.translations | orderBy:t_predicate:t_reverse">
								<td>
									<!-- non editable language --> <span e-name="language" e-form="t_rowform"> {{ translation.code }} </span>
								</td>
								<td>
									<!-- editable value --> <span editable-text="translation.value" e-class="form-control" e-name="value" e-form="t_rowform"> {{ translation.value }} </span>
								</td>
								<td style="white-space: nowrap">
									<!-- t form -->
									<form editable-form name="t_rowform" onbeforesave="updateTranslation($data, indicatorType.text_id, translation.code, indicatorType, 'indicatorType', indicatorType.id)"
										ng-show="t_rowform.$visible" class="form-buttons form-inline" shown="inserted == translation">
										<button type="submit" ng-disabled="t_rowform.$waiting" class="btn btn-primary btn-xs btn-custom-default">Save</button>
										<button type="button" ng-disabled="t_rowform.$waiting" ng-click="t_rowform.$cancel()" class="btn btn-default btn-xs btn-custom-cancel">Cancel</button>
									</form>
									<div class="buttons" ng-show="!t_rowform.$visible">
										<button class="btn btn-primary btn-xs btn-custom-default" ng-click="t_rowform.$show()">Edit</button>
										<button class="btn btn-danger btn-xs btn-custom-danger" ng-click="deleteTranslation(indicatorType.text_id, translation.code, indicatorType, 'indicatorType', indicatorType.id)">Delete</button>
									</div>
								</td>
							</tr>
							<tr ng-show="showAddTranslation(indicatorTypes, 'id', indicator	Type.id)">
								<td><select class="form-control" ng-model="newTranslation.language"
									ng-options="language.code for language in languages | filter:languagesByAvailableTranslations(indicatorTypes, 'id', indicatorType.id)" ng-class="default">
								</select></td>
								<td><input type="text" class="form-control" placeholder="Translation" id="newTranslation_{{indicatorType.text_id}}_value" ng-model="newTranslation.value" /></input></td>
								<td><button class="btn btn-primary btn-xs btn-custom-default" ng-click="createTranslation(indicatorType.text_id, indicatorType, 'indicatorType', indicatorType.id)">Add</button></td>
							</tr>
						</table>
						<div ng-show="addLanguage" class="translations_complete">
							<span>
								All translations complete. Do you wish to <a href="${ctx}/admin/misc/languages/">add another language</a> ?
							</span>
						</div>
					</div>
				</td>
				<td style="white-space: nowrap">
					<!-- form -->
					<form editable-form name="rowform" onbeforesave="updateIndicatorType($data, indicatorType.id)" ng-show="rowform.$visible" class="form-buttons form-inline" shown="inserted == indicatorType">
						<button type="submit" ng-disabled="rowform.$waiting" class="btn btn-primary btn-custom-default">Save</button>
						<button type="button" ng-disabled="rowform.$waiting" ng-click="rowform.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
					</form>
					<div class="buttons" ng-show="!rowform.$visible">
						<button class="btn btn-primary btn-custom-default" ng-click="rowform.$show()">Edit</button>
						<button class="btn btn-danger btn-custom-danger" ng-click="deleteIndicatorType(indicatorType.id)">Delete</button>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div ng-show="showTestZone">
		<h3>Test zone</h3>
		<pre>
		<p>IndicatorTypes : {{ indicatorTypes | json }}</p>
		<p>Value types : {{ valueTypes | json }}</p>
		<p>Languages : {{ languages | json }}</p>
	</pre>
	</div>
</body>
</html>