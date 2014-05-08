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
	<jsp:param name="which" value="reportsCountry" />
	<jsp:param name="needs" value="languages,entities" />
</jsp:include>
</head>
<body ng-controller="ReportsCountryCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div id="content">
		<h3>Country-centric reports</h3>
		<div>
			<form role="form" class="css-form">
				<div style="width: 300px;">
					<div class="form-group">
						<label for="country">Country</label> <select class="form-control" id="country" ng-model="country" ng-change="countrySelect()" ng-options="country.code for country in entities "
							ng-class="default">
						</select>
					</div>
					<div class="form-group">
						<label for="reportFormat">Report format</label> <select class="form-control" id="reportFormat" ng-model="reportFormat">
							<option selected="true">xlsx</option>
							<option>csv</option>
						</select>
					</div>
					<div class="form-group">
						<label for="fromYear">From year</label> <input type="number" class="form-control" id="fromYear" ng-model="fromYear" placeholder="From year...">
					</div>
					<div class="form-group">
						<label for="toYear">To year</label> <input type="number" class="form-control" id="toYear" ng-model="toYear" placeholder="To year...">
					</div>
					<div class="form-group">
						<label for="reportLanguage">Language</label> <select class="form-control" id="reportLanguage" ng-model="reportLanguage" ng-options="language.code for language in languages" ng-class="default">
						</select>
					</div>
					<!-- div class="form-group">
					<label for="reportFileName">File name</label> <input type="text" class="form-control" id="reportFileName" ng-model="reportFileName" placeholder="Report file name...">

				</div -->
				</div>
				<div style="width: 800px;">
					<button type="button" class="btn btn-primary btn-custom-default" ng-click="createReport('SW')">Create SW report</button>
					<button type="button" class="btn btn-primary btn-custom-default" ng-click="createTXTReadme('SW')">Create SW TXT Readme</button>
				</div>
				<div style="width: 800px; margin-top: 12px;">
					<button type="button" class="btn btn-primary btn-custom-default" ng-click="createReport('RW')">Create RW report</button>
					<button type="button" class="btn btn-primary btn-custom-default" ng-click="createTXTReadme('RW')">Create RW TXT Readme</button>
					<br />
				</div>

				<div style="width: 300px;">
					<div class="form-group" style="margin-top: 30px;">
						<label for="reportGroup">CKAN Country (Group)</label> <select class="form-control" id="ckanGroupId" ng-model="ckanGroupModel" ng-options="ckanGroup.name for ckanGroup in ckanGroups"
							ng-class="default" ng-change="groupSelect(ckanGroupModel)" ng-disabled="!grpLoaded">
						</select>
					</div>
					<div class="form-group">
						<label for="reportDataset">CKAN Dataset</label> <select class="form-control" id="ckanDatasetId" ng-model="ckanDatasetModel" ng-options="ckanDataset.name for ckanDataset in ckanDatasets"
							ng-class="default" ng-change="datasetSelect(ckanDatasetModel)" ng-disabled="!datasetLoaded">
						</select>
					</div>
					<div class="form-group">
						<label for="reportResource">CKAN Resource</label> <select class="form-control" id="ckanResourceId" ng-model="ckanResourceModel" ng-options="ckanResource.name for ckanResource in ckanResources"
							ng-class="default" ng-change="resourceSelect(ckanResourceModel)" ng-disabled="!resourceLoaded">
							<option>--Add as new resource--</option>

						</select>
					</div>
					<button type="button" class="btn btn-primary btn-custom-default" ng-click="savePublish()" ng-disabled="!saveBtn">Save & Publish</button>
				</div>
			</form>
		</div>
	</div>
	<div ng-show="showTestZone">
		<h3>Test zone</h3>
		<pre>
		<p>Languages : {{ languages | json }}</p>
		<p>Entities : {{ entities | json }}</p>
	</pre>
	</div>
</body>
</html>