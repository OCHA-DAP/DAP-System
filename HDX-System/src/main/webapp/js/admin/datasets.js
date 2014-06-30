app.controller('DatasetsCtrl', function($scope, utilities) {

  // Sort management
  $scope.predicate = 'title';

  // Resources
  $scope.resources = function() {
    $scope.importers = appData['importers'];
  }
  $scope.resources();

  $scope.findConfiguration = function(configName) {
    if (configName && null != configName && "" !== configName) {
      var selected = utilities.findSomething($scope.ckan.configurations, "name", configName);
      var result = (selected && selected.length) ? selected[0].code : "";
      return result;
    } else
      return null;
  }

  $scope.loadDatasets = function() {
    return utilities.loadResource($scope, 'ckan', '/admin/status/datasets/json', function() {
      /*
      if ($scope.ckan && $scope.ckan.datasets && 0 < $scope.ckan.datasets.length) {
        for (var i = 0; i < $scope.ckan.datasets.length; i++) {
          var dataset = $scope.ckan.datasets[i];
          angular.extend(dataset, {
            "newConfiguration" : $scope.findConfiguration(dataset.configuration.name)
          });
        }
      }
      */
    });
  }
  $scope.loadDatasets();

  // Datasets management
  $scope.selectedDataset = {};
  $scope.selectedImporter = "";
  $scope.selectedConfiguration = "";
  $scope.importAndConfigSetup = function(dataset) {
    $scope.selectedDataset = dataset;
    $scope.selectedImporter = dataset.type;
    if ('SCRAPER_VALIDATING' === dataset.type) {
      $scope.selectedConfiguration = dataset.configuration.id;
    }
  }

  $scope.resetSelectedConfiguration = function() {
    $scope.selectedConfiguration = "";
  }
  $scope.setImportAndConfigurationDisabled = function() {
    var isImporterSet = $scope.selectedImporter && '' !== $scope.selectedImporter;
    var isScraperValidating = $scope.selectedImporter && 'SCRAPER_VALIDATING' === $scope.selectedImporter;
    var isConfigSet = $scope.selectedConfiguration && '' !== $scope.selectedConfiguration;
    return !((isScraperValidating && isConfigSet) || (isImporterSet && !isScraperValidating && !isConfigSet));
  }

  $scope.showHideIgnoredMessage = "Hide ignored";
  $scope.toggleShowHideIgnored = function() {
    switch ($scope.showHideIgnoredMessage) {
    case 'Hide ignored':
      $scope.showHideIgnoredMessage = "Show ignored";
      break;
    case 'Show ignored':
      $scope.showHideIgnoredMessage = "Hide ignored";
      break;

    default:
      break;
    }
  }

  $scope.showImporter = function(dataset) {
    return dataset.type
        + (("SCRAPER_VALIDATING" === dataset.type) ? " (" + ((dataset.configuration.name && dataset.configuration.name) ? dataset.configuration.name : "configuration not set") + ")" : "");
  }
  $scope.setImportAndConfiguration = function() {
    return $scope.updateDatasetImporterAndConfiguration();
  }

  $scope.filterDatasets = function(dataset) {
    if ('Show ignored' === $scope.showHideIgnoredMessage) {
      return dataset.status !== 'IGNORED';
    }
    return true;
  }

  $scope.flagForCuration = function(datasetName) {
    var params = {};
    angular.extend(params, {
      "datasetName" : datasetName
    });
    return utilities.updateResource({
      validate : function(data) {
        return "OK";
      },
      data : {},
      params : params,
      url : '/admin/status/datasets/flagDatasetAsToBeCurated',
      successCallback : function() {
        $scope.loadDatasets();
      },
      errorCallback : function() {
        alert("Dataset update threw an error. No dataset has been updated.");
        $scope.loadDatasets();
      }
    });
  }

  $scope.ignoreDataset = function(datasetName) {
    var params = {};
    angular.extend(params, {
      "datasetName" : datasetName
    });
    return utilities.updateResource({
      validate : function(data) {
        return "OK";
      },
      data : {},
      params : params,
      url : '/admin/status/datasets/flagDatasetAsIgnored',
      successCallback : function() {
        $scope.loadDatasets();
      },
      errorCallback : function() {
        alert("Dataset update threw an error. No dataset has been updated.");
        $scope.loadDatasets();
      }
    });
  }

  $scope.updateDatasetImporterAndConfiguration = function() {
    var params = {};
    angular.extend(params, {
      "datasetName" : $scope.selectedDataset.name
    });
    angular.extend(params, {
      "importer" : $scope.selectedImporter
    });
    angular.extend(params, {
      "configurationId" : $scope.selectedConfiguration
    });
    return utilities.updateResource({
      validate : function(data) {
        return "OK";
      },
      data : {},
      params : params,
      url : '/admin/status/datasets/updateDatasetImporterAndConfiguration',
      successCallback : function() {
        $('#chooseImporterAndConfigModal').modal('hide');
        $scope.loadDatasets();
        $('#importerAndConfigChosenModal').modal('show');
      },
      errorCallback : function() {
        alert("Dataset update threw an error. No dataset has been updated.");
        $scope.loadDatasets();
      }
    });
  }
});