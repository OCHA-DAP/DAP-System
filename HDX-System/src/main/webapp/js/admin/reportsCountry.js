app.controller('ReportsCountryCtrl', function($scope, $filter, utilities) {

  // /////////
  // Resources
  // /////////
  $scope.resources = function() {
    $scope.entities = $filter('filter')(appData['entities'], {
      entityType : 1
    });
    $scope.languages = appData['languages'];
  }
  $scope.resources();

  // //////////////////////////
  // Country reports management
  // //////////////////////////

  var selected = $filter('filter')($scope.entities, {
    code : "USA"
  });
  $scope.country = selected[0];
  $scope.fromYear = 1998;
  $scope.toYear = 2014;
  $scope.reportFileName = selected[0].code;
  $scope.reportFormat = "xlsx";
  $scope.reportLanguage = $scope.languages[0];
  
  $scope.countrySelect = function() {
    $scope.reportFileName = $scope.country.code;
  }
  
  $scope.createReport = function() {
    
    // Sample http://localhost:8080/hdx/api/exporter/xlsx/country/BEL/fromYear/1998/toYear/2014/language/EN/Test.xlsx
    // Sample http://localhost:8080/hdx/api/exporter/xlsx/country/COL/fromYear/1998/toYear/2014/language/FR/COL.xlsx
    window.location.href = hdxContextRoot + "/api/exporter/country/" + $scope.reportFormat + "/" + $scope.country.code + "/fromYear/" + $scope.fromYear + "/toYear/" + $scope.toYear + "/language/" + $scope.reportLanguage.code + "/" + $scope.reportFileName + ".xlsx";
  } 
  
  $scope.publishReport = function() {
	    
	    // Sample data.ochadata.net:9231/hdx-1.0.0/api/exporter/country/xlsx/SDN/fromYear/1998/toYear/2014/language/en/sdn.xlsx
	    window.location.href = hdxContextRoot + "/admin/exporterpublisher/country/" + $scope.reportFormat + "/" + $scope.country.code + "/fromYear/" + $scope.fromYear + "/toYear/" + $scope.toYear + "/language/" + $scope.reportLanguage.code + "/";
	  } 
  
  

});