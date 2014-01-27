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
<script src="${ctx}/js/sources.js"></script>

</head>
<body ng-controller="SourcesCtrl">
	<jsp:include page="admin-header.jsp" />
	<div>
		<h3>Add source</h3>
		<form novalidate name="addSourceForm" class="css-form">
			<table class="table table-bordered table-hover table-condensed">
				<tr style="font-weight: bold">
					<td style="width: 10%">Code</td>
					<td style="width: 70%">Default name</td>
					<td style="width: 20%">Action</td>
				</tr>
				<tr>
					<td><input type="text" ng-model="newsource.code" required /></td>
					<td><input type="text" ng-model="newsource.name" required /></td>
					<td style="white-space: nowrap">
						<button class="btn btn-primary" ng-click="addSource(newsource)">Add</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<h3>Sources</h3>
	<div>
		<table class="table table-bordered table-hover table-condensed">
			<tr style="font-weight: bold">
				<td style="width: 10%"><a href="" ng-click="predicate='code'; reverse=!reverse">Code</a></td>
				<td style="width: 20%"><a href="" ng-click="predicate='name'; reverse=!reverse">Default name</a></td>
				<td style="width: 50%">Translations</td>
				<td style="width: 20%">Action</td>
			</tr>
			<tr ng-repeat="source in sources | orderBy:predicate:reverse">
				<td>
					<!-- non editable code --> <span e-name="code" e-form="rowform"> {{ source.code }} </span>
				</td>
				<td>
					<!-- editable name --> <span editable-text="source.name" e-name="name" e-form="rowform" e-required> {{ source.name }} </span>
				</td>
				<td>
					<table class="table table-bordered table-hover table-condensed">
						<tr style="font-weight: bold">
							<td style="width: 20%"><a href="" ng-click="t_predicate='code'; t_reverse=!t_reverse">Language</a></td>
							<td style="width: 60%"><a href="" ng-click="t_predicate='value'; t_reverse=!t_reverse">Translation</a></td>
							<td style="width: 20%">Action</td>
						</tr>
						<tr ng-repeat="translation in source.translations | orderBy:t_predicate:t_reverse">
							<td>
								<!-- non editable language --> <span e-name="language" e-form="t_rowform"> {{ translation.code }} </span>
							</td>
							<td>
								<!-- editable value --> <span editable-text="translation.value" e-name="value" e-form="t_rowform"> {{ translation.value }} </span>
							</td>
							<td style="white-space: nowrap">
								<!-- t form -->
								<form editable-form name="t_rowform" onbeforesave="saveTranslation($data, source.text_id, translation.code)" ng-show="t_rowform.$visible" class="form-buttons form-inline" shown="inserted == translation">
									<button type="submit" ng-disabled="t_rowform.$waiting" class="btn btn-primary" style="padding: 0px 5px 0px 5px; margin: 0px;">Save</button>
									<button type="button" ng-disabled="t_rowform.$waiting" ng-click="t_rowform.$cancel()" class="btn btn-default" style="padding: 0px 5px 0px 5px; margin: 0px;">Cancel</button>
								</form>
								<div class="buttons" ng-show="!t_rowform.$visible">
									<button class="btn btn-primary" id="save_source_button" style="padding: 0px 5px 0px 5px; margin: 0px;" ng-click="t_rowform.$show()">Edit</button>
									<button class="btn btn-danger" style="padding: 0px 5px 0px 5px; margin: 0px;" ng-click="removeTranslation(source.text_id, translation.code)">Delete</button>
								</div>
							</td>
						</tr>
						<tr ng-show="showAddTranslation(source.id)">
							<td><select ng-model="newtranslation[$index].language" ng-options="language.code for language in languages | filter:languagesByAvailableTranslations(source.id, $index)" ng-class="default">
							</select></td>
							<td><input type="text" ng-model="newtranslation[$index].value"/></input></td>
							<td><button class="btn btn-primary" style="padding: 0px 5px 0px 5px; margin: 0px;" ng-click="addTranslation(source.id, source.text_id, $index)">Add</button></td>
						</tr>
					</table>
				</td>
				<td style="white-space: nowrap">
					<!-- form -->
					<form editable-form name="rowform" onbeforesave="saveSource($data, source.id)" ng-show="rowform.$visible" class="form-buttons form-inline" shown="inserted == source">
						<button type="submit" ng-disabled="rowform.$waiting" class="btn btn-primary">Save</button>
						<button type="button" ng-disabled="rowform.$waiting" ng-click="rowform.$cancel()" class="btn btn-default">Cancel</button>
					</form>
					<div class="buttons" ng-show="!rowform.$visible">
						<button class="btn btn-primary" ng-click="rowform.$show()">Edit</button>
						<button class="btn btn-danger" ng-click="removeSource(source.id)">Delete</button>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<h3>Test zone</h3>
	<pre>
		<p>Sources : {{ sources | json }}</p>
		<p>Languages : {{ languages | json }}</p>
	</pre>


	<!-- h2>List of sources</h2>
	<table>
		<tr>
			<th>Id</th>
			<th>Code</th>
			<th>Name</th>
		</tr>

		<c:forEach var="source" items="${it}">
			<tr>
				<td>${source.id}</td>
				<td>${source.code}</td>
				<td>${source.name.defaultValue}</td>
			</tr>
		</c:forEach>

	</table -->

</body>
</html>