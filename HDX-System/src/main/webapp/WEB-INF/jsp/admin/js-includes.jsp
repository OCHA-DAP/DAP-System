<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
  var hdxContextRoot = "${ctx}";
</script>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.10/angular.min.js"></script>
<script type="text/javascript" src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.10.0.js"></script>
<script type="text/javascript" src="${ctx}/js/xeditable.min.js"></script>
<script type="text/javascript" src="${ctx}/js/loading-bar.min.js"></script>
<script type="text/javascript" src="${ctx}/js/admin/utilities.js"></script>
<script type="text/javascript" src="${ctx}/js/admin/init.js"></script>
<script type="text/javascript" src="${ctx}/js/admin/${param.which}.js"></script>
<script type="text/javascript" src="${ctx}/js/admin/menu.js"></script>
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

	if(needs.contains("dataValidators")) {
%>
<script src="${ctx}/admin/curated/dataValidators/json?var=data_dataValidators"></script>
<script>
	appData['dataValidators'] = data_dataValidators;
</script>
<%
	} // End if(needs.contains("sources")) {

	if(needs.contains("sources")) {
%>
<script src="${ctx}/admin/curated/sources/json?var=data_sources"></script>
<script>
	appData['sources'] = data_sources;
</script>
<%
	} // End if(needs.contains("sources")) {

	if(needs.contains("organizations")) {
%>
<script src="${ctx}/admin/curated/organizations/json?var=data_organizations"></script>
<script>
	appData['organizations'] = data_organizations;
</script>
<%
	} // End if(needs.contains("organizations")) {

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
    if(needs.contains("regionDictionaries")) {
%>
<script src="${ctx}/admin/dictionaries/regions/json?var=data_regionDictionaries"></script>
<script>
    appData['regionDictionaries'] = data_regionDictionaries;
</script>
<%
    } // End if(needs.contains("regionDictionaries")) {
    if(needs.contains("sourceDictionaries")) {
%>
<script src="${ctx}/admin/dictionaries/sources/json?var=data_sourceDictionaries"></script>
<script>
    appData['sourceDictionaries'] = data_sourceDictionaries;
</script>
<%
    } // End if(needs.contains("sourceDictionaries")) {
    if(needs.contains("indicatorTypeDictionaries")) {
%>
<script src="${ctx}/admin/dictionaries/indicatorTypes/json?var=data_indicatorTypeDictionaries"></script>
<script>
    appData['indicatorTypeDictionaries'] = data_indicatorTypeDictionaries;
</script>
<%
    } // End if(needs.contains("indicatorTypeDictionaries")) {
	if(needs.contains("entities")) {
%>
<script src="${ctx}/admin/curated/entities/json?var=data_entities"></script>
<script>
  appData['entities'] = data_entities;
</script>
<%
	} // End if(needs.contains("entities")) {
    if(needs.contains("importers")) {
%>
<script src="${ctx}/admin/importers/json?var=data_importers"></script>
<script>
    appData['importers'] = data_importers;
</script>
<%
    } // End if(needs.contains("importers")) {
	} // End if (null != needsRequest && !"".equals(needsRequest)) {
%>
<script>
  // Activate tooltips
  jQuery(function($) {
    $(".hdx_tooltip").tooltip()
  });
</script>
