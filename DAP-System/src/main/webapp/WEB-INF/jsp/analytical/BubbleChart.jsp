<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"
	scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
<!--Load the AJAX API-->
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript"
	src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript">
	// Load the Visualization API and the piechart package.
	google.load('visualization', '1', {
		'packages' : [ 'corechart' ]
	});

	// Set a callback to run when the Google Visualization API is loaded.
	google.setOnLoadCallback(drawChart);

	function drawChart() {
		var jsonData = $.ajax({
			url : "./json",
			dataType : "json",
			async : false
		}).responseText;

		// Create our data table out of JSON data loaded from server.
		var data = new google.visualization.DataTable(jsonData);

		// Instantiate and draw our chart, passing in some options.
		 var chart = new google.visualization.${it.model.chartType}(document.getElementById('chart_div'));
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
	
	function redirectWithFormParams(){
		var source1 = configForm.elements["source1"].value;
		var indicatorType1 = configForm.elements["indicatorType1"].value;
		var source2 = configForm.elements["source2"].value;
		var indicatorType2 = configForm.elements["indicatorType2"].value;
		var source3 = configForm.elements["source3"].value;
		var indicatorType3 = configForm.elements["indicatorType3"].value;
		var url = '${ctx}/api/yearly/year/2010/source1/' + source1 + '/indicatortype1/' + indicatorType1 + '/source2/' + source2 + '/indicatortype2/' + indicatorType2 + '/source3/' + source3 + '/indicatortype3/' + indicatorType3 + '/BubbleChart';
		window.location = url;
	}
</script>
</head>

</head>
<body>
	<jsp:include page="analytical-header.jsp" />
	<div>
		<form id="configForm">
			<label for="source1">Source 1</label>
			<select name="source1" id="source1">
				<c:forEach var="source" items="${it.sources}">
					<option value="${source.code}" <c:if test="${source.code eq it.source1}">selected</c:if> >${source.name}</option>
				</c:forEach>
			</select>
			
			<label for="indicatorType1">Indicator Type 1</label>
			<select name="indicatorType1" id="indicatorType1">
				<c:forEach var="indicatorType" items="${it.indicatorTypes}">
					<option value="${indicatorType.code}" <c:if test="${indicatorType.code eq it.indicatorType1}">selected</c:if> >${indicatorType.name}</option>
				</c:forEach>
			</select>
			
			<br/>
			<br/>
			<label for="source2">Source 2</label>
			<select name="source2" id="source2">
				<c:forEach var="source" items="${it.sources}">
					<option value="${source.code}" <c:if test="${source.code eq it.source2}">selected</c:if> >${source.name}</option>
				</c:forEach>
			</select>
			
			<label for="indicatorType2">Indicator Type 2</label>
			<select name="indicatorType2" id="indicatorType2">
				<c:forEach var="indicatorType" items="${it.indicatorTypes}">
					<option value="${indicatorType.code}" <c:if test="${indicatorType.code eq it.indicatorType2}">selected</c:if> >${indicatorType.name}</option>
				</c:forEach>
			</select>
			
			<br/>
			<br/>
			<label for="source3">Source 3</label>
			<select name="source3" id="source3">
				<c:forEach var="source" items="${it.sources}">
					<option value="${source.code}" <c:if test="${source.code eq it.source3}">selected</c:if> >${source.name}</option>
				</c:forEach>
			</select>
			
			<label for="indicatorType3">Indicator Type 3</label>
			<select name="indicatorType3" id="indicatorType3">
				<c:forEach var="indicatorType" items="${it.indicatorTypes}">
					<option value="${indicatorType.code}" <c:if test="${indicatorType.code eq it.indicatorType3}">selected</c:if> >${indicatorType.name}</option>
				</c:forEach>
			</select>

			<br/>
		</form>
		<button onclick="JavaScript:redirectWithFormParams()">Update</button>
	</div>
	<div id="chart_div" />
</body>
</html>