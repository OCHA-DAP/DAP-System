<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
  var dapContextRoot = "${ctx}";
</script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.10/angular.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
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
%><script src="${ctx}/js/admin/i18n.js"></script>
<script>
  var data_languages = [ {
    "code" : "EN",
    "native_name" : "English"
  }, {
    "code" : "ES",
    "native_name" : "Espanol"
  }, {
    "code" : "FR",
    "native_name" : "Français"
  }, {
    "code" : "IT",
    "native_name" : "Italiano"
  } ];
  appData['languages'] = data_languages;
</script>

<%
	}
%>
<%
	String needsRequest = request.getParameter("needs");
	if (null != needsRequest && !"".equals(needsRequest)) {
		List<String> needs = Arrays.asList(needsRequest.split(","));
		// TODO Temporary. All this is to be replaced with data coming from server. 
%>
<script>
<%if(needs.contains("sources")) {%>
  var data_sources = [ {
    "id" : 2,
    "code" : "acled",
    "name" : "Armed Conflict Location and Event Dataset",
    "link" : null,
    "text_id" : 13,
    "translations" : [ {
      "code" : "EN",
      "value" : "qwe"
    } ]
  } ];
  appData['sources'] = data_sources;
<%} // End if(needs.contains("sources")) {

if(needs.contains("roles")) {%>
  var data_roles = [ {
    "value" : "api",
    "text" : "api"
  }, {
    "value" : "admin",
    "text" : "admin"
  } ];
  appData['roles'] = data_roles;
<%} // End if(needs.contains("languages")) {

if(needs.contains("periodicities")) {%>
  var data_periodicities = [ {
    "value" : "NONE",
    "text" : "NONE"
  }, {
    "value" : "DAY",
    "text" : "DAY"
  }, {
    "value" : "WEEK",
    "text" : "WEEK"
  }, {
    "value" : "MONTH",
    "text" : "MONTH"
  }, {
    "value" : "QUARTER",
    "text" : "QUARTER"
  }, {
    "value" : "YEAR",
    "text" : "YEAR"
  }, {
    "value" : "FIVE_YEARS",
    "text" : "FIVE_YEARS"
  }, {
    "value" : "TEN_YEARS",
    "text" : "TEN_YEARS"
  } ];
  appData['periodicities'] = data_periodicities;
<%} // End if(needs.contains("periodicities")) {

		if(needs.contains("entityTypes")) {%>
  var data_entityTypes = [ {
    "id" : 1,
    "code" : "country",
    "name" : "Country",
    "text_id" : 18,
    "translations" : [ {
      "code" : "FR",
      "value" : "qwe"
    }, {
      "code" : "EN",
      "value" : "qwe"
    }, {
      "code" : "IT",
      "value" : "qwe"
    } ]
  }, {
    "id" : 2,
    "code" : "crisis",
    "name" : "Crisis",
    "text_id" : 19,
    "translations" : [ {
      "code" : "EN",
      "value" : "sdfg"
    }, {
      "code" : "IT",
      "value" : "qwe"
    } ]
  } ];
  appData['entityTypes'] = data_entityTypes;
<%} // End if(needs.contains("entityTypes")) {
				if(needs.contains("valueTypes")) {%>
  var data_valueTypes = [ {
    "value" : "TEXT",
    "text" : "TEXT"
  }, {
    "value" : "STRING",
    "text" : "STRING"
  }, {
    "value" : "NUMBER",
    "text" : "NUMBER"
  }, {
    "value" : "DATE",
    "text" : "DATE"
  }, {
    "value" : "DATETIME",
    "text" : "DATETIME"
  } ];
  appData['valueTypes'] = data_valueTypes;
<%} // End if(needs.contains("valueTypes")) {
				if(needs.contains("indicatorTypes")) {%>
  var data_indicatorTypes = [ {
    "id" : 5,
    "code" : "_emdat:total_affected",
    "name" : "Total affectedxvb",
    "unit" : "personsxvbxvb",
    "valueType" : "DATE",
    "text_id" : 9,
    "translations" : [ {
      "code" : "EN",
      "value" : "qwe"
    } ]
  }, {
    "id" : 8,
    "code" : "aaa",
    "name" : "aaa",
    "unit" : "aaa",
    "valueType" : "TEXT",
    "text_id" : 45,
    "translations" : []
  }, {
    "id" : 9,
    "code" : "aav",
    "name" : "aaav",
    "unit" : "aav",
    "valueType" : "NUMBER",
    "text_id" : 46,
    "translations" : []
  }, {
    "id" : 6,
    "code" : "PCX051",
    "name" : "Mobile cellular subscriptions",
    "unit" : "per 100 inhabitants",
    "valueType" : "NUMBER",
    "text_id" : 10,
    "translations" : []
  }, {
    "id" : 4,
    "code" : "PSE030",
    "name" : "GDP per capita, PPP",
    "unit" : "current international $",
    "valueType" : "NUMBER",
    "text_id" : 8,
    "translations" : []
  }, {
    "id" : 3,
    "code" : "PSP010",
    "name" : "Population (Total M+F)",
    "unit" : "persons",
    "valueType" : "NUMBER",
    "text_id" : 7,
    "translations" : []
  }, {
    "id" : 2,
    "code" : "PSP080",
    "name" : "Population Density",
    "unit" : "persons per square km",
    "valueType" : "NUMBER",
    "text_id" : 6,
    "translations" : []
  }, {
    "id" : 7,
    "code" : "PVF020",
    "name" : "Per capita food supply",
    "unit" : "kcal/capita/day",
    "valueType" : "NUMBER",
    "text_id" : 11,
    "translations" : []
  }, {
    "id" : 1,
    "code" : "PVX040",
    "name" : "Incidence of conflict",
    "unit" : "Count",
    "valueType" : "NUMBER",
    "text_id" : 5,
    "translations" : []
  } ];
  appData['indicatorTypes'] = data_indicatorTypes;
<%} // End if(needs.contains("indicatorTypes")) {
	
if(needs.contains("entities")) {%>
  var data_entities = [ {
    "id" : 7,
    "type" : 2,
    "code" : "AA",
    "name" : "Aaa",
    "text_id" : 48,
    "translations" : []
  }, {
    "id" : 1006,
    "type" : 1,
    "code" : "AD",
    "name" : "ANDORRA",
    "text_id" : 1006,
    "translations" : [ {
      "code" : "EN",
      "value" : "syfgyfg"
    }, {
      "code" : "IT",
      "value" : "qerer"
    }, {
      "code" : "ES",
      "value" : "wert"
    } ]
  }, {
    "id" : 1234,
    "type" : 1,
    "code" : "AE",
    "name" : "UNITED ARAB EMIRATES",
    "text_id" : 1234,
    "translations" : []
  }, {
    "id" : 1001,
    "type" : 1,
    "code" : "AF",
    "name" : "AFGHANISTAN",
    "text_id" : 1001,
    "translations" : []
  }, {
    "id" : 1010,
    "type" : 1,
    "code" : "AG",
    "name" : "ANTIGUA AND BARBUDA",
    "text_id" : 1010,
    "translations" : []
  }, {
    "id" : 1008,
    "type" : 1,
    "code" : "AI",
    "name" : "ANGUILLA",
    "text_id" : 1008,
    "translations" : []
  }, {
    "id" : 1003,
    "type" : 1,
    "code" : "AL",
    "name" : "ALBANIA",
    "text_id" : 1003,
    "translations" : []
  }, {
    "id" : 1012,
    "type" : 1,
    "code" : "AM",
    "name" : "ARMENIA",
    "text_id" : 1012,
    "translations" : []
  }, {
    "id" : 1007,
    "type" : 1,
    "code" : "AO",
    "name" : "ANGOLA",
    "text_id" : 1007,
    "translations" : []
  }, {
    "id" : 1009,
    "type" : 1,
    "code" : "AQ",
    "name" : "ANTARCTICA",
    "text_id" : 1009,
    "translations" : []
  }, {
    "id" : 1011,
    "type" : 1,
    "code" : "AR",
    "name" : "ARGENTINA",
    "text_id" : 1011,
    "translations" : []
  }, {
    "id" : 1005,
    "type" : 1,
    "code" : "AS",
    "name" : "AMERICAN SAMOA",
    "text_id" : 1005,
    "translations" : []
  }, {
    "id" : 1015,
    "type" : 1,
    "code" : "AT",
    "name" : "AUSTRIA",
    "text_id" : 1015,
    "translations" : []
  }, {
    "id" : 1014,
    "type" : 1,
    "code" : "AU",
    "name" : "AUSTRALIA",
    "text_id" : 1014,
    "translations" : []
  }, {
    "id" : 1013,
    "type" : 1,
    "code" : "AW",
    "name" : "ARUBA",
    "text_id" : 1013,
    "translations" : []
  }, {
    "id" : 1002,
    "type" : 1,
    "code" : "AX",
    "name" : "ÅLAND ISLANDS",
    "text_id" : 1002,
    "translations" : []
  }, {
    "id" : 1016,
    "type" : 1,
    "code" : "AZ",
    "name" : "AZERBAIJAN",
    "text_id" : 1016,
    "translations" : []
  }, {
    "id" : 1029,
    "type" : 1,
    "code" : "BA",
    "name" : "BOSNIA AND HERZEGOVINA",
    "text_id" : 1029,
    "translations" : []
  }, {
    "id" : 1020,
    "type" : 1,
    "code" : "BB",
    "name" : "BARBADOS",
    "text_id" : 1020,
    "translations" : []
  }, {
    "id" : 1019,
    "type" : 1,
    "code" : "BD",
    "name" : "BANGLADESH",
    "text_id" : 1019,
    "translations" : []
  }, {
    "id" : 1022,
    "type" : 1,
    "code" : "BE",
    "name" : "BELGIUM",
    "text_id" : 1022,
    "translations" : []
  }, {
    "id" : 1036,
    "type" : 1,
    "code" : "BF",
    "name" : "BURKINA FASO",
    "text_id" : 1036,
    "translations" : []
  }, {
    "id" : 1035,
    "type" : 1,
    "code" : "BG",
    "name" : "BULGARIA",
    "text_id" : 1035,
    "translations" : []
  }, {
    "id" : 1018,
    "type" : 1,
    "code" : "BH",
    "name" : "BAHRAIN",
    "text_id" : 1018,
    "translations" : []
  }, {
    "id" : 1037,
    "type" : 1,
    "code" : "BI",
    "name" : "BURUNDI",
    "text_id" : 1037,
    "translations" : []
  }, {
    "id" : 1024,
    "type" : 1,
    "code" : "BJ",
    "name" : "BENIN",
    "text_id" : 1024,
    "translations" : []
  }, {
    "id" : 1185,
    "type" : 1,
    "code" : "BL",
    "name" : "SAINT BARTHÉLEMY",
    "text_id" : 1185,
    "translations" : []
  }, {
    "id" : 1025,
    "type" : 1,
    "code" : "BM",
    "name" : "BERMUDA",
    "text_id" : 1025,
    "translations" : []
  }, {
    "id" : 1034,
    "type" : 1,
    "code" : "BN",
    "name" : "BRUNEI DARUSSALAM",
    "text_id" : 1034,
    "translations" : []
  }, {
    "id" : 1027,
    "type" : 1,
    "code" : "BO",
    "name" : "BOLIVIA, PLURINATIONAL STATE OF",
    "text_id" : 1027,
    "translations" : []
  }, {
    "id" : 1028,
    "type" : 1,
    "code" : "BQ",
    "name" : "BONAIRE, SINT EUSTATIUS AND SABA",
    "text_id" : 1028,
    "translations" : []
  }, {
    "id" : 1032,
    "type" : 1,
    "code" : "BR",
    "name" : "BRAZIL",
    "text_id" : 1032,
    "translations" : []
  }, {
    "id" : 1017,
    "type" : 1,
    "code" : "BS",
    "name" : "BAHAMAS",
    "text_id" : 1017,
    "translations" : []
  }, {
    "id" : 1026,
    "type" : 1,
    "code" : "BT",
    "name" : "BHUTAN",
    "text_id" : 1026,
    "translations" : []
  }, {
    "id" : 1031,
    "type" : 1,
    "code" : "BV",
    "name" : "BOUVET ISLAND",
    "text_id" : 1031,
    "translations" : []
  }, {
    "id" : 1030,
    "type" : 1,
    "code" : "BW",
    "name" : "BOTSWANA",
    "text_id" : 1030,
    "translations" : []
  }, {
    "id" : 1021,
    "type" : 1,
    "code" : "BY",
    "name" : "BELARUS",
    "text_id" : 1021,
    "translations" : []
  }, {
    "id" : 1023,
    "type" : 1,
    "code" : "BZ",
    "name" : "BELIZE",
    "text_id" : 1023,
    "translations" : []
  }, {
    "id" : 1040,
    "type" : 1,
    "code" : "CA",
    "name" : "CANADA",
    "text_id" : 1040,
    "translations" : []
  }, {
    "id" : 1048,
    "type" : 1,
    "code" : "CC",
    "name" : "COCOS (KEELING) ISLANDS",
    "text_id" : 1048,
    "translations" : []
  }, {
    "id" : 1052,
    "type" : 1,
    "code" : "CD",
    "name" : "CONGO, THE DEMOCRATIC REPUBLIC OF THE",
    "text_id" : 1052,
    "translations" : []
  }, {
    "id" : 1043,
    "type" : 1,
    "code" : "CF",
    "name" : "CENTRAL AFRICAN REPUBLIC",
    "text_id" : 1043,
    "translations" : []
  }, {
    "id" : 1051,
    "type" : 1,
    "code" : "CG",
    "name" : "CONGO",
    "text_id" : 1051,
    "translations" : []
  }, {
    "id" : 1216,
    "type" : 1,
    "code" : "CH",
    "name" : "SWITZERLAND",
    "text_id" : 1216,
    "translations" : []
  }, {
    "id" : 1055,
    "type" : 1,
    "code" : "CI",
    "name" : "CÔTE D'IVOIRE",
    "text_id" : 1055,
    "translations" : []
  }, {
    "id" : 1053,
    "type" : 1,
    "code" : "CK",
    "name" : "COOK ISLANDS",
    "text_id" : 1053,
    "translations" : []
  }, {
    "id" : 1045,
    "type" : 1,
    "code" : "CL",
    "name" : "CHILE",
    "text_id" : 1045,
    "translations" : []
  }, {
    "id" : 1039,
    "type" : 1,
    "code" : "CM",
    "name" : "CAMEROON",
    "text_id" : 1039,
    "translations" : []
  }, {
    "id" : 3,
    "type" : 1,
    "code" : "CMR",
    "name" : "Cameroon",
    "text_id" : 3,
    "translations" : [ {
      "code" : "EN",
      "value" : "Cameroon"
    }, {
      "code" : "IT",
      "value" : "Camerona"
    }, {
      "code" : "FR",
      "value" : "Camroon"
    } ]
  }, {
    "id" : 1046,
    "type" : 1,
    "code" : "CN",
    "name" : "CHINA",
    "text_id" : 1046,
    "translations" : []
  }, {
    "id" : 1049,
    "type" : 1,
    "code" : "CO",
    "name" : "COLOMBIA",
    "text_id" : 1049,
    "translations" : []
  }, {
    "id" : 1054,
    "type" : 1,
    "code" : "CR",
    "name" : "COSTA RICA",
    "text_id" : 1054,
    "translations" : []
  }, {
    "id" : 1057,
    "type" : 1,
    "code" : "CU",
    "name" : "CUBA",
    "text_id" : 1057,
    "translations" : []
  }, {
    "id" : 1041,
    "type" : 1,
    "code" : "CV",
    "name" : "CAPE VERDE",
    "text_id" : 1041,
    "translations" : []
  }, {
    "id" : 1058,
    "type" : 1,
    "code" : "CW",
    "name" : "CURAÇAO",
    "text_id" : 1058,
    "translations" : []
  }, {
    "id" : 1047,
    "type" : 1,
    "code" : "CX",
    "name" : "CHRISTMAS ISLAND",
    "text_id" : 1047,
    "translations" : []
  }, {
    "id" : 1059,
    "type" : 1,
    "code" : "CY",
    "name" : "CYPRUS",
    "text_id" : 1059,
    "translations" : []
  }, {
    "id" : 1060,
    "type" : 1,
    "code" : "CZ",
    "name" : "CZECH REPUBLIC",
    "text_id" : 1060,
    "translations" : []
  }, {
    "id" : 1083,
    "type" : 1,
    "code" : "DE",
    "name" : "GERMANY",
    "text_id" : 1083,
    "translations" : []
  }, {
    "id" : 1062,
    "type" : 1,
    "code" : "DJ",
    "name" : "DJIBOUTI",
    "text_id" : 1062,
    "translations" : []
  }, {
    "id" : 1061,
    "type" : 1,
    "code" : "DK",
    "name" : "DENMARK",
    "text_id" : 1061,
    "translations" : []
  }, {
    "id" : 1063,
    "type" : 1,
    "code" : "DM",
    "name" : "DOMINICA",
    "text_id" : 1063,
    "translations" : []
  }, {
    "id" : 1064,
    "type" : 1,
    "code" : "DO",
    "name" : "DOMINICAN REPUBLIC",
    "text_id" : 1064,
    "translations" : []
  }, {
    "id" : 1004,
    "type" : 1,
    "code" : "DZ",
    "name" : "ALGERIA",
    "text_id" : 1004,
    "translations" : []
  }, {
    "id" : 1065,
    "type" : 1,
    "code" : "EC",
    "name" : "ECUADOR",
    "text_id" : 1065,
    "translations" : []
  }, {
    "id" : 1070,
    "type" : 1,
    "code" : "EE",
    "name" : "ESTONIA",
    "text_id" : 1070,
    "translations" : []
  }, {
    "id" : 1066,
    "type" : 1,
    "code" : "EG",
    "name" : "EGYPT",
    "text_id" : 1066,
    "translations" : []
  }, {
    "id" : 1246,
    "type" : 1,
    "code" : "EH",
    "name" : "WESTERN SAHARA",
    "text_id" : 1246,
    "translations" : []
  }, {
    "id" : 1069,
    "type" : 1,
    "code" : "ER",
    "name" : "ERITREA",
    "text_id" : 1069,
    "translations" : []
  }, {
    "id" : 1209,
    "type" : 1,
    "code" : "ES",
    "name" : "SPAIN",
    "text_id" : 1209,
    "translations" : []
  }, {
    "id" : 1071,
    "type" : 1,
    "code" : "ET",
    "name" : "ETHIOPIA",
    "text_id" : 1071,
    "translations" : []
  }, {
    "id" : 1075,
    "type" : 1,
    "code" : "FI",
    "name" : "FINLAND",
    "text_id" : 1075,
    "translations" : []
  }, {
    "id" : 1074,
    "type" : 1,
    "code" : "FJ",
    "name" : "FIJI",
    "text_id" : 1074,
    "translations" : []
  }, {
    "id" : 1072,
    "type" : 1,
    "code" : "FK",
    "name" : "FALKLAND ISLANDS (MALVINAS)",
    "text_id" : 1072,
    "translations" : []
  }, {
    "id" : 1145,
    "type" : 1,
    "code" : "FM",
    "name" : "MICRONESIA, FEDERATED STATES OF",
    "text_id" : 1145,
    "translations" : []
  }, {
    "id" : 1073,
    "type" : 1,
    "code" : "FO",
    "name" : "FAROE ISLANDS",
    "text_id" : 1073,
    "translations" : []
  }, {
    "id" : 1076,
    "type" : 1,
    "code" : "FR",
    "name" : "FRANCE",
    "text_id" : 1076,
    "translations" : []
  }, {
    "id" : 1080,
    "type" : 1,
    "code" : "GA",
    "name" : "GABON",
    "text_id" : 1080,
    "translations" : []
  }, {
    "id" : 1235,
    "type" : 1,
    "code" : "GB",
    "name" : "UNITED KINGDOM",
    "text_id" : 1235,
    "translations" : []
  }, {
    "id" : 1088,
    "type" : 1,
    "code" : "GD",
    "name" : "GRENADA",
    "text_id" : 1088,
    "translations" : []
  }, {
    "id" : 1082,
    "type" : 1,
    "code" : "GE",
    "name" : "GEORGIA",
    "text_id" : 1082,
    "translations" : []
  }, {
    "id" : 1077,
    "type" : 1,
    "code" : "GF",
    "name" : "FRENCH GUIANA",
    "text_id" : 1077,
    "translations" : []
  }, {
    "id" : 1092,
    "type" : 1,
    "code" : "GG",
    "name" : "GUERNSEY",
    "text_id" : 1092,
    "translations" : []
  }, {
    "id" : 1084,
    "type" : 1,
    "code" : "GH",
    "name" : "GHANA",
    "text_id" : 1084,
    "translations" : []
  }, {
    "id" : 1085,
    "type" : 1,
    "code" : "GI",
    "name" : "GIBRALTAR",
    "text_id" : 1085,
    "translations" : []
  }, {
    "id" : 1087,
    "type" : 1,
    "code" : "GL",
    "name" : "GREENLAND",
    "text_id" : 1087,
    "translations" : []
  }, {
    "id" : 1081,
    "type" : 1,
    "code" : "GM",
    "name" : "GAMBIA",
    "text_id" : 1081,
    "translations" : []
  }, {
    "id" : 1093,
    "type" : 1,
    "code" : "GN",
    "name" : "GUINEA",
    "text_id" : 1093,
    "translations" : []
  }, {
    "id" : 1089,
    "type" : 1,
    "code" : "GP",
    "name" : "GUADELOUPE",
    "text_id" : 1089,
    "translations" : []
  }, {
    "id" : 1068,
    "type" : 1,
    "code" : "GQ",
    "name" : "EQUATORIAL GUINEA",
    "text_id" : 1068,
    "translations" : []
  }, {
    "id" : 1086,
    "type" : 1,
    "code" : "GR",
    "name" : "GREECE",
    "text_id" : 1086,
    "translations" : []
  }, {
    "id" : 1207,
    "type" : 1,
    "code" : "GS",
    "name" : "SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS",
    "text_id" : 1207,
    "translations" : []
  }, {
    "id" : 1091,
    "type" : 1,
    "code" : "GT",
    "name" : "GUATEMALA",
    "text_id" : 1091,
    "translations" : []
  }, {
    "id" : 1090,
    "type" : 1,
    "code" : "GU",
    "name" : "GUAM",
    "text_id" : 1090,
    "translations" : []
  }, {
    "id" : 1094,
    "type" : 1,
    "code" : "GW",
    "name" : "GUINEA-BISSAU",
    "text_id" : 1094,
    "translations" : []
  }, {
    "id" : 1095,
    "type" : 1,
    "code" : "GY",
    "name" : "GUYANA",
    "text_id" : 1095,
    "translations" : []
  }, {
    "id" : 1100,
    "type" : 1,
    "code" : "HK",
    "name" : "HONG KONG",
    "text_id" : 1100,
    "translations" : []
  }, {
    "id" : 1097,
    "type" : 1,
    "code" : "HM",
    "name" : "HEARD ISLAND AND MCDONALD ISLANDS",
    "text_id" : 1097,
    "translations" : []
  }, {
    "id" : 1099,
    "type" : 1,
    "code" : "HN",
    "name" : "HONDURAS",
    "text_id" : 1099,
    "translations" : []
  }, {
    "id" : 1056,
    "type" : 1,
    "code" : "HR",
    "name" : "CROATIA",
    "text_id" : 1056,
    "translations" : []
  }, {
    "id" : 1096,
    "type" : 1,
    "code" : "HT",
    "name" : "HAITI",
    "text_id" : 1096,
    "translations" : []
  }, {
    "id" : 1101,
    "type" : 1,
    "code" : "HU",
    "name" : "HUNGARY",
    "text_id" : 1101,
    "translations" : []
  }, {
    "id" : 1104,
    "type" : 1,
    "code" : "ID",
    "name" : "INDONESIA",
    "text_id" : 1104,
    "translations" : []
  }, {
    "id" : 1107,
    "type" : 1,
    "code" : "IE",
    "name" : "IRELAND",
    "text_id" : 1107,
    "translations" : []
  }, {
    "id" : 1109,
    "type" : 1,
    "code" : "IL",
    "name" : "ISRAEL",
    "text_id" : 1109,
    "translations" : []
  }, {
    "id" : 1108,
    "type" : 1,
    "code" : "IM",
    "name" : "ISLE OF MAN",
    "text_id" : 1108,
    "translations" : []
  }, {
    "id" : 1103,
    "type" : 1,
    "code" : "IN",
    "name" : "INDIA",
    "text_id" : 1103,
    "translations" : []
  }, {
    "id" : 1033,
    "type" : 1,
    "code" : "IO",
    "name" : "BRITISH INDIAN OCEAN TERRITORY",
    "text_id" : 1033,
    "translations" : []
  }, {
    "id" : 1106,
    "type" : 1,
    "code" : "IQ",
    "name" : "IRAQ",
    "text_id" : 1106,
    "translations" : []
  }, {
    "id" : 1105,
    "type" : 1,
    "code" : "IR",
    "name" : "IRAN, ISLAMIC REPUBLIC OF",
    "text_id" : 1105,
    "translations" : []
  }, {
    "id" : 1102,
    "type" : 1,
    "code" : "IS",
    "name" : "ICELAND",
    "text_id" : 1102,
    "translations" : []
  }, {
    "id" : 1110,
    "type" : 1,
    "code" : "IT",
    "name" : "ITALY",
    "text_id" : 1110,
    "translations" : []
  }, {
    "id" : 1113,
    "type" : 1,
    "code" : "JE",
    "name" : "JERSEY",
    "text_id" : 1113,
    "translations" : []
  }, {
    "id" : 1111,
    "type" : 1,
    "code" : "JM",
    "name" : "JAMAICA",
    "text_id" : 1111,
    "translations" : []
  }, {
    "id" : 1114,
    "type" : 1,
    "code" : "JO",
    "name" : "JORDAN",
    "text_id" : 1114,
    "translations" : []
  }, {
    "id" : 1112,
    "type" : 1,
    "code" : "JP",
    "name" : "JAPAN",
    "text_id" : 1112,
    "translations" : []
  }, {
    "id" : 1116,
    "type" : 1,
    "code" : "KE",
    "name" : "KENYA",
    "text_id" : 1116,
    "translations" : []
  }, {
    "id" : 1121,
    "type" : 1,
    "code" : "KG",
    "name" : "KYRGYZSTAN",
    "text_id" : 1121,
    "translations" : []
  }, {
    "id" : 1038,
    "type" : 1,
    "code" : "KH",
    "name" : "CAMBODIA",
    "text_id" : 1038,
    "translations" : []
  }, {
    "id" : 1117,
    "type" : 1,
    "code" : "KI",
    "name" : "KIRIBATI",
    "text_id" : 1117,
    "translations" : []
  }, {
    "id" : 1050,
    "type" : 1,
    "code" : "KM",
    "name" : "COMOROS",
    "text_id" : 1050,
    "translations" : []
  }, {
    "id" : 1187,
    "type" : 1,
    "code" : "KN",
    "name" : "SAINT KITTS AND NEVIS",
    "text_id" : 1187,
    "translations" : []
  }, {
    "id" : 1118,
    "type" : 1,
    "code" : "KP",
    "name" : "KOREA, DEMOCRATIC PEOPLE'S REPUBLIC OF",
    "text_id" : 1118,
    "translations" : []
  }, {
    "id" : 1119,
    "type" : 1,
    "code" : "KR",
    "name" : "KOREA, REPUBLIC OF",
    "text_id" : 1119,
    "translations" : []
  }, {
    "id" : 1120,
    "type" : 1,
    "code" : "KW",
    "name" : "KUWAIT",
    "text_id" : 1120,
    "translations" : []
  }, {
    "id" : 1042,
    "type" : 1,
    "code" : "KY",
    "name" : "CAYMAN ISLANDS",
    "text_id" : 1042,
    "translations" : []
  }, {
    "id" : 1115,
    "type" : 1,
    "code" : "KZ",
    "name" : "KAZAKHSTAN",
    "text_id" : 1115,
    "translations" : []
  }, {
    "id" : 1122,
    "type" : 1,
    "code" : "LA",
    "name" : "LAO PEOPLE'S DEMOCRATIC REPUBLIC",
    "text_id" : 1122,
    "translations" : []
  }, {
    "id" : 1124,
    "type" : 1,
    "code" : "LB",
    "name" : "LEBANON",
    "text_id" : 1124,
    "translations" : [ {
      "code" : "EN",
      "value" : "qwe"
    } ]
  }, {
    "id" : 1188,
    "type" : 1,
    "code" : "LC",
    "name" : "SAINT LUCIA",
    "text_id" : 1188,
    "translations" : []
  }, {
    "id" : 1128,
    "type" : 1,
    "code" : "LI",
    "name" : "LIECHTENSTEIN",
    "text_id" : 1128,
    "translations" : []
  }, {
    "id" : 1210,
    "type" : 1,
    "code" : "LK",
    "name" : "SRI LANKA",
    "text_id" : 1210,
    "translations" : []
  }, {
    "id" : 1126,
    "type" : 1,
    "code" : "LR",
    "name" : "LIBERIA",
    "text_id" : 1126,
    "translations" : []
  }, {
    "id" : 1125,
    "type" : 1,
    "code" : "LS",
    "name" : "LESOTHO",
    "text_id" : 1125,
    "translations" : []
  }, {
    "id" : 1129,
    "type" : 1,
    "code" : "LT",
    "name" : "LITHUANIA",
    "text_id" : 1129,
    "translations" : []
  }, {
    "id" : 1130,
    "type" : 1,
    "code" : "LU",
    "name" : "LUXEMBOURG",
    "text_id" : 1130,
    "translations" : []
  }, {
    "id" : 4,
    "type" : 1,
    "code" : "LUX",
    "name" : "Luxembourg",
    "text_id" : 4,
    "translations" : []
  }, {
    "id" : 1123,
    "type" : 1,
    "code" : "LV",
    "name" : "LATVIA",
    "text_id" : 1123,
    "translations" : []
  }, {
    "id" : 1127,
    "type" : 1,
    "code" : "LY",
    "name" : "LIBYA",
    "text_id" : 1127,
    "translations" : []
  }, {
    "id" : 1151,
    "type" : 1,
    "code" : "MA",
    "name" : "MOROCCO",
    "text_id" : 1151,
    "translations" : []
  }, {
    "id" : 1147,
    "type" : 1,
    "code" : "MC",
    "name" : "MONACO",
    "text_id" : 1147,
    "translations" : []
  }, {
    "id" : 1146,
    "type" : 1,
    "code" : "MD",
    "name" : "MOLDOVA, REPUBLIC OF",
    "text_id" : 1146,
    "translations" : []
  }, {
    "id" : 1149,
    "type" : 1,
    "code" : "ME",
    "name" : "MONTENEGRO",
    "text_id" : 1149,
    "translations" : []
  }, {
    "id" : 1189,
    "type" : 1,
    "code" : "MF",
    "name" : "SAINT MARTIN (FRENCH PART)",
    "text_id" : 1189,
    "translations" : []
  }, {
    "id" : 1133,
    "type" : 1,
    "code" : "MG",
    "name" : "MADAGASCAR",
    "text_id" : 1133,
    "translations" : []
  }, {
    "id" : 1139,
    "type" : 1,
    "code" : "MH",
    "name" : "MARSHALL ISLANDS",
    "text_id" : 1139,
    "translations" : []
  }, {
    "id" : 1132,
    "type" : 1,
    "code" : "MK",
    "name" : "MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF",
    "text_id" : 1132,
    "translations" : []
  }, {
    "id" : 1137,
    "type" : 1,
    "code" : "ML",
    "name" : "MALI",
    "text_id" : 1137,
    "translations" : []
  }, {
    "id" : 1153,
    "type" : 1,
    "code" : "MM",
    "name" : "MYANMAR",
    "text_id" : 1153,
    "translations" : []
  }, {
    "id" : 1148,
    "type" : 1,
    "code" : "MN",
    "name" : "MONGOLIA",
    "text_id" : 1148,
    "translations" : []
  }, {
    "id" : 1131,
    "type" : 1,
    "code" : "MO",
    "name" : "MACAO",
    "text_id" : 1131,
    "translations" : []
  }, {
    "id" : 1165,
    "type" : 1,
    "code" : "MP",
    "name" : "NORTHERN MARIANA ISLANDS",
    "text_id" : 1165,
    "translations" : []
  }, {
    "id" : 1140,
    "type" : 1,
    "code" : "MQ",
    "name" : "MARTINIQUE",
    "text_id" : 1140,
    "translations" : []
  }, {
    "id" : 1141,
    "type" : 1,
    "code" : "MR",
    "name" : "MAURITANIA",
    "text_id" : 1141,
    "translations" : []
  }, {
    "id" : 1150,
    "type" : 1,
    "code" : "MS",
    "name" : "MONTSERRAT",
    "text_id" : 1150,
    "translations" : []
  }, {
    "id" : 1138,
    "type" : 1,
    "code" : "MT",
    "name" : "MALTA",
    "text_id" : 1138,
    "translations" : []
  }, {
    "id" : 1142,
    "type" : 1,
    "code" : "MU",
    "name" : "MAURITIUS",
    "text_id" : 1142,
    "translations" : []
  }, {
    "id" : 1136,
    "type" : 1,
    "code" : "MV",
    "name" : "MALDIVES",
    "text_id" : 1136,
    "translations" : []
  }, {
    "id" : 1134,
    "type" : 1,
    "code" : "MW",
    "name" : "MALAWI",
    "text_id" : 1134,
    "translations" : []
  }, {
    "id" : 1144,
    "type" : 1,
    "code" : "MX",
    "name" : "MEXICO",
    "text_id" : 1144,
    "translations" : []
  }, {
    "id" : 1135,
    "type" : 1,
    "code" : "MY",
    "name" : "MALAYSIA",
    "text_id" : 1135,
    "translations" : []
  }, {
    "id" : 1152,
    "type" : 1,
    "code" : "MZ",
    "name" : "MOZAMBIQUE",
    "text_id" : 1152,
    "translations" : []
  }, {
    "id" : 1154,
    "type" : 1,
    "code" : "NA",
    "name" : "NAMIBIA",
    "text_id" : 1154,
    "translations" : []
  }, {
    "id" : 1158,
    "type" : 1,
    "code" : "NC",
    "name" : "NEW CALEDONIA",
    "text_id" : 1158,
    "translations" : []
  }, {
    "id" : 1161,
    "type" : 1,
    "code" : "NE",
    "name" : "NIGER",
    "text_id" : 1161,
    "translations" : []
  }, {
    "id" : 1164,
    "type" : 1,
    "code" : "NF",
    "name" : "NORFOLK ISLAND",
    "text_id" : 1164,
    "translations" : []
  }, {
    "id" : 1162,
    "type" : 1,
    "code" : "NG",
    "name" : "NIGERIA",
    "text_id" : 1162,
    "translations" : []
  }, {
    "id" : 1160,
    "type" : 1,
    "code" : "NI",
    "name" : "NICARAGUA",
    "text_id" : 1160,
    "translations" : []
  }, {
    "id" : 1157,
    "type" : 1,
    "code" : "NL",
    "name" : "NETHERLANDS",
    "text_id" : 1157,
    "translations" : []
  }, {
    "id" : 1166,
    "type" : 1,
    "code" : "NO",
    "name" : "NORWAY",
    "text_id" : 1166,
    "translations" : []
  }, {
    "id" : 1156,
    "type" : 1,
    "code" : "NP",
    "name" : "NEPAL",
    "text_id" : 1156,
    "translations" : []
  }, {
    "id" : 1155,
    "type" : 1,
    "code" : "NR",
    "name" : "NAURU",
    "text_id" : 1155,
    "translations" : []
  }, {
    "id" : 1163,
    "type" : 1,
    "code" : "NU",
    "name" : "NIUE",
    "text_id" : 1163,
    "translations" : []
  }, {
    "id" : 1159,
    "type" : 1,
    "code" : "NZ",
    "name" : "NEW ZEALAND",
    "text_id" : 1159,
    "translations" : []
  }, {
    "id" : 1167,
    "type" : 1,
    "code" : "OM",
    "name" : "OMAN",
    "text_id" : 1167,
    "translations" : []
  }, {
    "id" : 1171,
    "type" : 1,
    "code" : "PA",
    "name" : "PANAMA",
    "text_id" : 1171,
    "translations" : []
  }, {
    "id" : 1174,
    "type" : 1,
    "code" : "PE",
    "name" : "PERU",
    "text_id" : 1174,
    "translations" : []
  }, {
    "id" : 1078,
    "type" : 1,
    "code" : "PF",
    "name" : "FRENCH POLYNESIA",
    "text_id" : 1078,
    "translations" : []
  }, {
    "id" : 1172,
    "type" : 1,
    "code" : "PG",
    "name" : "PAPUA NEW GUINEA",
    "text_id" : 1172,
    "translations" : []
  }, {
    "id" : 1175,
    "type" : 1,
    "code" : "PH",
    "name" : "PHILIPPINES",
    "text_id" : 1175,
    "translations" : []
  }, {
    "id" : 1168,
    "type" : 1,
    "code" : "PK",
    "name" : "PAKISTAN",
    "text_id" : 1168,
    "translations" : []
  }, {
    "id" : 1177,
    "type" : 1,
    "code" : "PL",
    "name" : "POLAND",
    "text_id" : 1177,
    "translations" : []
  }, {
    "id" : 1190,
    "type" : 1,
    "code" : "PM",
    "name" : "SAINT PIERRE AND MIQUELON",
    "text_id" : 1190,
    "translations" : []
  }, {
    "id" : 1176,
    "type" : 1,
    "code" : "PN",
    "name" : "PITCAIRN",
    "text_id" : 1176,
    "translations" : []
  }, {
    "id" : 1179,
    "type" : 1,
    "code" : "PR",
    "name" : "PUERTO RICO",
    "text_id" : 1179,
    "translations" : []
  }, {
    "id" : 1170,
    "type" : 1,
    "code" : "PS",
    "name" : "PALESTINE, STATE OF",
    "text_id" : 1170,
    "translations" : []
  }, {
    "id" : 1178,
    "type" : 1,
    "code" : "PT",
    "name" : "PORTUGAL",
    "text_id" : 1178,
    "translations" : []
  }, {
    "id" : 1169,
    "type" : 1,
    "code" : "PW",
    "name" : "PALAU",
    "text_id" : 1169,
    "translations" : []
  }, {
    "id" : 1173,
    "type" : 1,
    "code" : "PY",
    "name" : "PARAGUAY",
    "text_id" : 1173,
    "translations" : []
  }, {
    "id" : 1180,
    "type" : 1,
    "code" : "QA",
    "name" : "QATAR",
    "text_id" : 1180,
    "translations" : []
  }, {
    "id" : 1181,
    "type" : 1,
    "code" : "RE",
    "name" : "RÉUNION",
    "text_id" : 1181,
    "translations" : []
  }, {
    "id" : 1182,
    "type" : 1,
    "code" : "RO",
    "name" : "ROMANIA",
    "text_id" : 1182,
    "translations" : []
  }, {
    "id" : 1197,
    "type" : 1,
    "code" : "RS",
    "name" : "SERBIA",
    "text_id" : 1197,
    "translations" : []
  }, {
    "id" : 1183,
    "type" : 1,
    "code" : "RU",
    "name" : "RUSSIAN FEDERATION",
    "text_id" : 1183,
    "translations" : []
  }, {
    "id" : 1,
    "type" : 1,
    "code" : "RUS",
    "name" : "Russia",
    "text_id" : 1,
    "translations" : [ {
      "code" : "EN",
      "value" : "Country"
    } ]
  }, {
    "id" : 1184,
    "type" : 1,
    "code" : "RW",
    "name" : "RWANDA",
    "text_id" : 1184,
    "translations" : []
  }, {
    "id" : 2,
    "type" : 1,
    "code" : "RWA",
    "name" : "Rwanda",
    "text_id" : 2,
    "translations" : []
  }, {
    "id" : 1195,
    "type" : 1,
    "code" : "SA",
    "name" : "SAUDI ARABIA",
    "text_id" : 1195,
    "translations" : []
  }, {
    "id" : 1204,
    "type" : 1,
    "code" : "SB",
    "name" : "SOLOMON ISLANDS",
    "text_id" : 1204,
    "translations" : []
  }, {
    "id" : 1198,
    "type" : 1,
    "code" : "SC",
    "name" : "SEYCHELLES",
    "text_id" : 1198,
    "translations" : []
  }, {
    "id" : 1211,
    "type" : 1,
    "code" : "SD",
    "name" : "SUDAN",
    "text_id" : 1211,
    "translations" : []
  }, {
    "id" : 1215,
    "type" : 1,
    "code" : "SE",
    "name" : "SWEDEN",
    "text_id" : 1215,
    "translations" : []
  }, {
    "id" : 5,
    "type" : 1,
    "code" : "sfvsf",
    "name" : "sdfsdf",
    "text_id" : 42,
    "translations" : []
  }, {
    "id" : 1200,
    "type" : 1,
    "code" : "SG",
    "name" : "SINGAPORE",
    "text_id" : 1200,
    "translations" : []
  }, {
    "id" : 1186,
    "type" : 1,
    "code" : "SH",
    "name" : "SAINT HELENA, ASCENSION AND TRISTAN DA CUNHA",
    "text_id" : 1186,
    "translations" : []
  }, {
    "id" : 1203,
    "type" : 1,
    "code" : "SI",
    "name" : "SLOVENIA",
    "text_id" : 1203,
    "translations" : []
  }, {
    "id" : 1213,
    "type" : 1,
    "code" : "SJ",
    "name" : "SVALBARD AND JAN MAYEN",
    "text_id" : 1213,
    "translations" : []
  }, {
    "id" : 1202,
    "type" : 1,
    "code" : "SK",
    "name" : "SLOVAKIA",
    "text_id" : 1202,
    "translations" : []
  }, {
    "id" : 1199,
    "type" : 1,
    "code" : "SL",
    "name" : "SIERRA LEONE",
    "text_id" : 1199,
    "translations" : []
  }, {
    "id" : 1193,
    "type" : 1,
    "code" : "SM",
    "name" : "SAN MARINO",
    "text_id" : 1193,
    "translations" : []
  }, {
    "id" : 1196,
    "type" : 1,
    "code" : "SN",
    "name" : "SENEGAL",
    "text_id" : 1196,
    "translations" : []
  }, {
    "id" : 1205,
    "type" : 1,
    "code" : "SO",
    "name" : "SOMALIA",
    "text_id" : 1205,
    "translations" : []
  }, {
    "id" : 1212,
    "type" : 1,
    "code" : "SR",
    "name" : "SURINAME",
    "text_id" : 1212,
    "translations" : []
  }, {
    "id" : 1208,
    "type" : 1,
    "code" : "SS",
    "name" : "SOUTH SUDAN",
    "text_id" : 1208,
    "translations" : []
  }, {
    "id" : 1194,
    "type" : 1,
    "code" : "ST",
    "name" : "SAO TOME AND PRINCIPE",
    "text_id" : 1194,
    "translations" : []
  }, {
    "id" : 1067,
    "type" : 1,
    "code" : "SV",
    "name" : "EL SALVADOR",
    "text_id" : 1067,
    "translations" : []
  }, {
    "id" : 1201,
    "type" : 1,
    "code" : "SX",
    "name" : "SINT MAARTEN (DUTCH PART)",
    "text_id" : 1201,
    "translations" : []
  }, {
    "id" : 1217,
    "type" : 1,
    "code" : "SY",
    "name" : "SYRIAN ARAB REPUBLIC",
    "text_id" : 1217,
    "translations" : []
  }, {
    "id" : 1214,
    "type" : 1,
    "code" : "SZ",
    "name" : "SWAZILAND",
    "text_id" : 1214,
    "translations" : []
  }, {
    "id" : 1230,
    "type" : 1,
    "code" : "TC",
    "name" : "TURKS AND CAICOS ISLANDS",
    "text_id" : 1230,
    "translations" : []
  }, {
    "id" : 1044,
    "type" : 1,
    "code" : "TD",
    "name" : "CHAD",
    "text_id" : 1044,
    "translations" : []
  }, {
    "id" : 1079,
    "type" : 1,
    "code" : "TF",
    "name" : "FRENCH SOUTHERN TERRITORIES",
    "text_id" : 1079,
    "translations" : []
  }, {
    "id" : 1223,
    "type" : 1,
    "code" : "TG",
    "name" : "TOGO",
    "text_id" : 1223,
    "translations" : []
  }, {
    "id" : 1221,
    "type" : 1,
    "code" : "TH",
    "name" : "THAILAND",
    "text_id" : 1221,
    "translations" : []
  }, {
    "id" : 1219,
    "type" : 1,
    "code" : "TJ",
    "name" : "TAJIKISTAN",
    "text_id" : 1219,
    "translations" : []
  }, {
    "id" : 1224,
    "type" : 1,
    "code" : "TK",
    "name" : "TOKELAU",
    "text_id" : 1224,
    "translations" : []
  }, {
    "id" : 1222,
    "type" : 1,
    "code" : "TL",
    "name" : "TIMOR-LESTE",
    "text_id" : 1222,
    "translations" : []
  }, {
    "id" : 1229,
    "type" : 1,
    "code" : "TM",
    "name" : "TURKMENISTAN",
    "text_id" : 1229,
    "translations" : []
  }, {
    "id" : 1227,
    "type" : 1,
    "code" : "TN",
    "name" : "TUNISIA",
    "text_id" : 1227,
    "translations" : []
  }, {
    "id" : 1225,
    "type" : 1,
    "code" : "TO",
    "name" : "TONGA",
    "text_id" : 1225,
    "translations" : []
  }, {
    "id" : 1228,
    "type" : 1,
    "code" : "TR",
    "name" : "TURKEY",
    "text_id" : 1228,
    "translations" : []
  }, {
    "id" : 1226,
    "type" : 1,
    "code" : "TT",
    "name" : "TRINIDAD AND TOBAGO",
    "text_id" : 1226,
    "translations" : []
  }, {
    "id" : 1231,
    "type" : 1,
    "code" : "TV",
    "name" : "TUVALU",
    "text_id" : 1231,
    "translations" : []
  }, {
    "id" : 1218,
    "type" : 1,
    "code" : "TW",
    "name" : "TAIWAN, PROVINCE OF CHINA",
    "text_id" : 1218,
    "translations" : []
  }, {
    "id" : 1220,
    "type" : 1,
    "code" : "TZ",
    "name" : "TANZANIA, UNITED REPUBLIC OF",
    "text_id" : 1220,
    "translations" : []
  }, {
    "id" : 1233,
    "type" : 1,
    "code" : "UA",
    "name" : "UKRAINE",
    "text_id" : 1233,
    "translations" : []
  }, {
    "id" : 1232,
    "type" : 1,
    "code" : "UG",
    "name" : "UGANDA",
    "text_id" : 1232,
    "translations" : []
  }, {
    "id" : 1237,
    "type" : 1,
    "code" : "UM",
    "name" : "UNITED STATES MINOR OUTLYING ISLANDS",
    "text_id" : 1237,
    "translations" : []
  }, {
    "id" : 1236,
    "type" : 1,
    "code" : "US",
    "name" : "UNITED STATES",
    "text_id" : 1236,
    "translations" : []
  }, {
    "id" : 1238,
    "type" : 1,
    "code" : "UY",
    "name" : "URUGUAY",
    "text_id" : 1238,
    "translations" : []
  }, {
    "id" : 1239,
    "type" : 1,
    "code" : "UZ",
    "name" : "UZBEKISTAN",
    "text_id" : 1239,
    "translations" : []
  }, {
    "id" : 1098,
    "type" : 1,
    "code" : "VA",
    "name" : "HOLY SEE (VATICAN CITY STATE)",
    "text_id" : 1098,
    "translations" : []
  }, {
    "id" : 1191,
    "type" : 1,
    "code" : "VC",
    "name" : "SAINT VINCENT AND THE GRENADINES",
    "text_id" : 1191,
    "translations" : []
  }, {
    "id" : 1241,
    "type" : 1,
    "code" : "VE",
    "name" : "VENEZUELA, BOLIVARIAN REPUBLIC OF",
    "text_id" : 1241,
    "translations" : []
  }, {
    "id" : 1243,
    "type" : 1,
    "code" : "VG",
    "name" : "VIRGIN ISLANDS, BRITISH",
    "text_id" : 1243,
    "translations" : []
  }, {
    "id" : 1244,
    "type" : 1,
    "code" : "VI",
    "name" : "VIRGIN ISLANDS, U.S.",
    "text_id" : 1244,
    "translations" : []
  }, {
    "id" : 1242,
    "type" : 1,
    "code" : "VN",
    "name" : "VIET NAM",
    "text_id" : 1242,
    "translations" : []
  }, {
    "id" : 1240,
    "type" : 1,
    "code" : "VU",
    "name" : "VANUATU",
    "text_id" : 1240,
    "translations" : []
  }, {
    "id" : 1245,
    "type" : 1,
    "code" : "WF",
    "name" : "WALLIS AND FUTUNA",
    "text_id" : 1245,
    "translations" : []
  }, {
    "id" : 1192,
    "type" : 1,
    "code" : "WS",
    "name" : "SAMOA",
    "text_id" : 1192,
    "translations" : []
  }, {
    "id" : 1247,
    "type" : 1,
    "code" : "YE",
    "name" : "YEMEN",
    "text_id" : 1247,
    "translations" : []
  }, {
    "id" : 1143,
    "type" : 1,
    "code" : "YT",
    "name" : "MAYOTTE",
    "text_id" : 1143,
    "translations" : []
  }, {
    "id" : 1206,
    "type" : 1,
    "code" : "ZA",
    "name" : "SOUTH AFRICA",
    "text_id" : 1206,
    "translations" : []
  }, {
    "id" : 1248,
    "type" : 1,
    "code" : "ZM",
    "name" : "ZAMBIA",
    "text_id" : 1248,
    "translations" : []
  }, {
    "id" : 1249,
    "type" : 1,
    "code" : "ZW",
    "name" : "ZIMBABWE",
    "text_id" : 1249,
    "translations" : []
  } ];
  appData['entities'] = data_entities;
<%} // End if(needs.contains("entities")) {
	} // End if (null != needsRequest && !"".equals(needsRequest)) {%>
  
</script>
