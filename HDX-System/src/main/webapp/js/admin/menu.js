if (typeof app != 'undefined') {
  app.controller('MenuCtrl', function($scope, $rootScope) {

    // Show or hide the test zone
    $rootScope.showTestZone = false;

    $rootScope.toggleTestZone = function() {
      $rootScope.showTestZone = !$rootScope.showTestZone;
    }

    // Highlight the active menu item
    $scope.isActive = function(viewLocation) {
      var s = false;
      // var thePath = $location.path(); <-- does not work ???
      var thePath = window.location.href;
      // console.log("The path : [" + thePath + "]");
      if (thePath.indexOf(viewLocation) != -1) {
        s = true;
      }
      return s;
    };

  });
}