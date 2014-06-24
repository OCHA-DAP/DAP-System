app.controller('DatasetsCtrl', function($scope, utilities) {

  // Sort management
  $scope.predicate = 'title';

  // Resources
  $scope.loadDatasets = function() {
    return utilities.loadResource($scope, 'ckan', '/admin/status/datasets/json', function() {
      /*
      if ($scope.ckan && $scope.ckan.datasets && 0 < $scope.ckan.datasets.length) {
        for (var i = 0; i < $scope.ckan.datasets.length; i++) {
          var dataset = $scope.ckan.datasets[i];
          angular.extend(dataset, {
            "newConfiguration" : null
          });
        }
      }
      */
    });
  }
  $scope.loadDatasets();

  // Datasets management
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

  $scope.filterDatasets = function(dataset) {
    if('Show ignored' === $scope.showHideIgnoredMessage) {
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
});