app.controller('ReportsIndicatorCtrl', function($scope, $filter, $http, utilities) {

  // Available reports
  $scope.reportTypes = [ {
    name : 'Data',
    formats : [ 'xlsx', 'csv' ]
  }, {
    name : 'Readme',
    formats : [ 'txt' ]
  }, {
    name : 'RW Data',
    formats : [ 'xlsx' ]
  }, {
    name : 'RW Readme',
    formats : [ 'txt' ]
  }, {
    name : 'FTS Data',
    formats : [ 'xlsx' ]
  }, {
    name : 'FTS Readme',
    formats : [ 'txt' ]
  } /* , {
    name : 'Metadata',
    formats : [ 'csv' ]
  }, {
    name : 'All Indicators metadata',
    formats : [ 'csv' ] 
  } */];

  $scope.reportFileName = "";
  $scope.reportFormat = "";
  $scope.reportType = $scope.reportTypes[0];
  $scope.updateFormat = function() {
    $scope.reportFormat = $scope.reportType.formats[0];
  }
  $scope.updateFormat();
  // /////////
  // Resources
  // /////////
  $scope.resources = function() {
    $scope.indicatorTypes = appData['indicatorTypes'];
    $scope.languages = appData['languages'];
  }
  $scope.resources();

  // /////////////////////////////
  // Indicators reports management
  // /////////////////////////////

  // On indicator type selection, 
  // reload the sources available for this indicator type.
  $scope.indicatorTypeSelect = function() {
    $scope.loadSources();
  }

  // Load the sources available for the current selected indicator type.
  $scope.loadSources = function() {
    return $http.get(hdxContextRoot + '/admin/curated/sourcesForIndicatorType/json', {
      params : {
        indicatorTypeCode : $scope.indicatorType.code
      }
    }).success(function(data, status, headers, config) {
      $scope.sources = data;
      $scope.sourceUnavailable = true;
      if ($scope.sources && 0 < $scope.sources.length) {
        $scope.source = $scope.sources[0];
        $scope.sourceUnavailable = false;
      }
    }).error(function(data, status, headers, config) {
      $scope.sourceUnavailable = true;
    });
  }

  // Display the default indicator type and other values.
  var selected = $filter('filter')($scope.indicatorTypes, {
    code : "PVF020"
  });
  $scope.indicatorType = selected[0];
  $scope.fromYear = 1998;
  $scope.toYear = 2014;
  $scope.reportLanguage = $scope.languages[0];

  $scope.sourceUnavailable = true;

  $scope.indicatorTypeSelect();

  $scope.createReport = function() {
    switch ($scope.reportType.name) {
    case 'Data':
      $scope.reportFileName = $scope.indicatorType.code + "_Baseline";
      window.location.href = hdxContextRoot + "/api/exporter/indicator/" + $scope.reportFormat + "/" + $scope.indicatorType.code + "/source/" + $scope.source.code + "/fromYear/" + $scope.fromYear
          + "/toYear/" + $scope.toYear + "/language/" + $scope.reportLanguage.code + "/" + $scope.reportFileName + "." + $scope.reportFormat;
      break;
    case 'Readme':
      window.location.href = hdxContextRoot + "/api/exporter/indicator/readme/" + $scope.indicatorType.code + "/source/" + $scope.source.code + "/language/" + $scope.reportLanguage.code + "/" + $scope.indicatorType.code + "_Readme.txt";
      break;
    case 'RW Data':
      $scope.reportFileName = "RW";
      window.location.href = hdxContextRoot + "/api/exporter/indicatorRW/" + $scope.reportFormat + "/fromYear/" + $scope.fromYear + "/toYear/" + $scope.toYear + "/language/"
          + $scope.reportLanguage.code + "/" + $scope.reportFileName + "." + $scope.reportFormat;
      break;
    case 'RW Readme':
      window.location.href = hdxContextRoot + "/api/exporter/indicatorRW/readme/language/" + $scope.reportLanguage.code + "/" + $scope.indicatorType.code + "_Readme.txt";
      break;
    case 'FTS Data':
      $scope.reportFileName = "FTS";
      window.location.href = hdxContextRoot + "/api/exporter/indicatorFTS/" + $scope.reportFormat + "/fromYear/" + $scope.fromYear + "/toYear/" + $scope.toYear + "/language/"
          + $scope.reportLanguage.code + "/" + $scope.reportFileName + "." + $scope.reportFormat;
      break;
    case 'FTS Readme':
      window.location.href = hdxContextRoot + "/api/exporter/indicatorFTS/readme/language/" + $scope.reportLanguage.code + "/" + $scope.indicatorType.code + "_Readme.txt";
      break;

    default:
      break;
    }
  }
});