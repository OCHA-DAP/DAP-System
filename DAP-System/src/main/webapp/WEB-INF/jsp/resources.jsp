<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath}" var="ctx" scope="request" />
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx}/css/style.css" />
</head>
<body>
<jsp:include page="header.jsp" />
<h2>Detected CKAN Resources</h2>
	
	<table>
		<tr>
			<th>Id</th>
			<th>Revision Id</th>
			<th>Revision Ts</th>
			<th>Workflow State</th>
			<th>Action</th>
		</tr>
		
		<c:forEach var="ckanResource" items="${it}">
			<tr>
				<td><c:if test="${previousId ne ckanResource.id.id}">${ckanResource.id.id}</c:if></td>
				<c:set scope="request" var="previousId" value="${ckanResource.id.id}" />
				<td>${ckanResource.id.revision_id}</td>
				<td><fmt:formatDate value="${ckanResource.revision_timestamp}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${ckanResource.workflowState}</td>
				<td>
					<c:if test="${ckanResource.isDownloadable()}"><a href="${ctx}/admin/status/manuallyTriggerDownload/${ckanResource.id.id}/${ckanResource.id.revision_id}/">download</a> </c:if>
					<c:if test="${ckanResource.workflowState eq 'DOWNLOADED'}"><a href="${ctx}/admin/status/manuallyTriggerEvaluation/${ckanResource.id.id}/${ckanResource.id.revision_id}/">evaluate</a> </c:if>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	
</body>
</html>