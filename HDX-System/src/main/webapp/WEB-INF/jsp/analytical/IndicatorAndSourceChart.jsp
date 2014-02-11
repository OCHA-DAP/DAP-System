<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="ctx" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<!--Load the AJAX API-->
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
	// Load the Visualization API and the piechart package.
	google.load('visualization', '1', {
		'packages' : [ 'corechart' ]
	});

	// Set a callback to run when the Google Visualization API is loaded.
	google.setOnLoadCallback(drawChart);

	function drawChart() {
		var jsonData = $.ajax({
			url : "../json?${pageContext.request.queryString}",
			dataType : "json",
			async : false
		}).responseText;

		// Create our data table out of JSON data loaded from server.
		var data = new google.visualization.DataTable(jsonData);

		// Instantiate and draw our chart, passing in some options.
		var chart = new google.visualization.$
		{
			it.model.chartType
		}
		(document.getElementById('chart_div'));
		chart.draw(data, {
			title : "${it.model.title}",
			width : 1200,
			height : 600,
			vAxis : {
				title : "${it.model.vAxisTitle}"
			},
			hAxis : {
				title : "${it.model.hAxisTitle}"
			}
		});
	}

	function redirectWithFormParams() {
		var source = configForm.elements["source"].value;
		var indicatorType = configForm.elements["indicatorType"].value;
		var url = '${ctx}/api/yearly/source/' + source + '/indicatortype/'
				+ indicatorType
				+ '/${it.model.chartType}/?${pageContext.request.queryString}';
		window.location = url;
	}
</script>
</head>

</head>
<body>
	<jsp:include page="analytical-header.jsp" />
	<div>
		<form id="configForm">

			<label for="indicatorType">Indicator Type</label>
			<select name="indicatorType" id="indicatorType">
				<c:forEach var="indicatorType" items="${it.indicatorTypes}">
					<option value="${indicatorType.code}" <c:if test="${indicatorType.code eq it.indicatorType}">selected</c:if>>${indicatorType.name.defaultValue}</option>
				</c:forEach>
			</select>
			<label for="source">Source</label>
			<select name="source" id="source">
				<c:forEach var="source" items="${it.sources}">
					<option value="${source.code}" <c:if test="${source.code eq it.source}">selected</c:if>>${source.name.defaultValue}</option>
				</c:forEach>
			</select>


		</form>
		<button onclick="JavaScript:redirectWithFormParams()">Update</button>
		<div id="chart_div" />


		<script type="text/javascript">
			$("select#indicatorType")
					.change(
							function() {

								var indicatorType = configForm.elements["indicatorType"].value;
								var url = '${ctx}/api/sources/indicatorTypeCode/'
										+ indicatorType + '/';
								$
										.get(
												url,
												function(data) {

													$("select#source").html("");
													var options = '';
													for (var i = 0; i < data.length; i++) {
														options += '<option value="' + data[i].code + '">'
																+ data[i].name
																+ '</option>';
													}
													$("select#source").html(
															options);

												});
							});
		</script>
</body>
</html>