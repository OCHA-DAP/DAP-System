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

  $scope.indicatorTypeSelect = function() {
    $scope.reportFileName = $scope.indicatorType.code + "_baseline";
    $scope.loadSources();
  }
  
  $scope.loadSources = function() {
    return $http.get(hdxContextRoot + '/admin/curated/sourcesForIndicatorType/json', {
      params : {
        indicatorTypeCode: $scope.indicatorType.code
      }
    }).success(function(data, status, headers, config) {
      $scope.sources = data;
      $scope.sourceUnavailable = true;
      if($scope.sources && 0 < $scope.sources.length) {
        $scope.source = $scope.sources[0];
        $scope.sourceUnavailable = false;
      }
    }).error(function(data, status, headers, config) {
      $scope.sourceUnavailable = true;
    });
  }
  
  var selected = $filter('filter')($scope.indicatorTypes, {
    code : "PVF020"
  });
  $scope.indicatorType = selected[0];
  $scope.fromYear = 1998;
  $scope.toYear = 2014;
  $scope.reportFileName = selected[0].code;
  $scope.reportFormat = "xlsx";
  $scope.reportLanguage = $scope.languages[0];
  
  $scope.sourceUnavailable = true;
    
  $scope.indicatorTypeSelect();
  
  $scope.createReport = function() {
    // Sample http://localhost:8080/hdx/api/indicator/country/xlsx/PVF020/fromYear/1998/toYear/2014/language/EN/Test.xlsx
    window.location.href = hdxContextRoot + "/api/exporter/indicator/" + $scope.reportFormat + "/" + $scope.indicatorType.code + "/source/" + $scope.source.code + "/fromYear/" + $scope.fromYear + "/toYear/" + $scope.toYear + "/language/" + $scope.reportLanguage.code + "/" + $scope.reportFileName + ".xlsx";
  } 
});