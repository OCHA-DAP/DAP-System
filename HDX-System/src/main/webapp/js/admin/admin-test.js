app.controller('TestCtrl', function($scope, $http, $rootScope) {

  $scope.available = [ {
    name : 'Sources',
    value : {
      name : 'sources',
      url : '/admin/curated/sources/json'
    }
  }, {
    name : 'Entity types',
    value : {
      name : 'entityTypes',
      url : '/admin/curated/entityTypes/json'
    }
  } ];
  $scope.which = "";
  // $scope.resource = [];

  $scope.go = function() {
    console.log("Loading resource " + $scope.which.value.name);
    utilities.loadResource($scope, $scope.which.value.name, $scope.which.value.url);
  }

  $scope.show = function(which) {
    switch (which) {
    case 'Sources':
      alert(angular.toJson($scope.sources));
      break;
    case 'Entity types':
      alert(angular.toJson($scope.entityTypes));
      break;
    case 'Resource':
      alert(angular.toJson($scope.resource));
      break;
    }
  }
});