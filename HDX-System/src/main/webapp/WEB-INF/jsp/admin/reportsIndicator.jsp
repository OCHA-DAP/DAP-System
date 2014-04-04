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
	<jsp:param name="which" value="reportsIndicator" />
	<jsp:param name="needs" value="languages,indicatorTypes" />
</jsp:include>
</head>
<body ng-controller="ReportsIndicatorCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div>
		<h3>Indicator-centric reports</h3>
		<div style="width: 300px;">
			<form role="form" class="css-form">
				<div class="form-group">
					<label for="indicatorType">Indicator type</label> <select class="form-control" id="indicatorType" ng-model="indicatorType" ng-change="indicatorTypeSelect()" ng-options="indicatorType.code for indicatorType in indicatorTypes" ng-class="default">
					</select>
				</div>
				<div class="form-group">
					<label for="source">Source</label> <select class="form-control" id="source" ng-disabled="sourceUnavailable" ng-model="source" ng-options="source.code for source in sources" ng-class="default">
					</select>
				</div>
				<div class="form-group">
					<label for="reportFormat">Report format</label> <select class="form-control" id="reportFormat" ng-model="reportFormat">
						<option selected="true">xlsx</option>
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
				<button type="button" class="btn btn-default" ng-disabled="sourceUnavailable" ng-click="createReport()">Create report</button>
				<!-- button type="button" class="btn btn-default" ng-click="publishReport()">Publish on CKAN</button -->
			</form>
		</div>
	</div>
	<div ng-show="showTestZone">
		<h3>Test zone</h3>
		<pre>
		<p>Languages : {{ languages | json }}</p>
		<p>Indicator types : {{ indicatorTypes | json }}</p>
	</pre>
	</div>
</body>
</html>