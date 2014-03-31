<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
  var hdxContextRoot = "${ctx}";
</script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.10/angular.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.10.0.js"></script>
<script src="${ctx}/js/xeditable.min.js"></script>
<script src="${ctx}/js/admin/utilities.js"></script>
<script src="${ctx}/js/admin/init.js"></script>
<script src="${ctx}/js/admin/${param.which}.js"></script>
<script src="${ctx}/js/admin/menu.js"></script>
<script>
  var appData = {};
</script>
<%
	if ("true".equals(request.getParameter("i18n"))) {
%>
<script src="${ctx}/admin/misc/languages/json?var=data_languages"></script>
<script>
  appData['languages'] = data_languages;
</script>
<script src="${ctx}/js/admin/i18n.js"></script>
<%
	}
%>
<%
	String needsRequest = request.getParameter("needs");
	if (null != needsRequest && !"".equals(needsRequest)) {
		List<String> needs = Arrays.asList(needsRequest.split(","));
%>
<%
	if(needs.contains("search")) {
%>
<script src="${ctx}/js/admin/search.js"></script>
<%
	} // End if(needs.contains("search")) {

	if(needs.contains("columnsearch")) {
%>
<script src="${ctx}/js/admin/columnsearch.js"></script>
<%
	} // End if(needs.contains("columnsearch")) {

if(needs.contains("sources")) {
%>
<script src="${ctx}/admin/curated/sources/json?var=data_sources"></script>
<script>
  appData['sources'] = data_sources;
</script>
<%
	} // End if(needs.contains("sources")) {

	if(needs.contains("roles")) {
%>
<script src="${ctx}/admin/misc/users/roles/json?var=data_roles"></script>
<script>
  appData['roles'] = data_roles;
</script>
<%
	} // End if(needs.contains("roles")) {

	if(needs.contains("periodicities")) {
%>
<script src="${ctx}/admin/curated/indicators/periodicities/json?var=data_periodicities"></script>
<script>
  appData['periodicities'] = data_periodicities;
</script>
<%
	} // End if(needs.contains("periodicities")) {

	if(needs.contains("languages")) {
%>
<script src="${ctx}/admin/misc/languages/json?var=data_languages"></script>
<script>
  appData['languages'] = data_languages;
</script>
<%
	} // End if(needs.contains("periodicities")) {

		if(needs.contains("entityTypes")) {
%>
<script src="${ctx}/admin/curated/entityTypes/json?var=data_entityTypes"></script>
<script>
  appData['entityTypes'] = data_entityTypes;
</script>
<%
	} // End if(needs.contains("entityTypes")) {
    if(needs.contains("valueTypes")) {
%>
<script src="${ctx}/admin/curated/indicatorTypes/valueTypes/json?var=data_valueTypes"></script>
<script>
  appData['valueTypes'] = data_valueTypes;
</script>
<%
	} // End if(needs.contains("valueTypes")) {
    if(needs.contains("units")) {
%>
<script src="${ctx}/admin/curated/indicatorTypes/units/json?var=data_units"></script>
<script>
  appData['units'] = data_units;
</script>
<%
	} // End if(needs.contains("valueTypes")) {
		if(needs.contains("indicatorTypes")) {
%>
<script src="${ctx}/admin/curated/indicatorTypes/json?var=data_indicatorTypes"></script>
<script>
	appData['indicatorTypes'] = data_indicatorTypes;
</script>
<%
	} // End if(needs.contains("indicatorTypes")) {
	if(needs.contains("resourceConfigurations")) {
%>
<script src="${ctx}/admin/misc/configurations/json?var=data_resourceConfigurations"></script>
<script>
  appData['resourceConfigurations'] = data_resourceConfigurations;
</script>

<%
	} // End if(needs.contains("resourceConfigurations")) {
	if(needs.contains("entities")) {
%>
<script src="${ctx}/admin/curated/entities/json?var=data_entities"></script>
<script>
  appData['entities'] = data_entities;
</script>
<%
	} // End if(needs.contains("entities")) {
	} // End if (null != needsRequest && !"".equals(needsRequest)) {
%>
<script>
	// Activate tooltips
	jQuery(function($) {
		$(".hdx_tooltip").tooltip()
	});
</script>
