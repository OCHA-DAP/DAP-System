app.controller('UnitsCtrl', function($scope, $filter, utilities) {

  // The new unit
  $scope.newResource;

  /*
   * Units management
   */

  // Load units
  $scope.loadUnits = function() {
    return utilities.loadResource($scope, 'units', '/admin/curated/units/json', function() {
    });
  };
  $scope.loadUnits();

  // Create an unit
  $scope.createUnit = function(data) {
    var params = {};
    if (data && data.code) {
      angular.extend(params, {
        "code" : data.code
      });
    }
    if (data && data.name) {
      angular.extend(params, {
        "name" : data.name
      });
    }
    return utilities.createResource({
      validate : $scope.checkCreateForm,
      data : data,
      params : params,
      url : '/admin/curated/units/submitCreate',
      successCallback : function() {
        $scope.resetNewUnit();
        $scope.resetCreateResourceForm();
        $scope.loadUnits();
      },
      errorCallback : function() {
        alert("Unit creation threw an error. Maybe this unit already exists. No unit has been created.");
      }
    });
  };

  // Check that the new unit is complete
  $scope.checkCreateForm = function(data) {
    if (!data) {
      utilities.setFocus('newResource_code');
      return "At least some info should be provided.";
    }
    var code = data.code;
    if (!code || null === code || '' === code) {
      utilities.setFocus('newResource_code');
      return "Code cannot be empty.";
    }
    var name = data.name;
    if (!name || null === name || '' === name) {
      utilities.setFocus('newResource_name');
      return "Name cannot be empty.";
    }
    return "OK";
  };

  // Reset the new unit
  $scope.resetNewUnit = function() {
    if (!$scope.newResource) {
      $scope.newResource = {};
    }
    $scope.newResource.code = "";
    $scope.newResource.name = "";
  };

  // Reset the create resource form
  $scope.resetCreateResourceForm = function() {
    $scope.createResourceForm.$setPristine();
  };

  // Update an unit
  // ================
  $scope.updateUnit = function(data, id) {
    var params = {};
    angular.extend(params, {
      "id" : id
    });
    angular.extend(params, {
      "newName" : data.name
    });

    return utilities.updateResource({
      validate : $scope.checkUpdateForm,
      data : data,
      params : params,
      url : '/admin/curated/units/submitUpdate',
      successCallback : function() {
        $scope.loadUnits();
      },
      errorCallback : function() {
        alert("Unit update threw an error. No unit has been updated.");
        $scope.loadUnits();
      }
    });
  };

  // Check that the updated unit is valid
  $scope.checkUpdateForm = function(data) {
    var name = data.name;
    if (!name || null === name || '' === name) {
      return "Name cannot be empty.";
    }
    return "OK";
  };

  // Delete an unit
  $scope.deleteUnit = function(id) {
    return utilities.deleteResource({
      params : {
        "id" : id
      },
      url : '/admin/curated/units/submitDelete',
      successCallback : $scope.loadUnits,
      errorCallback : function() {
        alert("Unit deletion threw an error. Maybe this unit is used by some indicator. No unit has been deleted.");
      }
    });
  }

  // Get an unit by its id
  $scope.getUnitById = function(unitId) {
    var filteredUnits = $filter('filter')($scope.units, {
      id : unitId
    });
    var theUnit = filteredUnits && 0 < filteredUnits.length ? filteredUnits[0] : null;
    return theUnit;
  }
});