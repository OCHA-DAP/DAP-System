app.controller('IndicatorsCtrl', function($scope, $filter, $http, utilities) {

  // /////////////
  // Date pickers
  // /////////////
  $scope.datePickerOptions = {
    format : "dd.mm.yyyy",
    weekStart : 1,
    autoclose : true
  };

  $('.datepicker').datepicker($scope.datePickerOptions).on('changeDate', function(ev) {
    $(this).datepicker('hide');
  });

  $scope.showDate = function(jsonDate) {
    var stripped = jsonDate.substring(1, jsonDate.length - 1);
    console.log("Date from json [" + stripped + "]");
    var date = new Date(Date.parse(stripped));
    console.log("Date parsed [" + date + "]");
    var day = 10 > date.getDate() ? "0" + date.getDate() : "" + date.getDate();
    var month = 10 > (1 + date.getMonth()) ? "0" + (1 + date.getMonth()) : "" + (1 + date.getMonth());
    var year = date.getFullYear();
    return day + "." + month + "." + year;
  }

  // ////////////////////
  // Sources management
  // ////////////////////

  // Load sources
  // ============
  $scope.loadSources = function() {
    return utilities.loadResource($scope, 'sources', '/admin/curated/sources/json', function() {
      // $scope.resetNewTranslations();
    });
  }
  $scope.loadSources();

  // Show a source for a given indicator
  $scope.showSource = function(indicator) {
    if (indicator.source) {
      var selected = $filter('filter')($scope.sources, {
        id : indicator.source.id
      });
      return selected.length ? selected[0].name : 'Not set';
    } else {
      return indicator.source || 'Not set';
    }
  };

  // ///////////////////////
  // Entity types management
  // ///////////////////////

  $scope.loadEntityTypes = function() {
    return utilities.loadResource($scope, 'entityTypes', '/admin/curated/entityTypes/json', function() {
      // $scope.resetNewTranslations();
    });
  }
  $scope.loadEntityTypes();

  // ///////////////////
  // Entities management
  // ///////////////////

  $scope.filteredEntities = [];

  // Load entities
  $scope.loadEntities = function() {
    return utilities.loadResource($scope, 'entities', '/admin/curated/entities/json', function() {
    });
  };
  $scope.loadEntities();

  $scope.refreshEntities = function() {
    if ($scope.newResource && $scope.newResource.entityType) {
      $scope.filteredEntities = $scope.entitiesByEntityType($scope.newResource.entityType);
      $scope.newResource.entity = null;
    }
  };

  $scope.entitiesByEntityType = function(entityType) {
    var theFilteredEntities = $filter('filter')($scope.entities, {
      type : entityType.id
    });
    return theFilteredEntities;
  }

  $scope.showEntities = function() {
    // alert("show !");
    var result = $scope.newResource && $scope.newResource.entityType && $scope.filteredEntities && 0 < $scope.filteredEntities.length;
    return result;
  };

  // //////////////////////////
  // Indicator types management
  // //////////////////////////

  $scope.indicatorTypes = [];

  // Load indicatorTypes
  $scope.loadIndicatorTypes = function() {
    return $http.get(dapContextRoot + '/admin/curated/indicatorTypes/json').success(function(data) {
      $scope.indicatorTypes = data;
    });
  };

  if (!$scope.indicatorTypes.length) {
    $scope.loadIndicatorTypes();
  }

  // Show a type for a given indicator
  $scope.showIndicatorType = function(indicator) {
    if (indicator.indicatorType) {
      var selected = $filter('filter')($scope.indicatorTypes, {
        id : indicator.indicatorType.id
      });
      return selected.length ? selected[0].name : 'Not set';
    } else {
      return indicator.indicatorType || 'Not set';
    }
  };

  // ////////////////////
  // Indicators management
  // ////////////////////

  // Sort management
  $scope.predicate = 'type';

  // Load indicators
  // ==================
  $scope.loadIndicators = function() {
    return utilities.loadResource($scope, 'indicators', '/admin/curated/indicators/json', function() {
      $scope.processIndicators();
    });
  };
  $scope.loadIndicators();

  // Process the received indicators
  $scope.processIndicators = function() {
    var data = $scope.indicators;
    if (data) {
      for (var i = 0; i < data.length; i++) {
        var indicator = data[i];
        var parsedStartDate = data[i].startDate ? $scope.showDate(data[i].startDate) : "";
        var parsedEndDate = data[i].endDate ? $scope.showDate(data[i].endDate) : "";
        var startsWithQuote = data[i].periodicity ? (data[i].periodicity.lastIndexOf('"', 0) === 0) : false;
        var endsWithQuote = data[i].periodicity ? (data[i].periodicity.lastIndexOf('"', 0) === data[i].periodicity.length - 1) : false;
        var processedPeriodicity = data[i].periodicity ? ((startsWithQuote && endsWithQuote) ? data[i].periodicity.substring(1, data[i].periodicity.length - 1) : "") : "";
        angular.extend(data[i], {
          "parsedStartDate" : parsedStartDate
        });
        angular.extend(data[i], {
          "parsedEndDate" : parsedEndDate
        });
        angular.extend(data[i], {
          "processedPeriodicity" : processedPeriodicity
        });
      }
    }
  };

  // Load periodicities
  // ==================
  $scope.loadPeriodicities = function() {
    return utilities.loadResource($scope, 'periodicities', '/admin/curated/indicators/periodicities/json', function() {
      $scope.resetNewIndicator();
    });
  }
  $scope.loadPeriodicities();

  // Load value types
  // ================
  $scope.loadValueTypes = function() {
    return utilities.loadResource($scope, 'valueTypes', '/admin/curated/indicatorTypes/valueTypes/json', function() {
      $scope.resetNewIndicator();
    });
  }
  $scope.loadValueTypes();

  // Show an indicator type's value type
  $scope.showValueType = function(indicator) {
    var selected = $filter('filter')($scope.valueTypes, {
      value : indicator.valueType
    });
    return (indicator.valueType && selected.length) ? selected[0].text : 'Not set';
  };

  // Show an indicator's periodicity
  $scope.showPeriodicity = function(indicator) {
    var selected = $filter('filter')($scope.periodicities, {
      value : indicator.periodicity.substring(1, indicator.periodicity.length - 1)
    });
    return (indicator.periodicity && selected.length) ? selected[0].text : 'Not set';
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
        "unit" : data.unit
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

  // - add it
  $scope.addIndicator = function(data) {
    var valid = $scope.checkForm(data);
    if ("OK" === valid) {
      // alert("Add indicator : " + data);
      return $http.post(dapContextRoot + '/admin/curated/indicators/submitCreate', "indicatorTypeCode=" + data.type.code + "&code=" + data.code + "&name=" + data.name, {
        headers : {
          'Content-Type' : 'application/x-www-form-urlencoded'
        }
      }).success(function(data, status, headers, config) {
        // this callback will be called asynchronously
        // when the response is available
        // alert("Indicator added !");
        $scope.resetNewIndicator();
        $scope.resetAddIndicatorForm();
        $scope.loadIndicators();
      }).error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        alert("Indicator creation threw an error. Maybe this indicator already exists. No indicator has been created.");
      });
    } else {
      alert("Form not valid ! \r\n" + valid);
    }
  };

  // - check that the new indicator is complete
  $scope.checkForm = function(data) {
    var code = data.code;
    if ('' === code || null === code) {
      return "Code cannot be empty.";
    }
    var name = data.name;
    if ('' === name || null === name) {
      return "Name cannot be empty.";
    }
    var type = data.type;
    if (null === type) {
      return "Type cannot be empty.";
    }
    return "OK";
  };

  // Save (update) an indicator
  $scope.saveIndicator = function(data, id) {
    var valid = $scope.checkUpdateForm(data);
    if ("OK" === valid) {

      return $http.post(dapContextRoot + '/admin/curated/indicators/submitUpdate', "indicatorId=" + id + "&newName=" + data.name, {
        headers : {
          'Content-Type' : 'application/x-www-form-urlencoded'
        }
      }).success(function(data, status, headers, config) {
        // this callback will be called asynchronously
        // when the response is available
        $scope.loadIndicators();
      }).error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        alert("Indicator update threw an error. No indicator has been updated.");
        $scope.loadIndicators();
      });
    } else {
      alert("Form not valid ! \r\n" + valid);
      return "";
    }
  };

  // - check that the updated indicator is valid
  $scope.checkUpdateForm = function(data) {
    var name = data.name;
    if ('' === name || null === name) {
      return "Name cannot be empty.";
    }
    return "OK";
  };

  // Remove an indicator
  $scope.removeIndicator = function(id) {
    if (!confirm("Do you really want to delete this indicator ?")) {
      return;
    }
    $http.post(dapContextRoot + '/admin/curated/indicators/submitDelete', "indicatorId=" + id, {
      headers : {
        'Content-Type' : 'application/x-www-form-urlencoded'
      }
    }).success(function(data, status, headers, config) {
      // this callback will be called asynchronously
      // when the response is available
      $scope.loadIndicators();
    }).error(function(data, status, headers, config) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
      alert("Indicator deletion threw an error. Maybe this indicator is used by some indicator. No indicator has been deleted.");
    });
  };

  // add indicator
  // - the new indicator
  $scope.newResource;

  // - reset it
  $scope.resetNewIndicator = function() {
    if (!$scope.newResource) {
      $scope.newResource = {};
    }
    $scope.newResource.valueType = $scope.valueTypes[0];
    /* TODO
    $scope.newResource.code = "";
    $scope.newResource.name = "";
    */

  };

  // - reset its form
  $scope.resetAddIndicatorForm = function() {
    $scope.addIndicatorForm.$setPristine();
  };

  // Get an indicator by its id
  $scope.getIndicatorById = function(indicatorId) {
    var filteredIndicators = $filter('filter')($scope.indicators, {
      id : indicatorId
    });
    var theIndicator = filteredIndicators && 0 < filteredIndicators.length ? filteredIndicators[0] : null;
    return theIndicator;
  }
});