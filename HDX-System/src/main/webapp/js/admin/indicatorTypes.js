app.controller('IndicatorTypesCtrl', function($scope, $filter, utilities) {

  // /////////
  // Resources
  // /////////
  $scope.resources = function() {
    $scope.valueTypes = appData['valueTypes'];
    $scope.units = appData['units'];
  }
  $scope.resources();

  // The new indicator
  $scope.newResource;

  // //////////////////////////
  // Indicator types management
  // //////////////////////////

  // Sort management
  $scope.predicate = 'code';

  // Load indicator types
  // ====================
  $scope.loadIndicatorTypes = function() {
    return utilities.loadResource($scope, 'indicatorTypes', '/admin/curated/indicatorTypes/json', function() {
      // $scope.resetNewTranslations();
    });
  }
  $scope.loadIndicatorTypes();

  // Load value types
  // ================
  /*
  $scope.loadValueTypes = function() {
    return utilities.loadResource($scope, 'valueTypes', '/admin/curated/indicatorTypes/valueTypes/json', function() {
      $scope.resetNewIndicatorType();
    });
  }
  $scope.loadValueTypes();
  */
  
  // Show an indicator type's value type
  $scope.showValueType = function(indicatorType) {
    var selected = $filter('filter')($scope.valueTypes, {
      value : indicatorType.valueType
    });
    return (indicatorType.valueType && selected.length) ? selected[0].text : 'Not set';
  };

  // Show an indicator type's unit
  $scope.showUnit = function(indicatorName) {
    var selected = utilities.findSomething($scope.units, "id", indicatorName.unit);
    var result = (indicatorName.unit && selected.length) ? selected[0].name : 'Not set';
    // console.log("Show unit - selected length [" + selected.length + "], indicatorName.name [" + indicatorName.name + "], unit id [" + indicatorName.unit + "], result id [" + selected[0].id + "]");
    return result;
  };

  // Create an indicator type
  // ========================
  $scope.createIndicatorType = function(data) {
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
    if (data && data.unit) {
      angular.extend(params, {
        "unit" : data.unit.id
      });
    }
    if (data && data.valueType) {
      angular.extend(params, {
        "valueType" : data.valueType
      });
    }
    return utilities.createResource({
      validate : $scope.checkCreateForm,
      data : data,
      params : params,
      url : '/admin/curated/indicatorTypes/submitCreate',
      successCallback : function() {
        $scope.resetNewIndicatorType();
        $scope.resetCreateResourceForm();
        $scope.loadIndicatorTypes();
      },
      errorCallback : function() {
        alert("Indicator type creation threw an error. Maybe this indicator type already exists. No indicator type has been created.");
      }
    });
  };

  // Check that the new indicatorType is complete
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
    var unit = data.unit;
    if ('' === unit || null === unit) {
      utilities.setFocus('newResource_unit');
      return "Unit cannot be empty.";
    }
    var valueType = data.valueType;
    if ('' === valueType || null === valueType) {
      utilities.setFocus('newResource_valueType');
      return "Value type cannot be empty.";
    }
    return "OK";
  };

  // Reset the new indicator type
  $scope.resetNewIndicatorType = function() {
    if (!$scope.newResource) {
      $scope.newResource = {};
    }
    $scope.newResource.code = "";
    $scope.newResource.name = "";
    $scope.newResource.unit = $scope.units ? $scope.units[0].name : "";
    $scope.newResource.valueType = $scope.valueTypes ? $scope.valueTypes[0].value : "";
  };

  // Reset the create resource form
  $scope.resetCreateResourceForm = function() {
    $scope.createResourceForm.$setPristine();
  };

  // Update an indicatorType
  // =======================
  $scope.updateIndicatorType = function(data, id) {
    var params = {};
    angular.extend(params, {
      "indicatorTypeId" : id
    });
    angular.extend(params, {
      "newCode" : data.code
    });
    angular.extend(params, {
      "newName" : data.name
    });
    angular.extend(params, {
      "newUnit" : data.unit
    });
    angular.extend(params, {
      "newValueType" : data.valueType
    });
    return utilities.updateResource({
      validate : $scope.checkUpdateForm,
      data : data,
      params : params,
      url : '/admin/curated/indicatorTypes/submitUpdate',
      successCallback : function() {
        $scope.loadIndicatorTypes();
      },
      errorCallback : function() {
        alert("Indicator type update threw an error. No indicator type has been updated.");
        $scope.loadIndicatorTypes();
      }
    });
  };

  // Check that the updated indicatorType is valid
  $scope.checkUpdateForm = function(data) {
    var code = data.code;
    if (!code || null === code || '' === code) {
      return "Code cannot be empty.";
    }
    var name = data.name;
    if (!name || null === name || '' === name) {
      return "Name cannot be empty.";
    }
    var unit = data.unit;
    if (!unit || null === unit || '' === unit) {
      return "Unit cannot be empty.";
    }
    var valueType = data.valueType;
    if (!valueType || null === valueType || '' === valueType) {
      return "Value type cannot be empty.";
    }
    return "OK";
  };

  // Delete an indicator type
  // =====================
  $scope.deleteIndicatorType = function(id) {
    return utilities.deleteResource({
      params : {
        "indicatorTypeId" : id
      },
      url : '/admin/curated/indicatorTypes/submitDelete',
      successCallback : $scope.loadIndicatorTypes,
      errorCallback : function() {
        alert("Indicator type deletion threw an error. Maybe this indicator type is used by some indicator. No indicator type has been deleted.");
      }
    });
  }

  // Get an indicator type by its id
  $scope.getIndicatorTypeById = function(indicatorTypeId) {
    var filteredIndicatorTypes = $filter('filter')($scope.indicatorTypes, {
      id : indicatorTypeId
    });
    var theIndicatorType = filteredIndicatorTypes && 0 < filteredIndicatorTypes.length ? filteredIndicatorTypes[0] : null;
    return theIndicatorType;
  }
});