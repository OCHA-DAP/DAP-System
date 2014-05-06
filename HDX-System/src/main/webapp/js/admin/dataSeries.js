app.controller('DataSeriesCtrl', function($scope, $filter, $http, $timeout, utilities) {

	/*
  // Test
  $scope.$watch('source', function(newValue, oldValue) {
    // console.log("Source changed : old = " + oldValue + ", new = " + newValue);
    var oldCode = "";
    if (oldValue && oldValue.code) {
      oldCode = oldValue.code;
    }
    var newCode = "";
    if (newValue && newValue.code) {
      newCode = newValue.code;
    }
    console.log("Source changed : old = " + oldCode + ", new = " + newCode);
    // $scope.source = newValue;
  });

  $scope.$watch('sourceForDataSeries', function(newValue, oldValue) {
    // console.log("Source for data series changed : old = " + oldValue + ", new = " + newValue);
    var oldCode = "";
    if (oldValue && oldValue.code) {
      oldCode = oldValue.code;
    }
    var newCode = "";
    if (newValue && newValue.code) {
      newCode = newValue.code;
    }
    console.log("Source for data series changed : old = " + oldCode + ", new = " + newCode);
  });
	*/
	
  // /////////
  // Resources
  // /////////
  $scope.resources = function() {
    $scope.indicatorTypes = appData['indicatorTypes'];
    $scope.sources = appData['sources'];
    $scope.languages = appData['languages'];
    $scope.periodicities = appData['periodicities'];
    $scope.dataValidators = appData['dataValidators'];
  }
  $scope.resources();

  $scope.sourceUnavailable = true;

  $scope.resetView = function() {
    $scope.resetMetadata();
    $scope.resetDataValidators();
    $scope.resetValidationNotes();
  }

  // //////////////////////
  // Data Series management
  // //////////////////////

  // Create a new data series management
  $scope.sourceForDataSeries = {};

  $scope.createDataSeries = function() {
    $scope.updateMetadata("CREATE_DATASERIES", "default");
  }

  $scope.editDataSeries = function() {
    // $scope.source = $scope.sourceForDataSeries;
    // $scope.sourceForDataSeries = null;
    $('#dataSeriesCreatedModal').modal('hide');
    $scope.showMetadata("DATASET_SUMMARY");
  }

  $scope.filterDataSeriesSources = function(v) {
    var found = false;
    if (v && $scope.sources && $scope.sourcesForIndicatorType) {
      for (var i = 0; i < $scope.sourcesForIndicatorType.length; i++) {
        var dv = $scope.sourcesForIndicatorType[i];
        if (dv.code === v.code) {
          found = true;
          break;
        }
      }
    }
    return !found;
  }

  $scope.metadataAvailable = false;
  $scope.dataValidatorsAvailable = false;
  $scope.validationNotesAvailable = false;

  // Reset the metadata part of the UI
  $scope.resetMetadata = function() {
    $scope.metadata = {};
    $scope.metadata.datasetSummary = "";
    $scope.metadata.datasetSummaryTranslations = {};
    $scope.metadata.methodology = "";
    $scope.metadata.methodologyTranslations = {};
    $scope.metadata.moreInfo = "";
    $scope.metadata.moreInfoTranslations = {};
    $scope.metadata.termsOfUse = "";
    $scope.metadata.termsOfUseTranslations = {};
    $scope.validationNotes = "";
    $scope.metadata.dataValidators = [];

    // Cancel the forms
    if ($scope.datasetSummaryForm) {
      $scope.datasetSummaryForm.$cancel();
      $scope.datasetSummaryForm.$visible = false;
    }
    if ($scope.methodologyForm) {
      $scope.methodologyForm.$cancel();
      $scope.methodologyForm.$visible = false;
    }
    if ($scope.moreInfoForm) {
      $scope.moreInfoForm.$cancel();
      $scope.moreInfoForm.$visible = false;
    }
    if ($scope.termsOfUseForm) {
      $scope.termsOfUseForm.$cancel();
      $scope.termsOfUseForm.$visible = false;
    }
    if ($scope.validationNotesForm) {
      $scope.validationNotesForm.$cancel();
      $scope.validationNotesForm.$visible = false;
    }
    if ($scope.dataValidatorsForm) {
      /*
      $scope.dataValidatorsForm.$cancel();
      $scope.dataValidatorsForm.$visible = false;
      */
    }

    // Empty the text areas
    $("#datasetSummary").empty();
    $("#methodology").empty();
    $("#moreInfo").empty();
    $("#termsOfUse").empty();
    $("#validationNotes").empty();

    // Hide the tabs
    $scope.metadataAvailable = false;
  }

  // If we have an indicator type and a source,
  // we can show the corresponding metadata.
  // If !which, we show the first tab.
  $scope.showMetadata = function(which) {
    return $http.get(hdxContextRoot + '/admin/curated/metadataForIndicatorTypeAndSource/json', {
      params : {
        indicatorTypeCode : $scope.indicatorType.code,
        sourceCode : $scope.source.code
      }
    }).success(function(data, status, headers, config) {
      $scope.resetView();
      if (data) {
        for (var i = 0; i < data.length; i++) {
          var metadata_ = data[i];
          if ("DATASET_SUMMARY" === metadata_.entryKey) {
            $scope.metadata.datasetSummary = metadata_.entryValue;
            $scope.processTranslations(metadata_.entryKey, metadata_.translations);
          } else if ("METHODOLOGY" === metadata_.entryKey) {
            $scope.metadata.methodology = metadata_.entryValue;
            $scope.processTranslations(metadata_.entryKey, metadata_.translations);
          } else if ("MORE_INFO" === metadata_.entryKey) {
            $scope.metadata.moreInfo = metadata_.entryValue;
            $scope.processTranslations(metadata_.entryKey, metadata_.translations);
          } else if ("TERMS_OF_USE" === metadata_.entryKey) {
            $scope.metadata.termsOfUse = metadata_.entryValue;
            $scope.processTranslations(metadata_.entryKey, metadata_.translations);
          } else if ("VALIDATOR" === metadata_.entryType) { // Special handling for data validators
            $scope.metadata.dataValidators.push({
              "id" : metadata_.id,
              "name" : metadata_.entryKey,
              "value" : metadata_.entryValue,
            });
          } else if ("VALIDATION_NOTES" === metadata_.entryKey) {
            $scope.validationNotes = metadata_.entryValue;
          }
        }
      }
      $scope.metadataAvailable = true;
      if (which) {
        if ("CURATED_DATA_VALIDATORS" === which) {
          $scope.metadataAvailable = false;
          $scope.validationNotesAvailable = false;
          $scope.dataValidatorsAvailable = true;
        } else if ("VALIDATION_NOTES" === which) {
          $scope.metadataAvailable = false;
          $scope.dataValidatorsAvailable = false;
          $scope.validationNotesAvailable = true;
        } else {
          $('#metadataTabs a[href="#' + which + '"]').tab('show')
        }
      } else {
        $('#metadataTabs a:first').tab('show');
      }
    }).error(function(data, status, headers, config) {
      $scope.resetView();
    });
  }

  $scope.processTranslations = function(which, t) {
    var translations = null;
    switch (which) {
    case 'DATASET_SUMMARY':
      if (!$scope.metadata.datasetSummaryTranslations) {
        $scope.metadata.datasetSummaryTranslations = {};
      }
      translations = $scope.metadata.datasetSummaryTranslations;
      break;
    case 'METHODOLOGY':
      if (!$scope.metadata.methodologyTranslations) {
        $scope.metadata.methodologyTranslations = {};
      }
      translations = $scope.metadata.methodologyTranslations;
      break;
    case 'MORE_INFO':
      if (!$scope.metadata.moreInfoTranslations) {
        $scope.metadata.moreInfoTranslations = {};
      }
      translations = $scope.metadata.moreInfoTranslations;
      break;
    case 'TERMS_OF_USE':
      if (!$scope.metadata.termsOfUseTranslations) {
        $scope.metadata.termsOfUseTranslations = {};
      }
      translations = $scope.metadata.termsOfUseTranslations;
      break;
    default:
      break;
    }

    for (var i = 0; i < t.length; i++) {
      var translation = t[i];
      translations[translation.code] = translation.value;
    }
  }

  // This is just incredible
  $scope.filterLanguage = function(language) {
    return function(copylanguage) {
      return language.code != copylanguage.code;
    }
  }

  // Copy the metadata from one text area to another
  // Doesn't work at submit, so don't use it
  $scope.copyMetadata = function(which, from, to) {
    console.log("Copy metadata for [" + which + "] from [" + from + "] to [" + to + "]");
    switch (which) {
    case 'DATASET_SUMMARY':
      if ($scope.metadata.datasetSummaryTranslations) {
        var fromTranslation = "default" === from ? $scope.metadata.datasetSummary : $scope.metadata.datasetSummaryTranslations[from];
        var theField = $('#datasetSummary_' + to);
        theField.val(fromTranslation);
        $scope.metadata.datasetSummaryTranslations[to] = fromTranslation;
        $scope.applyChange(fromTranslation, which, to);
      }
      break;
    case 'METHODOLOGY':
      if ($scope.metadata.methodologyTranslations) {
        var fromTranslation = "default" === from ? $scope.metadata.methodology : $scope.metadata.methodologyTranslations[from];
        var theField = $('#methodology_' + to);
        theField.val(fromTranslation);
        $scope.metadata.methodologyTranslations[to] = fromTranslation;
        $scope.applyChange(fromTranslation, which, to);
      }
      break;
    case 'MORE_INFO':
      if ($scope.metadata.moreInfoTranslations) {
        var fromTranslation = "default" === from ? $scope.metadata.moreInfo : $scope.metadata.moreInfoTranslations[from];
        var theField = $('#moreInfo_' + to);
        theField.val(fromTranslation);
        $scope.metadata.moreInfoTranslations[to] = fromTranslation;
        $scope.applyChange(fromTranslation, which, to);
      }
      break;
    case 'TERMS_OF_USE':
      if ($scope.metadata.termsOfUseTranslations) {
        var fromTranslation = "default" === from ? $scope.metadata.termsOfUse : $scope.metadata.termsOfUseTranslations[from];
        var theField = $('#termsOfUse_' + to);
        theField.val(fromTranslation);
        $scope.metadata.termsOfUseTranslations[to] = fromTranslation;
        $scope.applyChange(fromTranslation, which, to);
      }
      break;
    default:
      break;
    }
  }

  // Trying to enforce values in xeditable fields
  // when copying value from one text area to another
  // but to no avail when submitting...
  // Used in other cases though.
  $scope.applyChange = function(data, which, languageCode) {
    // console.log("Apply change for [" + which + "], language [" + languageCode + "], data [" + data + "]");
    switch (which) {
    case 'DATASET_SUMMARY':
      if ('default' === languageCode) {
        $scope.metadata.datasetSummary = data;
      } else {
        $scope.metadata.datasetSummaryTranslations[languageCode] = data;
      }
      break;
    case 'METHODOLOGY':
      if ('default' === languageCode) {
        $scope.metadata.methodology = data;
      } else {
        $scope.metadata.methodologyTranslations[languageCode] = data;
      }
      break;
    case 'MORE_INFO':
      if ('default' === languageCode) {
        $scope.metadata.moreInfo = data;
      } else {
        $scope.metadata.moreInfoTranslations[languageCode] = data;
      }
      break;
    case 'TERMS_OF_USE':
      if ('default' === languageCode) {
        $scope.metadata.termsOfUse = data;
      } else {
        $scope.metadata.termsOfUseTranslations[languageCode] = data;
      }
      break;
    case 'VALIDATION_NOTES':
      $scope.validationNotes = data;
      break;
    default:
      break;
    }
  }

  // Update the metadata
  $scope.updateMetadata = function(which, languageCode) {
    var data = "";
    var which_ = which;
    var sourceCode_ = $scope.source.code;

    switch (which) {
    case 'DATASET_SUMMARY':
      data = 'default' === languageCode ? $scope.metadata.datasetSummary : $scope.metadata.datasetSummaryTranslations[languageCode];
      break;
    case 'METHODOLOGY':
      data = 'default' === languageCode ? $scope.metadata.methodology : $scope.metadata.methodologyTranslations[languageCode];
      break;
    case 'MORE_INFO':
      data = 'default' === languageCode ? $scope.metadata.moreInfo : $scope.metadata.moreInfoTranslations[languageCode];
      break;
    case 'TERMS_OF_USE':
      data = 'default' === languageCode ? $scope.metadata.termsOfUse : $scope.metadata.termsOfUseTranslations[languageCode];
      break;
    case 'CURATED_DATA_VALIDATORS':
      which_ = $scope.updatedDataValidatorName;
      data = $scope.updatedDataValidatorValue;
      break;
    case 'VALIDATION_NOTES': // no i18n, so will be stored as Text.defaultValue in DB
      data = $scope.validationNotes;
      break;
    case 'CREATE_DATASERIES':
      which_ = "DATASET_SUMMARY";
      sourceCode_ = $scope.sourceForDataSeries.code;
      data = "";
      break;
    default:
      break;
    }

    // console.log("Updating metadata for [" + which + "], language [" + languageCode + "], data [" + data + "]");

    var options = {};
    options.params = {
      which : which_,
      data : data,
      languageCode : languageCode,
      indicatorTypeCode : $scope.indicatorType.code,
      sourceCode : sourceCode_
    };
    options.url = "/admin/curated/metadataForIndicatorTypeAndSource/submitUpdate";
    options.successCallback = function() {
      if ("CREATE_DATASERIES" === which) {
        $scope.loadSourcesForIndicatorType($scope.sourceForDataSeries);
        $('#createDataSeriesModal').modal('hide');
        $scope.resetView();
        $('#dataSeriesCreatedModal').modal('show');
      } else {
        $scope.showMetadata(which);
      }
    };
    options.errorCallback = function() {
      alert("Unable to update metadata !");
    };
    utilities.post(options);
  }

  // ////////////////////////////////
  // Metadata translations management
  // ////////////////////////////////
  $scope.showTranslation = function(which, languageCode) {
    console.log("Showing translation for [" + which + "] in language [" + languageCode + "]");
  }

  // //////////////////////////
  // Data validators management
  // //////////////////////////

  // The new data validator
  $scope.dataValidatorNewResource;

  // The new data validator sort management
  $scope.dataValidatorPredicate = 'name';

  // Reset the data validators part of the UI
  $scope.resetDataValidators = function() {
    $scope.metadata.dataValidators = [];

    // Hide the form
    $scope.dataValidatorsAvailable = false;
  }

  $scope.enableAddDataValidatorForm = true;
  $scope.filterDataValidator = function(v) {
    var found = false;
    if (v && $scope.metadata && $scope.metadata.dataValidators) {
      for (var i = 0; i < $scope.metadata.dataValidators.length; i++) {
        var dv = $scope.metadata.dataValidators[i];
        if (dv.name === v.name) {
          found = true;
          break;
        }
      }
    }
    // $scope.enableAddDataValidatorForm = !found; <-- not always working...
    return !found;
  }

  // Create a data validator
  // =======================
  $scope.createDataValidator = function(data) {
    var params = {};
    if (data && data.name && data.name.name) {
      angular.extend(params, {
        "which" : data.name.name
      });
    }
    if (data && data.value) {
      angular.extend(params, {
        "data" : data.value
      });
    }
    angular.extend(params, {
      "languageCode" : "default"
    });
    angular.extend(params, {
      "indicatorTypeCode" : $scope.indicatorType.code
    });
    angular.extend(params, {
      "sourceCode" : $scope.source.code
    });

    return utilities.createResource({
      validate : $scope.checkCreateDataValidatorForm,
      data : data,
      params : params,
      url : '/admin/curated/metadataForIndicatorTypeAndSource/submitUpdate',
      successCallback : function() {
        $scope.resetNewDataValidator();
        $scope.resetCreateDataValidatorForm();
        $scope.showMetadata("CURATED_DATA_VALIDATORS");
      },
      errorCallback : function() {
        alert("Data validator creation threw an error. Maybe this data validator already exists. No data validator has been created.");
      }
    });
  };

  // Check that the new data validator is complete
  $scope.checkCreateDataValidatorForm = function(data) {
    if (!data) {
      utilities.setFocus('dataValidatorNewResource_code');
      return "At least some info should be provided.";
    }
    var name = data.name;
    if (!name || null === name || '' === name) {
      utilities.setFocus('dataValidatorNewResource_name');
      return "Name cannot be empty.";
    }
    var value = data.value;
    if ('' === value || null === value) {
      utilities.setFocus('dataValidatorNewResource_value');
      return "Value cannot be empty.";
    }
    return "OK";
  };

  // Reset the new data validator
  $scope.resetNewDataValidator = function() {
    if (!$scope.dataValidatorNewResource) {
      $scope.dataValidatorNewResource = {};
    }
    $scope.dataValidatorNewResource.name = "";
    $scope.dataValidatorNewResource.value = "";
  };

  // Reset the create resource form
  $scope.resetCreateDataValidatorForm = function() {
    $scope.createDataValidatorForm.$setPristine();
  };

  // Special handling for data validators update
  $scope.updatedDataValidatorName = "";
  $scope.updatedDataValidatorValue = "";
  $scope.updateDataValidator = function(data, dataValidatorName) {
    $scope.updatedDataValidatorName = dataValidatorName;
    $scope.updatedDataValidatorValue = data.value;
    $scope.updateMetadata("CURATED_DATA_VALIDATORS", "default");
    $scope.updatedDataValidatorName = "";
    $scope.updatedDataValidatorValue = "";
  }

  // Delete a data validator
  $scope.deleteDataValidator = function(id) {
    return utilities.deleteResource({
      params : {
        "id" : id
      },
      url : '/admin/curated/dataValidators/submitDelete',
      successCallback : function() {
        $scope.showMetadata("CURATED_DATA_VALIDATORS");
        // $scope.$apply();
      },
      errorCallback : function() {
        alert("Data validator deletion threw an error. No data validator has been deleted.");
      }
    });
  }

  // ////////////////////////////////////////
  // Validation notes and comments management
  // ////////////////////////////////////////

  // Reset the validation notes part of the UI
  $scope.resetValidationNotes = function() {
    $scope.validationNotes = "";

    // Hide the form
    $scope.validationNotesAvailable = false;
  }

  // ///////////////////////////////////////////////////////
  // Manage Indicator type / Source selection part of the UI 
  // ///////////////////////////////////////////////////////

  // On indicator type selection, 
  // reload the sources available for this indicator type
  // and hide the metadata.
  $scope.indicatorTypeSelect = function() {
    $scope.resetView();
    $scope.loadSourcesForIndicatorType();
  }

  // On source selection, 
  // hide the metadata.
  $scope.sourceSelect = function() {
    $scope.resetView();
  }

  // Load the sources available for the current selected indicator type.
  $scope.loadSourcesForIndicatorType = function(aSource) {
    return $http.get(hdxContextRoot + '/admin/curated/sourcesForIndicatorType/json', {
      params : {
        indicatorTypeCode : $scope.indicatorType.code
      }
    }).success(function(data, status, headers, config) {
      $scope.sourcesForIndicatorType = data;
      $scope.sourceUnavailable = true;
      if ($scope.sourcesForIndicatorType && 0 < $scope.sourcesForIndicatorType.length) {
        if(aSource && aSource.code) {
          var sourceToSelect = utilities.findSomething($scope.sources, "code", aSource.code);
          if(sourceToSelect && 1 === sourceToSelect.length) {
            $scope.source = sourceToSelect[0];
          }
        }
        else {
          $scope.source = $scope.sourcesForIndicatorType[0];
        }
        $scope.sourceUnavailable = false;
      }
    }).error(function(data, status, headers, config) {
      $scope.sourceUnavailable = true;
    });
  }

  // Display the default indicator type and other values.
  var selected = utilities.findSomething($scope.indicatorTypes, 'code', "PVF020");
  $scope.indicatorType = selected[0];

  // Fill the UI according to default values
  $scope.indicatorTypeSelect();

}).filter('showIndicatorType', function() {
  return function(indicatorType) {
    return indicatorType.code + " (" + indicatorType.name + ")";
  };
}).filter('showSource', function() {
  return function(source) {
    var result = source.code + " (" + source.name + ")";
    // console.log("Show source : " + result);
    return result;
  };
});
