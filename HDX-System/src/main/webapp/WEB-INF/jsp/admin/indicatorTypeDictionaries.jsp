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
	<jsp:param name="which" value="indicatorTypeDictionaries" />
</jsp:include>
</head>
<body ng-controller="IndicatorTypeDictionariesCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div id="content">
		<h3>Add a new Indicator Type Dictionary</h3>
		<div>
			<form novalidate name="createResourceForm" class="css-form">
				<div class="form-group">
					<label for="entity">For indicator type</label> <select class="form-control" name="indicatorType" id="newResource_indicatorTypeId" ng-model="newResource.indicatorTypeId">
						<c:forEach var="indicatorType" items="${it.indicatorTypes}">
							<option value="${indicatorType.id}">${indicatorType.name.defaultValue}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label for="unnormalizedName">Unnormalized Name</label> <input type="text" name="unnormalizedName" id="newResource_unnormalizedName" ng-model="newResource.unnormalizedName" class="form-control" />
				</div>
				<div class="form-group">
					<label for="importer">Importer</label>
					<!-- input type="text" name="importer" id="importer" class="form-control" / -->
					<select class="form-control" name="importer" id="newResource_importer" ng-model="newResource.importer">
						<c:forEach var="importer" items="${it.importers}">
							<option value="${importer}">${importer}</option>
						</c:forEach>
					</select>
				</div>
				<button class="btn btn-primary btn-custom-default" ng-click="createDictionary(newResource)">Add</button>
			</form>
		</div>
		<h3>List of Indicator Type dictionaries</h3>
		<table class="table table-bordered table-hover table-condensed">
			<tr>
				<th>Indicator Type name</th>
				<th>Importer</th>
				<th>Unnormalized Name</th>
				<th>Action</th>
			</tr>

			<c:forEach var="indicatorTypeDictionary" items="${it.indicatorTypeDictionaries}">
				<tr>
					<td>${indicatorTypeDictionary.indicatorType.name.defaultValue}</td>
					<td>${indicatorTypeDictionary.id.importer}</td>
					<td>${indicatorTypeDictionary.id.unnormalizedName}</td>
					<td>
						<form method="POST" action="submitDelete">
							<input type="hidden" name="unnormalizedName" value="${indicatorTypeDictionary.id.unnormalizedName}" /> <input type="hidden" name="importer" value="${indicatorTypeDictionary.id.importer}" /> <input
								type="submit" value="Delete" />
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>