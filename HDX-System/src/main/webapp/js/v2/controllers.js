var hdxControllers = angular.module('hdxControllers', ['hdxServices']);

hdxControllers.controller('TestCtrl', ['$scope', 'debuggingService', function($scope, debuggingService) {
	 debuggingService.getPendingPackagesUpdates().then(function (data) {
		  $scope.pendingPackagesUpdates = data.data;
	  });
}]);