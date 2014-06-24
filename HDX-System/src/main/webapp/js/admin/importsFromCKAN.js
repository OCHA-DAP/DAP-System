app.controller('ImportsFromCKANCtrl', function($scope, utilities) {

  // Sort management
  $scope.predicate = 'id';

  // Resources
  $scope.loadImportsFromCKAN = function() {
    return utilities.loadResource($scope, 'importsFromCKAN', '/admin/curated/importsfromckan/json', function() {
    });
  }
  $scope.loadImportsFromCKAN();

  // Delete an import
  // ================
  $scope.deleteImport = function(id) {
    return utilities.deleteResource({
      params : {
        "importToDeleteId" : id
      },
      url : '/admin/curated/importsfromckan/delete',
      successCallback : $scope.loadImportsFromCKAN,
      errorCallback : function() {
        alert("Import deletion threw an error. No import has been deleted.");
      }
    });
  }

});