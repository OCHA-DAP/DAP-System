<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${pageContext.request.contextPath}" var="ctx" scope="request" />
<!DOCTYPE html>
<html ng-app="app">
<head>
<meta charset="UTF-8">
<jsp:include page="css-includes.jsp" />
<jsp:include page="js-includes.jsp">
	<jsp:param name="which" value="resources" />
</jsp:include>
</head>
<body ng-controller="ResourcesCtrl">
	<jsp:include page="admin-menu.jsp" />
	<div id="content">
		<h3>Detected CKAN Resources</h3>

		<table class="table table-bordered table-hover table-condensed">
			<tr>
				<th>Resource Name</th>
				<th>Parent Dataset</th>
				<th>Revision Id</th>
				<th>Revision Ts</th>
				<th>Configuration name</th>
				<th>Workflow State</th>
				<th>Action</th>
			</tr>

			<c:forEach var="ckanResource" items="${it}">
				<tr>
					<td title="Resource Id : ${ckanResource.id.id}">
						<c:if test="${previousId ne ckanResource.id.id}">${ckanResource.name}</c:if>
					</td>
					<td>
						<c:if test="${previousId ne ckanResource.id.id}">${ckanResource.parentDataset_name}</c:if>
					</td>
					<c:set scope="request" var="previousId" value="${ckanResource.id.id}" />
					<td>${fn:substring(ckanResource.id.revision_id, 0, 6)}...</td>
					<td>
						<fmt:formatDate value="${ckanResource.revision_timestamp}" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td>
						<a href="${ctx}/admin/misc/configurations/id/${ckanResource.resourceConfiguration.id}/edit/">${ckanResource.resourceConfiguration.name}</a>
					</td>
					<td>
						<c:if test="${ckanResource.workflowState eq 'FILE_PRE_VALIDATION_SUCCESS' || ckanResource.workflowState eq 'FILE_PRE_VALIDATION_FAIL'}">
							<a target="_blank" href="./${ckanResource.id.id}/${ckanResource.id.revision_id}/report"> ${ckanResource.workflowState} </a> evaluated by ${ckanResource.evaluator}
						</c:if>
						<c:if test="${ckanResource.workflowState eq 'IMPORT_SUCCESS' || ckanResource.workflowState eq 'IMPORT_FAIL'}">
							<a target="_blank" href="./${ckanResource.id.id}/${ckanResource.id.revision_id}/report"> Full validation Report </a> 
							<br />
							<a target="_blank" href="./${ckanResource.id.id}/${ckanResource.id.revision_id}/import-report">${ckanResource.workflowState} </a> imported by ${ckanResource.importer}
						</c:if>
					</td>
					<td>
						<c:if test="${ckanResource.isDownloadable()}">
							<a href="${ctx}/admin/status/manuallyTriggerDownload/${ckanResource.id.id}/${ckanResource.id.revision_id}/">download</a>
						</c:if>
						<c:if test="${ckanResource.workflowState eq 'DOWNLOADED'}">
							<a href="${ctx}/admin/status/manuallyTriggerEvaluation/${ckanResource.id.id}/${ckanResource.id.revision_id}/">evaluate</a>
						</c:if>
						<c:if test="${ckanResource.workflowState eq 'FILE_PRE_VALIDATION_SUCCESS'}">
							<a href="${ctx}/admin/status/manuallyTriggerImport/${ckanResource.id.id}/${ckanResource.id.revision_id}/">import</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>