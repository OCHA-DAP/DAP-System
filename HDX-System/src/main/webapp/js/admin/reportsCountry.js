app.controller('ReportsCountryCtrl', function($scope, $filter, utilities) {

  // /////////
  // Resources
  // /////////
  $scope.resources = function() {
    $scope.entities = $filter('filter')(appData['entities'], {
      entityType : 1
    });
    $scope.languages = appData['languages'];
    $scope.grpLoaded = false;
    $scope.datasetLoaded = false;
    $scope.resourceLoaded = false;
    $scope.saveBtn = false;
    utilities.loadResource($scope, 'ckanGroups', '/admin/misc/groups/json', function() {
      $scope.grpLoaded = true;
    });
  }
  $scope.resources();

  // //////////////////////////
  // Country reports management
  // //////////////////////////

  var selected = $filter('filter')($scope.entities, {
    code : "AFG"
  });
  $scope.country = selected[0];
  $scope.fromYear = 1990;
  $scope.toYear = 2014;
  $scope.reportFileName = "";
  $scope.reportFormat = "csv";
  $scope.reportLanguage = $scope.languages[0];

  $scope.countrySelect = function() {
  }
  $scope.countrySelect();

  $scope.createReport = function(which) {
    switch (which) {
    case 'SW':
      $scope.reportFileName = $scope.country.code + "_baseline";
      // Sample http://localhost:8080/hdx/api/exporter/country/xlsx/BEL/fromYear/1998/toYear/2014/language/EN/Test.xlsx
      window.location.href = hdxContextRoot + "/api/exporter/country/" + $scope.reportFormat + "/" + $scope.country.code + "/fromYear/" + $scope.fromYear + "/toYear/" + $scope.toYear + "/language/"
          + $scope.reportLanguage.code + "/" + $scope.reportFileName + "." + $scope.reportFormat;
      break;
    case 'RW':
      $scope.reportFileName = $scope.country.code + "_reliefweb";
      // Sample http://localhost:8080/hdx/api/exporter/country/xlsx/BEL/fromYear/1998/toYear/2014/language/EN/Test.xlsx
      window.location.href = hdxContextRoot + "/api/exporter/countryRW/" + $scope.reportFormat + "/" + $scope.country.code + "/fromYear/" + $scope.fromYear + "/toYear/" + $scope.toYear + "/language/"
          + $scope.reportLanguage.code + "/" + $scope.reportFileName + "." + $scope.reportFormat;
      break;

    default:
      break;
    }
  }

  $scope.createTXTReadme = function(which) {
    switch (which) {
    case 'SW':
      // Sample http://localhost:8080/hdx/api/exporter/country/readme/BEL/language/EN/ReadMe.txt
      window.location.href = hdxContextRoot + "/api/exporter/country/readme/" + $scope.country.code + "/language/" + $scope.reportLanguage.code + "/ReadMe.txt";
      break;

    default:
      break;
    }
  }

  $scope.groupSelect = function(data) {
    $scope.datasetLoaded = false;
    $scope.resourceLoaded = false;
    $scope.saveBtn = false;
    var id = data.id;
    var url = "/admin/misc/groups/id/" + id + "/json";
    utilities.loadResource($scope, 'ckanDatasets', url, function() {
      $scope.datasetLoaded = true;
      $scope.resourceLoaded = false;
    });
  }

  $scope.datasetSelect = function(data) {
    $scope.resourceLoaded = false;
    $scope.saveBtn = false;
    var id = data.id;
    var url = "/admin/misc/resources/id/" + id + "/json";
    utilities.loadResource($scope, 'ckanResources', url, function() {
      $scope.resourceLoaded = true;
      $scope.saveBtn = true;
    });
  }

  $scope.resourceSelect = function(data) {
    $scope.saveBtn = true;
  }

  $scope.savePublish = function(data) {
    alert("save");
  }

  //$scope.publishReport = function() {

  // Sample data.ochadata.net:9231/hdx-1.0.0/api/exporter/country/xlsx/SDN/fromYear/1998/toYear/2014/language/en/sdn.xlsx
  //	    window.location.href = hdxContextRoot + "/admin/exporterpublisher/country/" + $scope.reportFormat + "/" + $scope.country.code + "/fromYear/" + $scope.fromYear + "/toYear/" + $scope.toYear + "/language/" + $scope.reportLanguage.code + "/";
  //  } 

});