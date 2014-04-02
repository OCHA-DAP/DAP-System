app
    .controller(
        'DataSeriesCtrl',
        function($scope, $filter, $http, utilities) {

          // /////////
          // Resources
          // /////////
          $scope.resources = function() {
            $scope.indicatorTypes = appData['indicatorTypes'];
            $scope.languages = appData['languages'];
            $scope.periodicities = appData['periodicities'];
          }
          $scope.resources();

          $scope.sourceUnavailable = true;

          $scope.resetView = function() {
            $scope.resetMetadata();
            $scope.resetTimeParameters();
          }

          // //////////////////////
          // Data Series management
          // //////////////////////

          $scope.metadataAvailable = false;
          $scope.timeParametersAvailable = false;

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
                  } else if ("EXPECTED_TIME_FORMAT" === metadata_.entryKey) {
                    $scope.timeParameters.expectedTimeFormat = metadata_.entryValue;
                  } else if ("INTERPRETED_START_TIME" === metadata_.entryKey) {
                    $scope.timeParameters.interpretedStartTime = metadata_.entryValue;
                  } else if ("INTERPRETED_END_TIME" === metadata_.entryKey) {
                    $scope.timeParameters.interpretedEndTime = metadata_.entryValue;
                  } else if ("INTERPRETED_PERIODICITY" === metadata_.entryKey) {
                    $scope.timeParameters.interpretedPeriodicity = metadata_.entryValue;
                  }
                }
                $scope.assignNewValues();
              }
              $scope.metadataAvailable = true;
              if (which) {
                if ("TIME_PARAMETERS" === which) {
                  $scope.metadataAvailable = false;
                  $scope.timeParametersAvailable = true;
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
          
          $scope.applyChange = function (data, which, languageCode) {
            // console.log("Apply change for [" + which + "], language [" + languageCode + "], data [" + data + "]");
            switch (which) {
            case 'DATASET_SUMMARY':
              if('default' === languageCode) {
                $scope.metadata.datasetSummary = data;
              }
              else {
                $scope.metadata.datasetSummaryTranslations[languageCode] = data;
              }
              break;
            case 'METHODOLOGY':
              if('default' === languageCode) {
                $scope.metadata.methodology = data;
              }
              else {
                $scope.metadata.methodologyTranslations[languageCode] = data;
              }
              break;
            case 'MORE_INFO':
              if('default' === languageCode) {
                $scope.metadata.moreInfo = data;
              }
              else {
                $scope.metadata.moreInfoTranslations[languageCode] = data;
              }
              break;
            case 'TERMS_OF_USE':
              if('default' === languageCode) {
                $scope.metadata.termsOfUse = data;
              }
              else {
                $scope.metadata.termsOfUseTranslations[languageCode] = data;
              }
              break;
            default:
              break;
            }
          }

          // Update the metadata
          $scope.updateMetadata = function(which, languageCode) {
            var data = "";
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
            default:
              break;
            }
            
            // console.log("Updating metadata for [" + which + "], language [" + languageCode + "], data [" + data + "]");
            
            var options = {};
            options.params = {
              which : which,
              data : data,
              languageCode : languageCode,
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

          // ////////////////////////////////
          // Metadata translations management
          // ////////////////////////////////
          $scope.showTranslation = function(which, languageCode) {
            console.log("Showing translation for [" + which + "] in language [" + languageCode + "]");
          }

          // ////////////////////////// //
          // Time parameters management
          // ////////////////////////// //

          // Reset the time parameters part of the UI
          $scope.resetTimeParameters = function() {
            $scope.timeParameters = {};
            $scope.timeParameters.expectedTimeFormat = "";
            $scope.timeParameters.interpretedStartTime = "";
            $scope.timeParameters.interpretedEndTime = "";
            $scope.timeParameters.interpretedPeriodicity = "";

            $scope.assignNewValues();

            // Hide the form
            $scope.timeParametersAvailable = false;
            $scope.editTimeParameters = false;
          }

          $scope.assignNewValues = function() {
            // Time parameters new values
            $scope.newValuesTimeParameters = {};
            $scope.newValuesTimeParameters.expectedTimeFormat = $scope.timeParameters.expectedTimeFormat;
            $scope.newValuesTimeParameters.interpretedStartTime = $scope.timeParameters.interpretedStartTime;
            $scope.newValuesTimeParameters.interpretedEndTime = $scope.timeParameters.interpretedEndTime;
            $scope.newValuesTimeParameters.interpretedPeriodicity = $scope.timeParameters.interpretedPeriodicity;
          }

          $scope.showEditTimeParameters = function(mode) {
            $scope.editTimeParameters = mode;
            if (!mode) {
              $scope.newValuesTimeParameters.expectedTimeFormat = $scope.timeParameters.expectedTimeFormat;
              $scope.newValuesTimeParameters.interpretedStartTime = $scope.timeParameters.interpretedStartTime;
              $scope.newValuesTimeParameters.interpretedEndTime = $scope.timeParameters.interpretedEndTime;
              $scope.newValuesTimeParameters.interpretedPeriodicity = $scope.timeParameters.interpretedPeriodicity;
            } else {
              setTimeout(function() {
                utilities.setFocus("expectedTimeFormat"); // Not working if we don't wait a bit
              }, (50));
            }
          }

          // Update the time parameters
          $scope.updateTimeParameters = function() {
            if (!$scope.newValuesTimeParameters
                || null === $scope.newValuesTimeParameters
                || ((!$scope.newValuesTimeParameters.expectedTimeFormat || null === $scope.newValuesTimeParameters.expectedTimeFormat || "" === $scope.newValuesTimeParameters.expectedTimeFormat)
                    && (!$scope.newValuesTimeParameters.interpretedStartTime || null === $scope.newValuesTimeParameters.interpretedStartTime || "" === $scope.newValuesTimeParameters.interpretedStartTime)
                    && (!$scope.newValuesTimeParameters.interpretedEndTime || null === $scope.newValuesTimeParameters.interpretedEndTime || "" === $scope.newValuesTimeParameters.interpretedEndTime) && (!$scope.newValuesTimeParameters.interpretedPeriodicity
                    || null === $scope.newValuesTimeParameters.interpretedPeriodicity || "" === $scope.newValuesTimeParameters.interpretedPeriodicity))) {
              alert("Please at least fill some info.");
              utilities.setFocus("expectedTimeFormat");
              return;
            }
            if (!$scope.newValuesTimeParameters.expectedTimeFormat || null === $scope.newValuesTimeParameters.expectedTimeFormat) {
              $scope.newValuesTimeParameters.expectedTimeFormat = "";
            }
            if (!$scope.newValuesTimeParameters.interpretedStartTime || null === $scope.newValuesTimeParameters.interpretedStartTime) {
              $scope.newValuesTimeParameters.interpretedStartTime = "";
            }
            if (!$scope.newValuesTimeParameters.interpretedEndTime || null === $scope.newValuesTimeParameters.interpretedEndTime) {
              $scope.newValuesTimeParameters.interpretedEndTime = "";
            }
            if (!$scope.newValuesTimeParameters.interpretedPeriodicity || null === $scope.newValuesTimeParameters.interpretedPeriodicity) {
              $scope.newValuesTimeParameters.interpretedPeriodicity = "";
            }
            var options = {};
            options.params = {
              expectedTimeFormat : $scope.newValuesTimeParameters.expectedTimeFormat,
              interpretedStartTime : $scope.newValuesTimeParameters.interpretedStartTime,
              interpretedEndTime : $scope.newValuesTimeParameters.interpretedEndTime,
              interpretedPeriodicity : $scope.newValuesTimeParameters.interpretedPeriodicity,
              indicatorTypeCode : $scope.indicatorType.code,
              sourceCode : $scope.source.code
            };
            options.url = "/admin/curated/timeParametersForIndicatorTypeAndSource/submitUpdate";
            options.successCallback = function() {
              $scope.showMetadata("TIME_PARAMETERS");
              $scope.showEditTimeParameters(false);
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
        return source.code + " (" + source.name + ")";
      };
    });
