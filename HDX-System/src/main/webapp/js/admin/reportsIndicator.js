app.controller('ReportsIndicatorCtrl', function($scope, $filter, $http, utilities) {

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
  $scope.reportFileName = "";
  $scope.reportFormat = "xlsx";
  $scope.reportLanguage = $scope.languages[0];

  $scope.sourceUnavailable = true;

  $scope.indicatorTypeSelect();

  $scope.createReport = function(which) {
    switch (which) {
    case 'SW':
      $scope.reportFileName = $scope.indicatorType.code + "_baseline";
      window.location.href = hdxContextRoot + "/api/exporter/indicator/" + $scope.reportFormat + "/" + $scope.indicatorType.code + "/source/" + $scope.source.code + "/fromYear/" + $scope.fromYear
          + "/toYear/" + $scope.toYear + "/language/" + $scope.reportLanguage.code + "/" + $scope.reportFileName + ".xlsx";
      break;

    case 'RW':
      $scope.reportFileName = "Reliefweb";
      window.location.href = hdxContextRoot + "/api/exporter/indicatorRW/" + $scope.reportFormat + "/fromYear/" + $scope.fromYear + "/toYear/" + $scope.toYear + "/language/"
          + $scope.reportLanguage.code + "/" + $scope.reportFileName + ".xlsx";
      break;

    default:
      break;
    }
  }
});