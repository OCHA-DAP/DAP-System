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
	<jsp:param name="which" value="units" />
	<jsp:param name="i18n" value="true" />
	<jsp:param name="needs" value="search,columnsearch" />
</jsp:include>
</head>
<body ng-controller="UnitsCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div>
		<h3>Add unit</h3>
		<form novalidate name="createResourceForm" class="css-form">
			<table class="table table-bordered table-hover table-condensed">
				<tr style="font-weight: bold">
					<td style="width: 30%">Code</td>
					<td style="width: 50%">Default name</td>
					<td style="width: 20%">Action</td>
				</tr>
				<tr>
					<td><input type="text" class="form-control" placeholder="Code" id="newResource_code" ng-model="newResource.code" required /></td>
					<td><input type="text" class="form-control" placeholder="Name" id="newResource_name" ng-model="newResource.name" required /></td>
					<td style="white-space: nowrap">
						<button class="btn btn-primary btn-custom-default" ng-click="createUnit(newResource)">Add</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<search title="Units"></search>
	<div ng-controller="I18nCtrl">
		<table class="table table-bordered table-hover table-condensed">
			<tr style="font-weight: bold">
				<td style="width: 30%"><a href="" ng-click="predicate='code'; reverse=!reverse">Code</a><columnsearch param="search.code"></columnsearch></td>
				<td style="width: 15%"><a href="" ng-click="predicate='name'; reverse=!reverse">Default name</a><columnsearch param="search.name"></columnsearch></td>
				<td style="width: 35%">Translations</td>
				<td style="width: 20%">Action</td>
			</tr>
			<tr ng-repeat="unit in units | filter:search | orderBy:predicate:reverse">
				<td>
					<!-- non editable code --> <span e-name="code" e-form="rowform"> {{ unit.code }} </span>
				</td>
				<td>
					<!-- editable name --> <span editable-text="unit.name" e-class="form-control" e-name="name" e-id="name" e-form="rowform" e-required> {{ unit.name }} </span>
				</td>
				<td>
					<div ng-controller="TranslationsCtrl">
						<table class="table table-bordered table-hover table-condensed">
							<tr style="font-weight: bold">
								<td style="width: 20%"><a href="" ng-click="t_predicate='code'; t_reverse=!t_reverse">Language</a></td>
								<td style="width: 55%"><a href="" ng-click="t_predicate='value'; t_reverse=!t_reverse">Translation</a></td>
								<td style="width: 25%">Action</td>
							</tr>
							<tr ng-repeat="translation in unit.translations | orderBy:t_predicate:t_reverse">
								<td>
									<!-- non editable language --> <span e-name="language" e-form="t_rowform"> {{ translation.code }} </span>
								</td>
								<td>
									<!-- editable value --> <span editable-text="translation.value" e-class="form-control" e-name="value" e-form="t_rowform"> {{ translation.value }} </span>
								</td>
								<td style="white-space: nowrap">
									<!-- t form -->
									<form editable-form name="t_rowform" onbeforesave="updateTranslation($data, unit.text_id, translation.code, unit, 'unit', unit.id)" ng-show="t_rowform.$visible"
										class="form-buttons form-inline" shown="inserted == translation">
										<button type="submit" ng-disabled="t_rowform.$waiting" class="btn btn-primary btn-xs btn-custom-default">Save</button>
										<button type="button" ng-disabled="t_rowform.$waiting" ng-click="t_rowform.$cancel()" class="btn btn-default btn-xs btn-custom-cancel">Cancel</button>
									</form>
									<div class="buttons" ng-show="!t_rowform.$visible">
										<button class="btn btn-primary btn-xs btn-custom-default" ng-click="t_rowform.$show()">Edit</button>
										<button class="btn btn-danger btn-xs btn-custom-danger" ng-click="deleteTranslation(unit.text_id, translation.code, unit, 'unit', unit.id)">Delete</button>
									</div>
								</td>
							</tr>
							<tr ng-show="showAddTranslation(units, 'id', unit.id)">
								<td><select class="form-control" ng-model="newTranslation.language"
									ng-options="language.code for language in languages | filter:languagesByAvailableTranslations(units, 'id', unit.id)" ng-class="default">
								</select></td>
								<td><input type="text" class="form-control" placeholder="Translation" id="newTranslation_value" ng-model="newTranslation.value" required /></td>
								<td><button class="btn btn-primary btn-xs btn-custom-default" ng-click="createTranslation(unit.text_id, unit, 'unit', unit.id)">Add</button></td>
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
					<form editable-form name="rowform" onbeforesave="updateUnit($data, unit.id)" ng-show="rowform.$visible" class="form-buttons form-inline" shown="inserted == unit">
						<button type="submit" ng-disabled="rowform.$waiting" class="btn btn-primary btn-custom-default">Save</button>
						<button type="button" ng-disabled="rowform.$waiting" ng-click="rowform.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
					</form>
					<div class="buttons" ng-show="!rowform.$visible">
						<button class="btn btn-primary btn-custom-default" ng-click="rowform.$show()">Edit</button>
						<button class="btn btn-danger btn-custom-danger" ng-click="deleteUnit(unit.id)">Delete</button>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<div ng-show="showTestZone">
		<h3>Test zone</h3>
		<pre>
		<p>Units : {{ units | json }}</p>
		<p>Languages : {{ languages | json }}</p>
	</pre>
	</div>
</body>
</html>