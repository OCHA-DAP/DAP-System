<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<c:set value="${pageContext.request.contextPath}" var="ctx" scope="request" />
<!DOCTYPE html>
<html ng-app="app">
<head>
<meta charset="UTF-8">
<jsp:include page="css-includes.jsp" />
<jsp:include page="js-includes.jsp">
	<jsp:param name="which" value="datasets" />
</jsp:include>
</head>
<body ng-controller="DatasetsCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div>
		<h3>List of datasets in CKAN</h3>
		<table class="table table-bordered table-hover table-condensed">
			<tr>
				<th><!-- a href="" ng-click="predicate='title'; reverse=!reverse" -->Title (name)<!-- /a --></th>
				<th><!-- a href="" ng-click="predicate='status'; reverse=!reverse" -->Status<!-- /a --></th>
				<th><!-- a href="" ng-click="predicate='type'; reverse=!reverse" -->Type<!-- /a --></th>
				<th>Choose a type and flag as to be curated</th>
				<th>Ignore a dataset</th>
			</tr>

			<c:forEach var="dataset" items="${it}">
				<tr>
					<td>${dataset.title}(${dataset.name})</td>
					<td>${dataset.status}</td>
					<td>${dataset.type}</td>
					<td>
						<form method="POST" action="flagDatasetAsToBeCurated">
							<input type="hidden" name="datasetName" value="${dataset.name}" /> <select name="type">
								<option value="SCRAPER_VALIDATING" selected="selected">SCRAPER_VALIDATING</option>
								<option value="DUMMY">DUMMY</option>
							</select> <input type="submit" value="flag for curation" />
						</form>
					</td>
					<td>
						<form method="POST" action="flagDatasetAsIgnored">
							<input type="hidden" name="datasetName" value="${dataset.name}" /> <input type="submit" <c:if test="${dataset.status eq 'IGNORED'}">disabled="disabled"</c:if> value="Ignore Dataset" />
						</form>
					</td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>