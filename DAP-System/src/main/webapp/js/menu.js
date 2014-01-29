app.controller('MenuCtrl', function($scope) {

  $scope.isActive = function(viewLocation) {
    var s = false;
    // var thePath = $location.path(); <-- does not work ???
    var thePath = window.location.href;
    console.log("The path : [" + thePath + "]");
    if (thePath.indexOf(viewLocation) != -1) {
      s = true;
    }
    return s;
  };

});