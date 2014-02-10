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
</jsp:include>
</head>
<body ng-controller="EntitiesCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div>
		<h3>Add entity</h3>
		<form novalidate name="createResourceForm" class="css-form">
			<table class="table table-bordered table-hover table-condensed">
				<tr style="font-weight: bold">
					<td style="width: 15%">Type</td>
					<td style="width: 15%">Code</td>
					<td style="width: 50%">Default name</td>
					<td style="width: 20%">Action</td>
				</tr>
				<tr>
					<td><select class="form-control" id="newResource_type" ng-model="newResource.type" ng-options="type.name for type in types" ng-class="default">
					</select></td>
					<td><input type="text" class="form-control" placeholder="Code" id="newResource_code" ng-model="newResource.code" required /></td>
					<td><input type="text" class="form-control" placeholder="Name" id="newResource_name" ng-model="newResource.name" required /></td>
					<td style="white-space: nowrap">
						<button class="btn btn-primary btn-custom-default" ng-click=" createEntity(newResource)">Add</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<h3>Entities</h3>
	<div ng-controller="I18nCtrl">
		<table class="table table-bordered table-hover table-condensed">
			<tr style="font-weight: bold">
				<td style="width: 15%"><a href="" ng-click="predicate='type'; reverse=!reverse">Type</a></td>
				<td style="width: 15%"><a href="" ng-click="predicate='code'; reverse=!reverse">Code</a></td>
				<td style="width: 15%"><a href="" ng-click="predicate='name'; reverse=!reverse">Default name</a></td>
				<td style="width: 35%">Translations</td>
				<td style="width: 20%">Action</td>
			</tr>
			<tr ng-repeat="entity in entities | orderBy:predicate:reverse">
				<td>
					<!-- non editable type (from select box) --> <span> {{ showType(entity) }} </span>
				</td>
				<td>
					<!-- non editable code --> <span e-name="code" e-form="rowform"> {{ entity.code }} </span>
				</td>
				<td>
					<!-- editable name --> <span editable-text="entity.name" e-class="form-control" e-name="name" e-id="name" e-form="rowform" e-required> {{ entity.name }} </span>
				</td>
				<td>
					<div ng-controller="TranslationsCtrl">
						<table class="table table-bordered table-hover table-condensed">
							<tr style="font-weight: bold">
								<td style="width: 20%"><a href="" ng-click="t_predicate='code'; t_reverse=!t_reverse">Language</a></td>
								<td style="width: 55%"><a href="" ng-click="t_predicate='value'; t_reverse=!t_reverse">Translation</a></td>
								<td style="width: 25%">Action</td>
							</tr>
							<tr ng-repeat="translation in entity.translations | orderBy:t_predicate:t_reverse">
								<td>
									<!-- non editable language --> <span e-name="language" e-form="t_rowform"> {{ translation.code }} </span>
								</td>
								<td>
									<!-- editable value --> <span editable-text="translation.value" e-class="form-control" e-name="value" e-form="t_rowform"> {{ translation.value }} </span>
								</td>
								<td style="white-space: nowrap">
									<!-- t form -->
									<form editable-form name="t_rowform" onbeforesave="updateTranslation($data, entity.text_id, translation.code, entity, 'entity', entity.id)" ng-show="t_rowform.$visible"
										class="form-buttons form-inline" shown="inserted == translation">
										<button type="submit" ng-disabled="t_rowform.$waiting" class="btn btn-primary btn-xs btn-custom-default">Save</button>
										<button type="button" ng-disabled="t_rowform.$waiting" ng-click="t_rowform.$cancel()" class="btn btn-default btn-xs btn-custom-cancel">Cancel</button>
									</form>
									<div class="buttons" ng-show="!t_rowform.$visible">
										<button class="btn btn-primary btn-xs btn-custom-default" ng-click="t_rowform.$show()">Edit</button>
										<button class="btn btn-danger btn-xs btn-custom-danger" ng-click="deleteTranslation(entity.text_id, translation.code, entity, 'entity', entity.id)">Delete</button>
									</div>
								</td>
							</tr>
							<tr ng-show="showAddTranslation(entities, 'id', entity.id)">
								<td><select class="form-control" ng-model="newTranslation.language"
									ng-options="language.code for language in languages | filter:languagesByAvailableTranslations(entities, 'id', entity.id)" ng-class="default">
								</select></td>
								<td><input type="text" class="form-control" placeholder="Translation" id="newTranslation_value" ng-model="newTranslation.value" required /></input></td>
								<td><button class="btn btn-primary btn-xs btn-custom-default" ng-click="createTranslation(entity.text_id, entity, 'entity', entity.id)">Add</button></td>
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
	<div ng-show="showTestZone">
		<h3>Test zone</h3>
		<pre>
		<p>Entities : {{ entities | json }}</p>
		<p>Types : {{ types | json }}</p>
		<p>Entities : {{ entities | json }}</p>
		<p>Languages : {{ languages | json }}</p>
	</pre>
	</div>
</body>
</html>