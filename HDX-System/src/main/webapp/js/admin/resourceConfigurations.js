app.controller('ResourceConfigurationsCtrl', function($scope, $filter, $http, $rootScope, utilities) {
  
  // ////////////////////
  // Configurations management
  // ////////////////////

  // Sort management
  $scope.predicate = 'id';

  // Load configurations
  // ==============
  $scope.loadConfigurations = function() {
    return utilities.loadResource($scope, 'resourceConfigurations', '/admin/misc/configurations/json');
  }
  $scope.loadConfigurations();

  // Create a configuration
  // =================
  $scope.createResourceConfiguration = function(data) {
    var name = data ? data.name : "";
    return utilities.createResource({
      validate : $scope.checkCreateForm,
      data : data,
      params : {
        //"code" : code,
        "name" : name
      },
      url : '/admin/misc/configurations/submitCreate',
      successCallback : function() {
        $scope.resetNewConfiguration();
        $scope.resetCreateResourceForm();
        $scope.loadConfigurations();
      },
      errorCallback : function() {
        alert("Configuration creation threw an error. Maybe this configuration already exists. No configuration has been created.");
      }
    });
  }

  // Check that the new configuration is complete
  $scope.checkCreateForm = function(data) {
    if (!data) {
      utilities.setFocus('newResource_name');
      return "At least some info should be provided.";
    }
    var name = data.name;
    if (!name || null === name || '' === name) {
      utilities.setFocus('newResource_name');
      return "Name cannot be empty.";
    }
    return "OK";
  };

  // Reset the new configuration
  $scope.resetNewConfiguration = function() {
    if (!$scope.newResource) {
      $scope.newResource = {};
    }
    $scope.newResource.name = "";
    //$scope.newResource.native_name = "";
  };

  // Reset the create resource form
  $scope.resetCreateResourceForm = function() {
    $scope.createResourceForm.$setPristine();
  };

  // Update a configuration
  // =================
  $scope.updateResourceConfiguration = function(data, id) {
    return utilities.updateResource({
      validate : $scope.checkUpdateForm,
      data : data,
      params : {
        "id" : id,
        "name" : data.name
      },
      url : '/admin/misc/configurations/submitUpdate',
      successCallback : function() {
        $scope.loadConfigurations();
      },
      errorCallback : function() {
        alert("Configuration update threw an error. No configuration has been updated.");
        $scope.loadConfigurations();
      }
    });
  };

  // Check that the updated configuration is valid
  $scope.checkUpdateForm = function(data) {
    var name = data.name;
    if (!name || null === name || '' === name) {
      return "Name cannot be empty.";
    }
    return "OK";
  };

  // Delete a configuration
  // =================
  $scope.deleteResourceConfiguration = function(id) {
    return utilities.deleteResource({
      params : {
        "id" : id
      },
      url : '/admin/misc/configurations/submitDelete',
      successCallback : $scope.loadConfigurations,
      errorCallback : function() {
        alert("Configuration deletion threw an error. Maybe this configuration is used in some translation. No configuration has been deleted.");
      }
    });
  };
  
  $scope.editConfiguration = function(id) {
	  	//alert (id);
	    return window.location.href = hdxContextRoot + '/admin/misc/configurations/id/'+id+"/edit/";
	  }
  
});