app.controller('MetadataCtrl', function($scope, $filter, $http, utilities) {

  // /////////
  // Resources
  // /////////
  $scope.resources = function() {
    $scope.indicatorTypes = appData['indicatorTypes'];
    $scope.languages = appData['languages'];
  }
  $scope.resources();

  // /////////////////// //
  // Metadata management
  // /////////////////// //

  $scope.sourceUnavailable = true;
  $scope.metadataAvailable = false;

  // Metadata
  $scope.metadata = {};
  $scope.metadata.datasetSummary = "";
  $scope.metadata.methodology = "";
  $scope.metadata.moreInfo = "";
  $scope.metadata.termsOfUse = "";

  /*
  // The forms
  $scope.datasetSummaryForm;
  $scope.methodologyForm;
  $scope.moreInfoForm;
  $scope.termsOfUseForm;
  */
  // Reset the metadata part of the UI
  $scope.resetMetadata = function() {
    $scope.metadata = {};

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

    $("#datasetSummary").empty();
    $("#methodology").empty();
    $("#moreInfo").empty();
    $("#termsOfUse").empty();

    $scope.metadataAvailable = false;
  }

  // On indicator type selection, 
  // reload the sources available for this indicator type
  // and hide the metadata.
  $scope.indicatorTypeSelect = function() {
    $scope.resetMetadata();
    $scope.loadSources();
  }

  // On source selection, 
  // hide the metadata.
  $scope.sourceSelect = function() {
    $scope.resetMetadata();
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

  // If we have an indicator type and a source,
  // we can show the corresponding metadata.
  $scope.showMetadata = function(which) {
    return $http.get(hdxContextRoot + '/admin/curated/metadataForIndicatorTypeAndSource/json', {
      params : {
        indicatorTypeCode : $scope.indicatorType.code,
        sourceCode : $scope.source.code
      }
    }).success(function(data, status, headers, config) {
      $scope.resetMetadata();
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
      $scope.resetMetadata();
    });
  }

  // Update the metadata
  $scope.updateMetadata = function(data, which) {
    var options = {};
    if (!data || null === data) {
      data = "";
    }
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
    utilities.post(options);
  }
});