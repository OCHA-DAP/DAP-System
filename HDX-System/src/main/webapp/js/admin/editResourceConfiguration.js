function getRESTParameter(sep, suffix) {
  var partialString = window.location.href.split(sep)[1];
  var id = partialString.split(suffix)[0];
  return id;
}
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
  // ===========================
  $scope.ercFormVisibility = false;
  $scope.minimumNumberOfColumnsId = 0;
  $scope.minimumNumberOfColumnsKey = "";
  $scope.minimumNumberOfColumns = 0;
  $scope.allowedIndicatorTypeCodesId = 0;
  $scope.allowedIndicatorTypeCodesKey = "";
  $scope.allowedIndicatorTypeCodes = [];
  $scope.allowedIndicatorTypeCodesSelected = [];
  $scope.availableIndicatorTypeCodesSelected = [];
  $scope.processResourceConfiguration = function() {
    if ($scope.editResourceConfiguration && $scope.editResourceConfiguration.generalConfigurations) {
      for (var i = 0; i < $scope.editResourceConfiguration.generalConfigurations.length; i++) {
        var gc = $scope.editResourceConfiguration.generalConfigurations[i];
        console.log(gc.id + ", " + gc.key + ", " + gc.value);
        if (gc.key === "Allowed indicator type codes") {
          $scope.allowedIndicatorTypeCodesId = gc.id;
          $scope.allowedIndicatorTypeCodesKey = gc.key;
          $scope.allowedIndicatorTypeCodes = gc.value.split("&&");
        } else if (gc.key === "Minimum number of columns") {
          $scope.minimumNumberOfColumnsId = gc.id;
          $scope.minimumNumberOfColumnsKey = gc.key;
          $scope.minimumNumberOfColumns = gc.value;
        }
      }
    }
  }

  // FRAGILE
  $scope.move = function(which) {
    switch (which) {
    case 'right':
      // console.log("Adding " + $scope.availableIndicatorTypeCodesSelected);
      while (0 < $scope.availableIndicatorTypeCodesSelected.length) {
        var it = $scope.availableIndicatorTypeCodesSelected.pop();
        // Add to db
        $scope.allowedIndicatorTypeCodes.splice(0, 0, it.code);
      }
      break;

    case 'left':
      // console.log("Removing " + $scope.allowedIndicatorTypeCodesSelected);
      while (0 < $scope.allowedIndicatorTypeCodesSelected.length) {
        var code = $scope.allowedIndicatorTypeCodesSelected.pop();
        // Remove from db 
        var index = $scope.allowedIndicatorTypeCodes.indexOf(code);
        if (index > -1) {
          $scope.allowedIndicatorTypeCodes.splice(index, 1);
        }
      }
      break;

    default:
      break;
    }

    var joined = $scope.allowedIndicatorTypeCodes.join("&&");
    if(utilities.endsWith(joined, "&&")) {
      joined = joined.substring(0, joined.length-2);
    }
    console.log("[" + joined + "]");
    $scope.updateGC(joined, $scope.allowedIndicatorTypeCodesKey, $scope.allowedIndicatorTypeCodesId);
  }

  $scope.filterAvailableITC = function(it) {
    // console.log(it.code);
    return $scope.allowedIndicatorTypeCodes.indexOf(it.code) === -1;
  }
  $scope.loadResourceConfiguration = function() {
    var id = getRESTParameter("/id/", "/");
    return utilities.loadResource($scope, 'editResourceConfiguration', '/admin/misc/configurations/id/' + id + '/json', function() {
      $scope.processResourceConfiguration();
      // $scope.resetNewTranslations();
    });
  }
  $scope.loadResourceConfiguration();

  $scope.updateRC = function(data, id) {
    return utilities.updateResource({
      validate : $scope.checkRCUpdateForm,
      data : data,
      params : {
        "id" : id,
        "name" : data.name
      },
      url : '/admin/misc/configurations/submitUpdate',
      successCallback : function() {
        $scope.loadResourceConfiguration();
      },
      errorCallback : function() {
        alert("Configuration update threw an error. No configuration has been updated.");
        $scope.loadResourceConfiguration();
      }
    });
  };

  $scope.checkRCUpdateForm = function(data) {
    var value = data.name;
    if (!value || null === value || '' === value) {
      return "Name cannot be empty.";
    }
    return "OK";
  };

  // Update a configuration
  $scope.updateGC = function(data, key, id) {
    return utilities.updateResource({
      validate : $scope.checkUpdateGCForm,
      data : data,
      params : {
        "id" : id,
        "key" : key,
        "value" : data
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
  $scope.checkUpdateGCForm = function(data) {
    var value = data;
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

  // Indicator Resource Configuration
  
  // Find available sources for the selected indicator type
  $scope.dataSeriesSourcesAvailable = false;
  $scope.dataSeriesSources = [];
  $scope.dataSeriesIndicatorTypeCode = "";
  $scope.dataSeriesIndicatorTypeSelect = function() {
    if(!$scope.newIndRC || !$scope.newIndRC.indTypeCode) {
      $scope.resetIndicatorNewConfiguration();
    }
    var found = utilities.findSomething($scope.indicatorTypes, "code", $scope.dataSeriesIndicatorTypeCode);
    $scope.newIndRC.indTypeCode = found && 1 === found.length ? found[0] : {};
    $scope.loadSourcesForIndicatorType();
  }

  // Load the sources available for the current selected indicator type.
  $scope.loadSourcesForIndicatorType = function() {
    return $http.get(hdxContextRoot + '/admin/curated/sourcesForIndicatorType/json', {
      params : {
        indicatorTypeCode: $scope.newIndRC.indTypeCode.code
      }
    }).success(function(data, status, headers, config) {
      $scope.dataSeriesSources = data;
      $scope.dataSeriesSourcesAvailable = false;
      if($scope.dataSeriesSources && 0 < $scope.dataSeriesSources.length) {
        $scope.newIndRC.sourceCode = $scope.dataSeriesSources[0];
        $scope.dataSeriesSourcesAvailable = true;
      }
    }).error(function(data, status, headers, config) {
      $scope.dataSeriesSourcesAvailable = false;
    });
  }
  
  
  
  
  // add an Indicator Resource Configuration
  $scope.addIndicatorRC = function(data, rc) {

    var existIC = 0;
    var icID = null;

    if (rc.indicatorConfigurations != null && rc.indicatorConfigurations.length > 0) {
      for (i = 0; i < rc.indicatorConfigurations.length; i++) {
        if (data.key.key == rc.indicatorConfigurations[i].key && data.indTypeCode.id == rc.indicatorConfigurations[i].indTypeId && data.sourceCode.id == rc.indicatorConfigurations[i].srcId) {
          existIC = 1;
          icID = rc.indicatorConfigurations[i].id;
        }
      }
    }
    if (existIC == 1) {
      if (confirm("The Indicator Configuration exists and will be overwritten! Are you sure you want to continue?") == false) {
        return;
      }
    }

    return utilities.createResource({
      validate : $scope.checkIndicatorCreateForm,
      data : data,
      params : {
        "rcID" : rc.id,
        "icID" : icID,
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
  };

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

  $scope.resetIndicatorNewConfiguration = function() {
    if (!$scope.newIndRC) {
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

  // delete an Indicator Resource Configuration
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

  //update an Indicator Resource Configuration
  $scope.updateIC = function(data, ic) {
    return utilities.updateResource({
      validate : $scope.checkUpdateIndicatorForm,
      data : data,
      params : {
        "id" : ic.id,
        "indTypeId" : ic.indTypeId,
        "sourceId" : ic.srcId,
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

  //Check that the updated configuration is valid
  $scope.checkUpdateIndicatorForm = function(data) {
    if (!data) {
      utilities.setFocus('newIndRC_value');
      return "At least some info should be provided.";
    }
    //    var key = data.key;
    //    if (!key || null === key || '' === key) {
    //      utilities.setFocus('newIndRC_key');
    //      return "Key cannot be empty.";
    //    }
    var value = data.value;
    if (!value || null === value || '' === value) {
      utilities.setFocus('newIndRC_value');
      return "Value cannot be empty.";
    }
    /*
    var itc = data.indType;
    if (!itc || null === itc || '' === itc) {
      utilities.setFocus('newIndRC_indTypeCode');
      return "Indicator Type cannot be empty.";
    }
    var src = data.src;
    if (!src || null === src || '' === src) {
      utilities.setFocus('newIndRC_sourceCode');
      return "Source cannot be empty.";
    }
    */
    return "OK";
  };

  $scope.showIndicatorType = function(ic) {
    if (ic.indTypeId) {
      for (i = 0; i < $scope.indicatorTypes.length; i++) {
        if ($scope.indicatorTypes[i].id == ic.indTypeId)
          return $scope.indicatorTypes[i].code;
      }
      return 'Not set';
    } else {
      return ic.indTypeId || 'Not set';
    }
  };

  $scope.showSources = function(ic) {
    if (ic.srcId) {
      for (i = 0; i < $scope.sources.length; i++) {
        if ($scope.sources[i].id == ic.srcId)
          return $scope.sources[i].code;
      }
      return 'Not set';
    } else {
      return ic.srcId || 'Not set';
    }
  };

});

app.controller('RegionDictionariesCtrl', function($scope, utilities) {
  $scope.loadRegionDictionaire = function() {
    var id = getRESTParameter("/id/", "/");
    return utilities.loadResource($scope, 'regionDictionaries', '/admin/dictionaries/regions/' + id + '/json');
  };
  $scope.loadRegionDictionaire();

  $scope.resources = function() {
    $scope.regionDictionaries = appData['regionDictionaries'];
    $scope.entities = appData['entities'];
  };
  $scope.resources();

  $scope.resetNewResource = function() {
    var id = getRESTParameter("/id/", "/");
    $scope.newResource = {};
    $scope.newResource.configId = id;
  };

  // The new dictionary
  $scope.resetNewResource();

  // Create a new dictionary
  $scope.createDictionary = function(data) {
    var params = {};
    if (data && data.entityId) {
      angular.extend(params, {
        "entityId" : data.entityId
      });
    }
    if (data && data.unnormalizedName) {
      angular.extend(params, {
        "unnormalizedName" : data.unnormalizedName
      });
    }

    if (data && data.configId) {
      angular.extend(params, {
        "configId" : data.configId
      });
    }
    return utilities.createResource({
      validate : $scope.checkCreateForm,
      data : data,
      params : params,
      url : '/admin/dictionaries/regions/submitCreate',
      successCallback : function() {
        $scope.resetNewResource();
        $scope.loadRegionDictionaire();
      },
      errorCallback : function() {
        alert("Dictionary creation threw an error. Maybe this dictionary already exists. No dictionary has been created.");
        $scope.loadRegionDictionaire();
      }
    });
  };

  $scope.deleteDictionary = function(id) {
    return utilities.deleteResource({
      params : {
        "id" : id
      },
      url : '/admin/dictionaries/regions/submitDelete',
      successCallback : $scope.loadRegionDictionaire,
      errorCallback : function() {
        alert("Configuration deletion threw an error. Maybe this configuration is used in some translation. No configuration has been deleted.");
      }
    });
  };

  // Check that the new dictionary is complete
  $scope.checkCreateForm = function(data) {
    if (!data) {
      utilities.setFocus('newResource_entityId');
      return "At least some info should be provided.";
    }
    var entityId = data.entityId;
    if (!entityId || null === entityId || '' === entityId) {
      utilities.setFocus('newResource_entityId');
      return "Entity cannot be empty.";
    }
    var unnormalizedName = data.unnormalizedName;
    if (!unnormalizedName || null === unnormalizedName || '' === unnormalizedName) {
      utilities.setFocus('newResource_unnormalizedName');
      return "Unnormalized name cannot be empty.";
    }

    return "OK";
  };

});

app.controller('SourceDictionariesCtrl', function($scope, utilities) {
  $scope.loadSourceDictionaire = function() {
    var id = getRESTParameter("/id/", "/");
    return utilities.loadResource($scope, 'sourceDictionaries', '/admin/dictionaries/sources/' + id + '/json');
  };
  $scope.loadSourceDictionaire();

  $scope.resources = function() {
    $scope.sourceDictionaries = appData['sourceDictionaries'];
    $scope.sources = appData['sources'];
  };
  $scope.resources();

  $scope.resetNewResource = function() {
    var id = getRESTParameter("/id/", "/");
    $scope.newResource = {};
    $scope.newResource.configId = id;
  };

  // The new dictionary
  $scope.resetNewResource();

  // The new dictionary
  $scope.newResource;

  // Create a new dictionary
  $scope.createDictionary = function(data) {
    var params = {};
    if (data && data.sourceId) {
      angular.extend(params, {
        "sourceId" : data.sourceId
      });
    }
    if (data && data.unnormalizedName) {
      angular.extend(params, {
        "unnormalizedName" : data.unnormalizedName
      });
    }

    if (data && data.configId) {
      angular.extend(params, {
        "configId" : data.configId
      });
    }

    return utilities.createResource({
      validate : $scope.checkCreateForm,
      data : data,
      params : params,
      url : '/admin/dictionaries/sources/submitCreate',
      successCallback : function() {
        $scope.resetNewResource();
        $scope.loadSourceDictionaire();
      },
      errorCallback : function() {
        alert("Dictionary creation threw an error. Maybe this dictionary already exists. No dictionary has been created.");
        $scope.loadSourceDictionaire();
      }
    });
  };

  $scope.deleteDictionary = function(id) {
    return utilities.deleteResource({
      params : {
        "id" : id
      },
      url : '/admin/dictionaries/sources/submitDelete',
      successCallback : $scope.loadSourceDictionaire,
      errorCallback : function() {
        alert("Configuration deletion threw an error. Maybe this configuration is used in some translation. No configuration has been deleted.");
      }
    });
  };

  // Check that the new dictionary is complete
  $scope.checkCreateForm = function(data) {
    if (!data) {
      utilities.setFocus('newResource_sourceId');
      return "At least some info should be provided.";
    }
    var sourceId = data.sourceId;
    if (!sourceId || null === sourceId || '' === sourceId) {
      utilities.setFocus('newResource_sourceId');
      return "Source cannot be empty.";
    }
    var unnormalizedName = data.unnormalizedName;
    if (!unnormalizedName || null === unnormalizedName || '' === unnormalizedName) {
      utilities.setFocus('newResource_unnormalizedName');
      return "Unnormalized name cannot be empty.";
    }
    return "OK";
  };

});

app.controller('IndicatorTypeDictionariesCtrl', function($scope, utilities) {
  $scope.loadIndicatorTypeDictionaire = function() {
    var id = getRESTParameter("/id/", "/");
    return utilities.loadResource($scope, 'indicatorTypeDictionaries', '/admin/dictionaries/indicatorTypes/' + id + '/json');
  };
  $scope.loadIndicatorTypeDictionaire();

  $scope.resources = function() {
    $scope.indicatorTypeDictionaries = appData['indicatorTypeDictionaries'];
    $scope.indicatorTypes = appData['indicatorTypes'];
  };
  $scope.resources();

  $scope.resetNewResource = function() {
    var id = getRESTParameter("/id/", "/");
    $scope.newResource = {};
    $scope.newResource.configId = id;
  };

  // The new dictionary
  $scope.resetNewResource();

  // The new dictionary
  $scope.newResource;

  // Create a new dictionary
  $scope.createDictionary = function(data) {
    var params = {};
    if (data && data.indicatorTypeId) {
      angular.extend(params, {
        "indicatorTypeId" : data.indicatorTypeId
      });
    }
    if (data && data.unnormalizedName) {
      angular.extend(params, {
        "unnormalizedName" : data.unnormalizedName
      });
    }

    if (data && data.configId) {
      angular.extend(params, {
        "configId" : data.configId
      });
    }

    return utilities.createResource({
      validate : $scope.checkCreateForm,
      data : data,
      params : params,
      url : '/admin/dictionaries/indicatorTypes/submitCreate',
      successCallback : function() {
        $scope.resetNewResource();
        $scope.loadIndicatorTypeDictionaire();
      },
      errorCallback : function() {
        alert("Dictionary creation threw an error. Maybe this dictionary already exists. No dictionary has been created.");
        $scope.loadIndicatorTypeDictionaire();
      }
    });
  };

  $scope.deleteDictionary = function(id) {
    return utilities.deleteResource({
      params : {
        "id" : id
      },
      url : '/admin/dictionaries/indicatorTypes/submitDelete',
      successCallback : $scope.loadIndicatorTypeDictionaire,
      errorCallback : function() {
        alert("Configuration deletion threw an error. Maybe this configuration is used in some translation. No configuration has been deleted.");
      }
    });
  };

  // Check that the new dictionary is complete
  $scope.checkCreateForm = function(data) {
    if (!data) {
      utilities.setFocus('newResource_indicatorTypeId');
      return "At least some info should be provided.";
    }
    var indicatorTypeId = data.indicatorTypeId;
    if (!indicatorTypeId || null === indicatorTypeId || '' === indicatorTypeId) {
      utilities.setFocus('newResource_indicatorTypeId');
      return "Indicator type cannot be empty.";
    }
    var unnormalizedName = data.unnormalizedName;
    if (!unnormalizedName || null === unnormalizedName || '' === unnormalizedName) {
      utilities.setFocus('newResource_unnormalizedName');
      return "Unnormalized name cannot be empty.";
    }

    return "OK";
  };

});