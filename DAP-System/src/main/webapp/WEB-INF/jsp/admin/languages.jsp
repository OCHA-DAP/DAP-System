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
	<jsp:param name="which" value="languages" />
</jsp:include>

</head>
<body ng-controller="LanguagesCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div>
		<h3>Add language</h3>
		<form novalidate name="addLanguageForm" class="css-form">
			<table class="table table-bordered table-hover table-condensed">
				<tr style="font-weight: bold">
					<td style="width: 40%">Code</td>
					<td style="width: 40%">Native name</td>
					<td style="width: 20%">Action</td>
				</tr>
				<tr>
					<td><input type="text" id="newlanguage_code" ng-model="newlanguage.code" required /></td>
					<td><input type="text" id="newlanguage_native_name" ng-model="newlanguage.native_name" required /></td>
					<td style="white-space: nowrap">
						<button class="btn btn-primary btn-custom-default" ng-click="addLanguage(newlanguage)">Add</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<h3>Languages</h3>
	<div>
		<table class="table table-bordered table-hover table-condensed">
			<tr style="font-weight: bold">
				<td style="width: 40%"><a href="" ng-click="predicate='code'; reverse=!reverse">Id</td>
				<td style="width: 40%"><a href="" ng-click="predicate='native_name'; reverse=!reverse">Role</td>
				<td style="width: 20%">Action</td>
			</tr>
			<tr ng-repeat="language in languages | orderBy:predicate:reverse">
				<td>
					<!-- non editable code --> <span e-name="code" e-form="rowform"> {{ language.code }} </span>
				</td>
				<td>
					<!-- editable native name --> <span editable-text="language.native_name" e-name="native_name" e-form="rowform" e-required> {{ language.native_name }} </span>
				</td>
				<td style="white-space: nowrap">
					<!-- form -->
					<form editable-form name="rowform" onbeforesave="saveLanguage($data, language.code)" ng-show="rowform.$visible" class="form-buttons form-inline" shown="inserted == language">
						<button type="submit" ng-disabled="rowform.$waiting" class="btn btn-primary btn-custom-default">Save</button>
						<button type="button" ng-disabled="rowform.$waiting" ng-click="rowform.$cancel()" class="btn btn-default btn-custom-cancel">Cancel</button>
					</form>
					<div class="buttons" ng-show="!rowform.$visible">
						<button class="btn btn-primary btn-custom-default" ng-click="rowform.$show()">Edit</button>
						<button class="btn btn-danger btn-custom-danger" ng-click="removeLanguage(language.code)">Delete</button>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<!-- h3>Test zone</h3>
	<pre>
		<p>Languages : {{ languages | json }}</p>
	</pre -->
</body>
</html>