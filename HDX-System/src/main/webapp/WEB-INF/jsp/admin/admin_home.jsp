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
	<jsp:param name="which" value="importsFromCKAN" />
</jsp:include>
</head>
<body ng-controller="ImportsFromCKANCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div id="content">
		<h3>Welcome to HDX / admin !</h3>
		<p>Please use these short links or the menu above.</p>
		<div>
			<table class="welcome_table">
				<tr>
					<td style="width: 50%;"><div class="welcome_content">
							<h4>Status</h4>
							<ul>
								<li><a href="${ctx}/admin/status/datasets/">Detected CKAN datasets</a></li>
								<li><a href="${ctx}/admin/status/resources/">Detected CKAN resources</a></li>
								<li style="visibility: hidden;">&nbsp;</li>
								<li style="visibility: hidden;">&nbsp;</li>
							</ul>
						</div></td>
					<td style="width: 50%;"><div class="welcome_content">
							<h4>Curated data</h4>
							<ul>
								<li><a href="${ctx}/admin/curated/entities/">Entities</a></li>
								<li><a href="${ctx}/admin/curated/indicatorTypes/">Indicator types</a></li>
								<li><a href="${ctx}/admin/curated/indicators/">Indicators</a></li>
								<li><a href="${ctx}/admin/curated/dataSeries/">Data Series</a></li>
							</ul>
						</div></td>
				</tr>
				<tr>
					<td><div class="welcome_content">
							<h4>Import configurations</h4>
							<ul>
								<li><a href="${ctx}/admin/misc/configurations/">Manage configurations</a></li>
								<li style="visibility: hidden;">&nbsp;</li>
								<li style="visibility: hidden;">&nbsp;</li>
								<li style="visibility: hidden;">&nbsp;</li>
							</ul>
						</div></td>
					<td><div class="welcome_content">
							<h4>Reports</h4>
							<ul>
								<li ng-class="{ active: isActive('${ctx}/admin/reports/country', 'reports') }"><a href="${ctx}/admin/reports/country/">Country-centric</a></li>
								<li ng-class="{ active: isActive('${ctx}/admin/reports/indicator/', 'reports') }"><a href="${ctx}/admin/reports/indicator/">Indicator-centric</a></li>
								<li ng-class="{ active: isActive('${ctx}/admin/reports/indicatorMetadata', 'reports') }"><a href="${ctx}/admin/reports/indicatorMetadata/">Metadata</a></li>
								<li style="visibility: hidden;">&nbsp;</li>
							</ul>
						</div></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>