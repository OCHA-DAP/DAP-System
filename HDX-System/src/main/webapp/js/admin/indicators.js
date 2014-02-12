app.controller('IndicatorsCtrl', function($scope, $filter, $http, utilities) {

  // /////////
  // Resources
  // /////////
  $scope.resources = function() {
    $scope.sources = appData['sources'];
    $scope.entityTypes = appData['entityTypes'];
    $scope.entities = appData['entities'];
    $scope.indicatorTypes = appData['indicatorTypes'];
    $scope.periodicities = appData['periodicities'];
    $scope.valueTypes = appData['valueTypes'];
  }
  $scope.resources();

  // The new indicator
  $scope.newResource;

  $scope.filteredEntities = [];
  $scope.entitiesByEntityType = function(entityType) {
    var theFilteredEntities = $filter('filter')($scope.entities, {
      type : entityType.id
    });
    return theFilteredEntities;
  }

  $scope.refreshEntities = function() {
    if ($scope.newResource && $scope.newResource.entityType) {
      $scope.filteredEntities = $scope.entitiesByEntityType($scope.newResource.entityType);
      $scope.newResource.entity = $scope.filteredEntities ? $scope.filteredEntities[0] : null;
    }
  };

  // Reset the new indicator
  $scope.resetNewIndicator = function(fromWhere) {
    console.log("Reset new indicator after [" + fromWhere + "]");
    if (!$scope.newResource) {
      $scope.newResource = {};
    }
    $scope.newResource.source = $scope.sources ? $scope.sources[0] : null;
    $scope.newResource.entityType = $scope.entityTypes ? $scope.entityTypes[0] : null;
    $scope.refreshEntities();
    $scope.newResource.entity = $scope.filteredEntities ? $scope.filteredEntities[0] : null;
    $scope.newResource.indicatorType = $scope.indicatorTypes ? $scope.indicatorTypes[0] : null;
    $scope.newResource.startDate = null;
    $scope.newResource.endDate = null;
    $scope.newResource.periodicity = $scope.periodicities ? $scope.periodicities[0].value : null;
    $scope.newResource.valueType = $scope.valueTypes ? $scope.valueTypes[1].value : null;
    $scope.newResource.value = null;
    $scope.newResource.initialValue = null;

  };

  // Reset the create resource form
  $scope.resetCreateResourceForm = function() {
    $scope.createResourceForm.$setPristine();
  };

  $scope.resetNewIndicator("resources");

  // /////////////
  // Date pickers
  // /////////////
  $scope.datePickerOptions = {
    /* format : "dd.mm.yyyy", */
    format : "yyyy-mm-dd",
    weekStart : 1,
    autoclose : true
  };

  $('.datepicker').datepicker($scope.datePickerOptions).on('changeDate', function(ev) {
    var $this = $(this);
    // TODO Improve (f.i. with a custom directive)
    if ($this.context.id === "newResource_startDate") {
      $scope.newResource.startDate = $this.val();
    } else if ($this.context.id === "newResource_endDate") {
      $scope.newResource.endDate = $this.val();
    }
    $this.datepicker('hide');
  });

  $scope.showDate = function(jsonDate) {
    var stripped = jsonDate.substring(1, jsonDate.length - 1);
    // console.log("Date from json [" + stripped + "]");
    var date = new Date(Date.parse(stripped));
    // console.log("Date parsed [" + date + "]");
    var day = 10 > date.getDate() ? "0" + date.getDate() : "" + date.getDate();
    var month = 10 > (1 + date.getMonth()) ? "0" + (1 + date.getMonth()) : "" + (1 + date.getMonth());
    var year = date.getFullYear();
    // return day + "." + month + "." + year;
    return year + "-" + month + "-" + day;
  }

  // ////////////////////
  // Sources management
  // ////////////////////

  // Load sources
  // ============
  /*
  $scope.loadSources = function() {
    return utilities.loadResource($scope, 'sources', '/admin/curated/sources/json', function() {
      $scope.resetNewIndicator("loadSources");
    });
  }
  $scope.loadSources();
  */

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
  /*
  $scope.loadEntityTypes = function() {
    return utilities.loadResource($scope, 'entityTypes', '/admin/curated/entityTypes/json', function() {
      $scope.resetNewIndicator("loadEntityTypes");
    });
  }
  $scope.loadEntityTypes();
  */

  // ///////////////////
  // Entities management
  // ///////////////////
  /*
  // Load entities
  $scope.loadEntities = function() {
    return utilities.loadResource($scope, 'entities', '/admin/curated/entities/json', function() {
      $scope.resetNewIndicator("loadEntities");
    });
  };
  $scope.loadEntities();
  */

  $scope.showEntities = function() {
    // alert("show !");
    var result = $scope.newResource && $scope.newResource.entityType && $scope.filteredEntities && 0 < $scope.filteredEntities.length;
    return result;
  };

  // //////////////////////////
  // Indicator types management
  // //////////////////////////
  /*
  $scope.loadIndicatorTypes = function() {
    return utilities.loadResource($scope, 'indicatorTypes', '/admin/curated/indicatorTypes/json', function() {
      $scope.resetNewIndicator("loadIndicatorTypes");
    });
  };
  $scope.loadIndicatorTypes();
  */

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

  // ////////////////////////
  // Periodicities management
  // ////////////////////////

  // Load periodicities
  // ==================
  /*
  $scope.loadPeriodicities = function() {
    return utilities.loadResource($scope, 'periodicities', '/admin/curated/indicators/periodicities/json', function() {
      $scope.resetNewIndicator("loadPeriodicities");
    });
  }
  $scope.loadPeriodicities();
  */

  // //////////////////////
  // Value types management
  // //////////////////////
  // Load value types
  // ================
  /*
  $scope.loadValueTypes = function() {
    return utilities.loadResource($scope, 'valueTypes', '/admin/curated/indicatorTypes/valueTypes/json', function() {
      $scope.resetNewIndicator("loadValueTypes");
    });
  }
  $scope.loadValueTypes();
  */

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
  // ===============================
  $scope.processIndicators = function() {
    var data = $scope.indicators;
    if (data) {
      for (var i = 0; i < data.length; i++) {
        var indicator = data[i];
        
        var parsedStartDate = data[i].startDate ? $scope.showDate(data[i].startDate) : "";
        var parsedEndDate = data[i].endDate ? $scope.showDate(data[i].endDate) : "";

        var periodicityStartsWithQuote = data[i].periodicity ? (data[i].periodicity.lastIndexOf('"', 0) === 0) : false;
        var periodicityEndsWithQuote = data[i].periodicity ? (data[i].periodicity.lastIndexOf('"', data[i].periodicity.length - 1) === data[i].periodicity.length - 1) : false;
        var processedPeriodicity = data[i].periodicity ? ((periodicityStartsWithQuote && periodicityEndsWithQuote) ? data[i].periodicity.substring(1, data[i].periodicity.length - 1) : "") : "";
        
        var valueStartsWithQuote = data[i].value ? (data[i].value.lastIndexOf('"', 0) === 0) : false;
        var valueEndsWithQuote = data[i].value ? (data[i].value.lastIndexOf('"', data[i].value.length - 1) === data[i].value.length - 1) : false;
        var processedValue = data[i].value ? ((valueStartsWithQuote && valueEndsWithQuote) ? data[i].value.substring(1, data[i].value.length - 1) : "") : "";
        
        angular.extend(data[i], {
          "parsedStartDate" : parsedStartDate
        });
        angular.extend(data[i], {
          "parsedEndDate" : parsedEndDate
        });
        angular.extend(data[i], {
          "processedPeriodicity" : processedPeriodicity
        });
        angular.extend(data[i], {
          "processedValue" : processedValue
        });
        angular.extend(data[i], {
          "processedImportFromCkan" : "TODO"
        });
      }
    }
  };

  // Create an indicator
  // ===================
  $scope.createIndicator = function(data) {
    var params = {};
    if (data && data.source) {
      angular.extend(params, {
        "sourceCode" : data.source.code
      });
    }
    if (data && data.entity) {
      angular.extend(params, {
        "entityId" : data.entity.id
      });
    }
    if (data && data.indicatorType) {
      angular.extend(params, {
        "indicatorTypeCode" : data.indicatorType.code
      });
    }
    if (data && data.startDate) {
      angular.extend(params, {
        "start" : data.startDate
      });
    }
    if (data && data.endDate) {
      angular.extend(params, {
        "end" : data.endDate
      });
    }
    if (data && data.periodicity) {
      angular.extend(params, {
        "periodicity" : data.periodicity
      });
    }
    if (data && data.valueType) {
      angular.extend(params, {
        "valueType" : data.valueType
      });
    }
    if (data && data.value) {
      angular.extend(params, {
        "value" : data.value
      });
    }
    if (data && data.initialValue) {
      angular.extend(params, {
        "initialValue" : data.initialValue
      });
    }
    return utilities.createResource({
      validate : $scope.checkCreateForm,
      data : data,
      params : params,
      url : '/admin/curated/indicators/submitCreate',
      successCallback : function() {
        $scope.resetNewIndicator("createResource");
        $scope.resetCreateResourceForm();
        $scope.loadIndicators();
      },
      errorCallback : function() {
        alert("Indicator creation threw an error. Maybe this indicator already exists. No indicator has been created.");
      }
    });
  };

  // Check that the new indicatorType is complete
  $scope.checkCreateForm = function(data) {
    if (!data) {
      utilities.setFocus('newResource_source');
      return "At least some info should be provided.";
    }
    var source = data.source;
    if (!source || null === source || '' === source) {
      utilities.setFocus('newResource_source');
      return "Source cannot be empty.";
    }
    var entity = data.entity;
    if (!entity || null === entity || '' === entity) {
      utilities.setFocus('newResource_entity');
      return "Entity cannot be empty.";
    }
    var startDate = data.startDate;
    if (!startDate || null === startDate || '' === startDate) {
      utilities.setFocus('newResource_startDate');
      return "Start date cannot be empty.";
    }
    var periodicity = data.periodicity;
    if (!periodicity || null === periodicity || '' === periodicity) {
      utilities.setFocus('newResource_periodicity');
      return "Periodicity cannot be empty.";
    }
    var valueType = data.valueType;
    if (!valueType || null === valueType || '' === valueType) {
      utilities.setFocus('newResource_valueType');
      return "Value type cannot be empty.";
    }
    var initialValue = data.initialValue;
    if (!initialValue || null === initialValue || '' === initialValue) {
      utilities.setFocus('newResource_initialValue');
      return "Initial value cannot be empty.";
    }
    return "OK";
  };

  // Delete an indicator
  // ===================
  $scope.deleteIndicator = function(id) {
    return utilities.deleteResource({
      params : {
        "indicatorId" : id
      },
      url : '/admin/curated/indicators/submitDelete',
      successCallback : $scope.loadIndicators,
      errorCallback : function() {
        alert("Indicator deletion threw an error. No indicator has been deleted.");
      }
    });
  }

  // Get an indicator by its id
  $scope.getIndicatorById = function(indicatorId) {
    var filteredIndicators = $filter('filter')($scope.indicators, {
      id : indicatorId
    });
    var theIndicator = filteredIndicators && 0 < filteredIndicators.length ? filteredIndicators[0] : null;
    return theIndicator;
  }
});