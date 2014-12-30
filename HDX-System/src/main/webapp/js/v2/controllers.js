var hdxControllers = angular.module('hdxControllers', ['hdxServices', 'app']);

hdxControllers.controller('TestCtrl', ['$scope', '$rootScope', 'debuggingService', function($scope, $rootScope, debuggingService) {
	 debuggingService.getPendingPackagesUpdates().then(function (data) {
		  $scope.pendingPackagesUpdates = data.data;
	  });
}]);