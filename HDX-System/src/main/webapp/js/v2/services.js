var hdxServices = angular.module('hdxServices', []);

hdxServices.service('debuggingService', [ '$http', function($http) {

	this.getPendingPackagesUpdates = function() {
		// Simple GET request example :
		return $http.get(hdxContextRoot + '/public/debug/pendingpackagesupdates');
	};
} ]);