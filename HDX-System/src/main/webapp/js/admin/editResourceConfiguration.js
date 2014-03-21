app.controller('EditResourceConfigurationCtrl', function($scope, $filter, $http, $rootScope, utilities) {

  // ////////////////////
  // Configurations management
  // ////////////////////

  $scope.resources = function() {
    $scope.sources = appData['sources'];
    $scope.indicatorTypes = appData['indicatorTypes'];
  }
  $scope.resources();

  // Sort management
  $scope.predicate = 'id';

  // Load resource Configuration
  // ==============
  $scope.ercFormVisibility = false;
  $scope.loadResourceConfiguration = function() {
    var id = getRESTParameter("/id/", "/");
    return utilities.loadResource($scope, 'editResourceConfiguration', '/admin/misc/configurations/id/' + id + '/json');
  }
  $scope.loadResourceConfiguration();

  function getRESTParameter(sep, suffix) {
    var partialString = window.location.href.split(sep)[1];
    var id = partialString.split(suffix)[0];
    return id;
  }

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
        $scope.loadResourceConfiguration();
      },
      errorCallback : function() {
        alert("Configuration creation threw an error. Maybe this configuration already exists. No configuration has been created.");
      }
    });
  }

  $scope.addGeneralRC = function(data, rcID) {

    return utilities.createResource({
      validate : $scope.checkCreateForm,
      data : data,
      params : {
        "rcID" : rcID,
        "key" : data.key.key,
        "value" : data.value
      },
      url : '/admin/misc/configurations/addGeneralConfiguration',
      successCallback : function() {
        $scope.resetNewConfiguration();
        $scope.resetCreateResourceForm();
        $scope.loadResourceConfiguration();
      },
      errorCallback : function() {
        alert("Configuration creation threw an error. Maybe this configuration already exists. No configuration has been created.");
      }
    });

  }

  $scope.addIndicatorRC = function(data, rcID) {
    return utilities.createResource({
      validate : $scope.checkIndicatorCreateForm,
      data : data,
      params : {
        "rcID" : rcID,
        "indTypeId" : data.indTypeCode.id,
        "sourceId" : data.sourceCode.id,
        "key" : data.key.key,
        "value" : data.value
      },
      url : '/admin/misc/configurations/addIndicatorConfiguration',
      successCallback : function() {
        $scope.resetIndicatorNewConfiguration();
        $scope.resetCreateIndicatorResourceForm();
        $scope.loadResourceConfiguration();
      },
      errorCallback : function() {
        alert("Indicator Configuration creation threw an error. Maybe this configuration already exists. No configuration has been created.");
      }
    });

  }

  // Check that the new general configuration is complete
  $scope.checkIndicatorCreateForm = function(data) {
    if (!data) {
      utilities.setFocus('newIndRC_key');
      return "At least some info should be provided.";
    }
    var key = data.key;
    if (!key || null === key || '' === key) {
      utilities.setFocus('newIndRC_key');
      return "Key cannot be empty.";
    }
    var value = data.value;
    if (!value || null === value || '' === value) {
      utilities.setFocus('newIndRC_value');
      return "Value cannot be empty.";
    }
    var itc = data.indTypeCode.id;
    if (!itc || null === itc || '' === itc) {
      utilities.setFocus('newIndRC_indTypeCode');
      return "Indicator Type cannot be empty.";
    }
    var src = data.sourceCode.id;
    if (!src || null === src || '' === src) {
      utilities.setFocus('newIndRC_sourceCode');
      return "Source cannot be empty.";
    }
    return "OK";
  };

  // Check that the new general configuration is complete
  $scope.checkCreateForm = function(data) {
    if (!data) {
      utilities.setFocus('newGenRC_key');
      return "At least some info should be provided.";
    }
    var key = data.key;
    if (!key || null === key || '' === key) {
      utilities.setFocus('newGenRC_key');
      return "Key cannot be empty.";
    }
    var value = data.value;
    if (!value || null === value || '' === value) {
      utilities.setFocus('newGenRC_value');
      return "Value cannot be empty.";
    }
    return "OK";
  };

  // Reset the new configuration
  $scope.resetNewConfiguration = function() {
    if (!$scope.newGenRC) {
      $scope.newGenRC = {};
    }
    $scope.newGenRC.key = "";
    $scope.newGenRC.value = "";
  };

  $scope.resetIndicatorNewConfiguration = function() {
    if (!$scope.newGenRC) {
      $scope.newIndRC = {};
    }
    $scope.newIndRC.indTypeCode = "";
    $scope.newIndRC.sourceCode = "";
    $scope.newIndRC.indConfKey = "";
    $scope.newIndRC.value = "";
  };

  $scope.resetCreateIndicatorResourceForm = function() {
    $scope.createIndicatorResourceForm.$setPristine();
  };

  // Reset the create resource form
  $scope.resetCreateResourceForm = function() {
    $scope.createGeneralResourceForm.$setPristine();
  };

  // Update a configuration
  // =================
  $scope.updateGC = function(data, gc) {
    return utilities.updateResource({
      validate : $scope.checkUpdateForm,
      data : data,
      params : {
        "id" : gc.id,
        "key" : gc.key,
        "value" : data.value
      },
      url : '/admin/misc/configurations/updateGeneralConfiguration',
      successCallback : function() {
        $scope.loadResourceConfiguration();
      },
      errorCallback : function() {
        alert("General Configuration update threw an error. No configuration has been updated.");
        $scope.loadResourceConfiguration();
      }
    });
  };

  // Check that the updated configuration is valid
  $scope.checkUpdateForm = function(data) {
    var value = data.value;
    if (!value || null === value || '' === value) {
      return "Value cannot be empty.";
    }
    return "OK";
  };

  // Delete a general configuration
  // =================
  $scope.deleteGC = function(rcID, id) {

    return utilities.deleteResource({
      params : {
        "rcID" : rcID,
        "id" : id
      },
      url : '/admin/misc/configurations/deleteGeneralConfiguration',
      successCallback : $scope.loadResourceConfiguration,
      errorCallback : function() {
        alert("Configuration deletion threw an error. Maybe this configuration is used in some translation. No configuration has been deleted.");
      }
    });
  };

  $scope.deleteIC = function(rcID, id) {
    return utilities.deleteResource({
      params : {
        "rcID" : rcID,
        "id" : id
      },
      url : '/admin/misc/configurations/deleteIndicatorConfiguration',
      successCallback : $scope.loadResourceConfiguration,
      errorCallback : function() {
        alert("Configuration deletion threw an error. Maybe this configuration is used in some translation. No configuration has been deleted.");
      }
    });
  };

  $scope.updateIC = function(data, ic) {
    return utilities.updateResource({
      validate : $scope.checkUpdateForm,
      data : data,
      params : {
        "id" : ic.id,
        "indTypeId" : data.indType,
        "sourceId" : data.src,
        "key" : ic.key,
        "value" : data.value
      },
      url : '/admin/misc/configurations/updateIndicatorConfiguration',
      successCallback : function() {
        $scope.loadResourceConfiguration();
      },
      errorCallback : function() {
        alert("General Configuration update threw an error. No configuration has been updated.");
        $scope.loadResourceConfiguration();
      }
    });
  };

  $scope.showIndicatorType = function(ic) {
    if (ic.indTypeId) {
      var selected = $filter('filter')($scope.indicatorTypes, {
        id : ic.indTypeId
      });
      return selected.length ? selected[0].code : 'Not set';
    } else {
      return ic.indTypeId || 'Not set';
    }
  };
  $scope.showSources = function(ic) {
    if (ic.srcId) {
      var selected = $filter('filter')($scope.sources, {
        id : ic.srcId
      });
      return selected.length ? selected[0].code : 'Not set';
    } else {
      return ic.srcId || 'Not set';
    }
  };

});