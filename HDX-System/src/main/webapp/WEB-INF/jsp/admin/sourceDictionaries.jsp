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
	<jsp:param name="which" value="sourceDictionaries" />
</jsp:include>
</head>
<body ng-controller="SourceDictionariesCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div>
		<h3>Add a new Source Dictionary</h3>
		<div>
			<form method="POST" action="" class="css-form" role="form">
				<div class="form-group" style="">
					<label for="entity">For Entity</label> <select class="form-control" name="source" id="source">
						<c:forEach var="source" items="${it.sources}">
							<option value="${source.id}">${source.name.defaultValue}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group" style="">
					<label for="unnormalizedName">Unnormalized Name</label> <input type="text" name="unnormalizedName" id="unnormalizedName" class="form-control" />
				</div>
				<div class="form-group" style="">
					<label for="importer">Importer</label> <input type="text" name="importer" id="importer" class="form-control" />
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form>
		</div>

		<h3>List of region dictionaries</h3>
		<table class="table table-bordered table-hover table-condensed">
			<tr>
				<th>Source name</th>
				<th>Importer</th>
				<th>Unnormalized Name</th>
			</tr>

			<c:forEach var="sourceDictionary" items="${it.sourceDictionaries}">
				<tr>
					<td>${sourceDictionary.source.name.defaultValue}</td>
					<td>${sourceDictionary.id.importer}</td>
					<td>${sourceDictionary.id.unnormalizedName}</td>
				</tr>
			</c:forEach>

		</table>
	</div>

</body>
</html>