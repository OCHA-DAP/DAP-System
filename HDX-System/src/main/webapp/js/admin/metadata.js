app.controller('MetadataCtrl', function($scope, $filter, $http, utilities) {

  // /////////
  // Resources
  // /////////
  $scope.resources = function() {
    $scope.indicatorTypes = appData['indicatorTypes'];
    $scope.languages = appData['languages'];
  }
  $scope.resources();

  $scope.sourceUnavailable = true;

  $scope.resetView = function() {
    $scope.resetMetadata();
    $scope.resetTimeParameters();
  }

  // ///////////////////
  // Metadata management
  // ///////////////////

  $scope.metadataAvailable = false;

  // Metadata
  $scope.metadata = {};
  $scope.metadata.datasetSummary = "";
  $scope.metadata.methodology = "";
  $scope.metadata.moreInfo = "";
  $scope.metadata.termsOfUse = "";

  // Reset the metadata part of the UI
  $scope.resetMetadata = function() {
    $scope.metadata = {};

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

    // Empty the text areas
    $("#datasetSummary").empty();
    $("#methodology").empty();
    $("#moreInfo").empty();
    $("#termsOfUse").empty();

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
          if ("DATASET_SUMMARY" == metadata_.entryKey) {
            $scope.metadata.datasetSummary = metadata_.entryValue;
          } else if ("METHODOLOGY" == metadata_.entryKey) {
            $scope.metadata.methodology = metadata_.entryValue;
          } else if ("MORE_INFO" == metadata_.entryKey) {
            $scope.metadata.moreInfo = metadata_.entryValue;
          } else if ("TERMS_OF_USE" == metadata_.entryKey) {
            $scope.metadata.termsOfUse = metadata_.entryValue;
          }
        }
      }
      $scope.metadataAvailable = true;
      if (which) {
        $('#metadataTabs a[href="#' + which + '"]').tab('show')
      } else {
        $('#metadataTabs a:first').tab('show');
      }
    }).error(function(data, status, headers, config) {
      $scope.resetView();
    });
  }

  // Update the metadata
  $scope.updateMetadata = function(data, which) {
    if (!data || null === data) {
      data = "";
    }
    var options = {};
    options.params = {
      which : which,
      data : data,
      indicatorTypeCode : $scope.indicatorType.code,
      sourceCode : $scope.source.code
    };
    options.url = "/admin/curated/metadataForIndicatorTypeAndSource/submitUpdate";
    options.successCallback = function() {
      $scope.showMetadata(which);
    };
    options.errorCallback = function() {
      alert("Unable to update metadata !");
    };
    utilities.post(options);
  }

  // ////////////////////////// //
  // Time parameters management
  // ////////////////////////// //

  $scope.timeParametersAvailable = false;

  // Time parameters
  $scope.timeParameters = {};
  $scope.timeParameters.expectedTimeFormat = "";
  $scope.timeParameters.interpretedStartTime = "";
  $scope.timeParameters.interpretedEndTime = "";

  // Reset the time parameters part of the UI
  $scope.resetTimeParameters = function() {
    $scope.timeParameters = {};

    // Hide the form
    $scope.timeParametersAvailable = false;
  }

  // If we have an indicator type and a source,
  // we can show the corresponding time parameters.
  $scope.showTimeParameters = function() {
    $scope.resetView();
    $scope.timeParametersAvailable = true;
    /*
    return $http.get(hdxContextRoot + '/admin/curated/timeParametersForIndicatorTypeAndSource/json', {
      params : {
        indicatorTypeCode : $scope.indicatorType.code,
        sourceCode : $scope.source.code
      }
    }).success(function(data, status, headers, config) {
      $scope.resetTimeParameters();
      if (data) {
        $scope.timeParameters.expectedTimeFormat = data.expectedTimeFormat;
      }
      $scope.timeParametersAvailable = true;
    }).error(function(data, status, headers, config) {
      $scope.resetTimeParameters();
    });
    */
  }

  // Update the time parameters
  $scope.updateTimeParameters = function() {
    if (!$scope.timeParameters || null === $scope.timeParameters || ((!$scope.timeParameters.expectedTimeFormat || null === $scope.timeParameters.expectedTimeFormat || "" === $scope.timeParameters.expectedTimeFormat) 
        && (!$scope.timeParameters.interpretedStartTime || null === $scope.timeParameters.interpretedStartTime || "" === $scope.timeParameters.interpretedStartTime)
        && (!$scope.timeParameters.interpretedEndTime || null === $scope.timeParameters.interpretedEndTime || "" === $scope.timeParameters.interpretedEndTime)
    )) {
      alert("Please at least fill some info.");
      $('#expectedTimeFormat').focus();
      return;
    }
    if (!$scope.timeParameters.expectedTimeFormat || null === $scope.timeParameters.expectedTimeFormat) {
      $scope.timeParameters.expectedTimeFormat = "";
    }
    if (!$scope.timeParameters.interpretedStartTime || null === $scope.timeParameters.interpretedStartTime) {
      $scope.timeParameters.interpretedStartTime = "";
    }
    if (!$scope.timeParameters.interpretedEndTime || null === $scope.timeParameters.interpretedEndTime) {
      $scope.timeParameters.interpretedEndTime = "";
    }
    var options = {};
    options.params = {
      expectedTimeFormat : $scope.timeParameters.expectedTimeFormat,
      interpretedStartTime : $scope.timeParameters.interpretedStartTime,
      interpretedEndTime : $scope.timeParameters.interpretedEndTime,
      indicatorTypeCode : $scope.indicatorType.code,
      sourceCode : $scope.source.code
    };
    options.url = "/admin/curated/timeParametersForIndicatorTypeAndSource/submitUpdate";
    options.successCallback = function() {
      $scope.showTimeParameters();
    };
    options.errorCallback = function() {
      alert("Unable to update time parameters !");
    };
    utilities.post(options);
  }

  // ///////////////////////////////////////////////////////
  // Manage Indicator type / Source selection part of the UI 
  // ///////////////////////////////////////////////////////

  // On indicator type selection, 
  // reload the sources available for this indicator type
  // and hide the metadata.
  $scope.indicatorTypeSelect = function() {
    $scope.resetView();
    $scope.loadSources();
  }

  // On source selection, 
  // hide the metadata.
  $scope.sourceSelect = function() {
    $scope.resetView();
  }

  // Load the sources available for the current selected indicator type.
  $scope.loadSources = function() {
    return $http.get(hdxContextRoot + '/admin/curated/sourcesForIndicatorType/json', {
      params : {
        indicatorTypeCode : $scope.indicatorType.code
      }
    }).success(function(data, status, headers, config) {
      $scope.sources = data;
      $scope.sourceUnavailable = true;
      if ($scope.sources && 0 < $scope.sources.length) {
        $scope.source = $scope.sources[0];
        $scope.sourceUnavailable = false;
      }
    }).error(function(data, status, headers, config) {
      $scope.sourceUnavailable = true;
    });
  }

  // Display the default indicator type and other values.
  var selected = $filter('filter')($scope.indicatorTypes, {
    code : "PVF020"
  });
  $scope.indicatorType = selected[0];

  // Fill the UI according to default values
  $scope.indicatorTypeSelect();

}).filter('showIndicatorType', function() {
  return function(indicatorType) {
    return indicatorType.code + " (" + indicatorType.name + ")";
  };
}).filter('showSource', function() {
  return function(source) {
    return source.code + " (" + source.name + ")";
  };
});
