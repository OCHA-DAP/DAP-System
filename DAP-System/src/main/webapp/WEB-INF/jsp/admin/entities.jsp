<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="ctx" scope="request" />
<!DOCTYPE html>
<html ng-app="app">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/css/xeditable.css">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<script type="text/javascript">
  var dapContextRoot = "${ctx}";
</script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.7/angular.min.js"></script>
<script src="${ctx}/js/xeditable.js"></script>
<script src="${ctx}/js/entities.js"></script>

</head>
<body ng-controller="EntitiesCtrl">
	<jsp:include page="admin-header.jsp" />
	<div>
		<h3>Add entity</h3>
		<form novalidate name="addEntityForm" class="css-form">
			<table class="table table-bordered table-hover table-condensed">
				<tr style="font-weight: bold">
					<td style="width: 10%">Type</td>
					<td style="width: 10%">Code</td>
					<td style="width: 60%">Default name</td>
					<td style="width: 20%">Action</td>
				</tr>
				<tr>
					<td><select ng-model="newentity.type" ng-options="type.name for type in types" ng-class="default">
					</select></td>
					<td><input type="text" ng-model="newentity.code" required /></td>
					<td><input type="text" ng-model="newentity.name" required /></td>
					<td style="white-space: nowrap">
						<button class="btn btn-primary" ng-click="addEntity(newentity)">Add</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<h3>Entities</h3>
	<div>
		<table class="table table-bordered table-hover table-condensed">
			<tr style="font-weight: bold">
				<td style="width: 10%"><a href="" ng-click="predicate='type'; reverse=!reverse">Type</a></td>
				<td style="width: 10%"><a href="" ng-click="predicate='code'; reverse=!reverse">Code</a></td>
				<td style="width: 20%"><a href="" ng-click="predicate='name'; reverse=!reverse">Default name</a></td>
				<td style="width: 40%">Translations</td>
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
					<!-- editable name --> <span editable-text="entity.name" e-name="name" e-form="rowform" e-required> {{ entity.name }} </span>
				</td>
				<td>
					<table class="table table-bordered table-hover table-condensed">
						<tr style="font-weight: bold">
							<td style="width: 20%"><a href="" ng-click="t_predicate='code'; t_reverse=!t_reverse">Language</a></td>
							<td style="width: 60%"><a href="" ng-click="t_predicate='value'; t_reverse=!t_reverse">Translation</a></td>
							<td style="width: 20%">Action</td>
						</tr>
						<tr ng-repeat="translation in entity.translations | orderBy:t_predicate:t_reverse">
							<td>
								<!-- non editable language --> <span e-name="language" e-form="t_rowform"> {{ translation.code }} </span>
							</td>
							<td>
								<!-- editable value --> <span editable-text="translation.value" e-name="value" e-form="t_rowform"> {{ translation.value }} </span>
							</td>
							<td style="white-space: nowrap">
								<!-- t form -->
								<form editable-form name="t_rowform" onbeforesave="saveTranslation($data, entity.text_id, translation.code)" ng-show="t_rowform.$visible" class="form-buttons form-inline" shown="inserted == translation">
									<button type="submit" ng-disabled="t_rowform.$waiting" class="btn btn-primary" style="padding: 0px 5px 0px 5px; margin: 0px;">Save</button>
									<button type="button" ng-disabled="t_rowform.$waiting" ng-click="t_rowform.$cancel()" class="btn btn-default" style="padding: 0px 5px 0px 5px; margin: 0px;">Cancel</button>
								</form>
								<div class="buttons" ng-show="!t_rowform.$visible">
									<button class="btn btn-primary" id="save_entity_button" style="padding: 0px 5px 0px 5px; margin: 0px;" ng-click="t_rowform.$show()">Edit</button>
									<button class="btn btn-danger" style="padding: 0px 5px 0px 5px; margin: 0px;" ng-click="removeTranslation(entity.text_id, translation.code)">Delete</button>
								</div>
							</td>
						</tr>
						<tr ng-show="showAddTranslation(entity.id)">
							<td><select ng-model="newtranslation[$index].language" ng-options="language.code for language in languages | filter:languagesByAvailableTranslations(entity.id, $index)" ng-class="default">
							</select></td>
							<td><input type="text" ng-model="newtranslation[$index].value"/></input></td>
							<td><button class="btn btn-primary" style="padding: 0px 5px 0px 5px; margin: 0px;" ng-click="addTranslation(entity.id, entity.text_id, $index)">Add</button></td>
						</tr>
					</table>
				</td>
				<td style="white-space: nowrap">
					<!-- form -->
					<form editable-form name="rowform" onbeforesave="saveEntity($data, entity.id)" ng-show="rowform.$visible" class="form-buttons form-inline" shown="inserted == entity">
						<button type="submit" ng-disabled="rowform.$waiting" class="btn btn-primary">Save</button>
						<button type="button" ng-disabled="rowform.$waiting" ng-click="rowform.$cancel()" class="btn btn-default">Cancel</button>
					</form>
					<div class="buttons" ng-show="!rowform.$visible">
						<button class="btn btn-primary" ng-click="rowform.$show()">Edit</button>
						<button class="btn btn-danger" ng-click="removeEntity(entity.id)">Delete</button>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<!-- h3>Test zone</h3>
	<pre>
		<p>Entities : {{ entities | json }}</p>
		<p>Types : {{ types | json }}</p>
		<p>Entities : {{ entities | json }}</p>
		<p>Languages : {{ languages | json }}</p>
	</pre -->


	<!-- h2>List of entity</h2>
	<table>
		<tr>
			<th>Id</th>
			<th>Code</th>
			<th>Display Names</th>
			<th>Type</th>
		</tr>

		<c:forEach var="entity" items="${it.entities}">
			<tr>
				<td>${entity.id}</td>
				<td>${entity.code}</td>
				<td>
					<c:forEach var="translation" items="${entity.name.translations}">
					${translation.id.language.code} : ${translation.value} <br/> 
					</c:forEach>
				</td>
				<td>${entity.type.name}</td>
				<td>
					<form method="POST" action="submitdelete">
						<input type="hidden" name="entityId" value="${entity.id}" />
						<input type="submit" value="Delete" />
					</form>
				</td>
			</tr>
		</c:forEach>

	</table -->



</body>
</html>