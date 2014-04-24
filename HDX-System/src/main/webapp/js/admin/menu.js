if (typeof app != 'undefined') {
  app.controller('MenuCtrl', function($scope, $rootScope) {

    // Show or hide the test zone
    $rootScope.showTestZone = false;

    $rootScope.toggleTestZone = function() {
      $rootScope.showTestZone = !$rootScope.showTestZone;
    }

    // Highlight the active menu and  menu item
    $scope.statusActive = false;
    $scope.curatedDataActive = false;
    $scope.importConfigurationsActive = false;
    $scope.reportsActive = false;
    $scope.adminActive = false;

      // Highlight the active menu item
    $scope.isActive = function(viewLocation, which) {
      var s = false;
      // var thePath = $location.path(); <-- does not work ???
      var thePath = window.location.href;
      if (thePath.indexOf(viewLocation) != -1) {
        s = true;
        $scope.statusActive = "status" === which;
        $scope.curatedDataActive = "curatedData" === which;
        $scope.importConfigurationsActive = "importConfigurations" === which;
        $scope.reportsActive = "reports" === which;
        $scope.adminActive = "admin" === which;
      }
      return s;
    };

  });
}