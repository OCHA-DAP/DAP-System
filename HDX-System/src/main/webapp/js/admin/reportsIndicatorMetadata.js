app.controller('ReportsIndicatorMetadataCtrl', function($scope, $filter, $http, utilities) {

  // /////////
  // Resources
  // /////////
  $scope.resources = function() {
    $scope.indicatorTypes = appData['indicatorTypes'];
    $scope.languages = appData['languages'];
  }
  $scope.resources();

  // //////////////////////////////////////
  // Indicators metadata reports management
  // //////////////////////////////////////

  // On indicator type selection, 
  // set the file name accordingly.
  $scope.indicatorTypeSelect = function() {
    $scope.reportFileName = $scope.indicatorType.code + "_metadata";
  }
  
  // Display the default indicator type and other values.
  var selected = $filter('filter')($scope.indicatorTypes, {
    code : "PVF020"
  });
  $scope.indicatorType = selected[0];
  $scope.reportFileName = selected[0].code;
  $scope.reportFormat = "csv";
  // $scope.reportLanguage = $scope.languages[0];
  $scope.reportLanguage = {};
  $scope.reportLanguage.code = 'default';
  
  $scope.indicatorTypeSelect();
  
  $scope.createReport = function() {
    // Sample http://localhost:8080/hdx/api/indicator/country/xlsx/PVF020/fromYear/1998/toYear/2014/language/EN/Test.xlsx
    window.location.href = hdxContextRoot + "/api/exporter/indicatorMetadata/" + $scope.reportFormat + "/" + $scope.indicatorType.code + "/language/" + $scope.reportLanguage.code + "/" + $scope.reportFileName + ".csv";
  } 
});