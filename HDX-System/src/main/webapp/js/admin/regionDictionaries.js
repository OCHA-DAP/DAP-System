app.controller('RegionDictionariesCtrl', function($scope, utilities) {

  // The new dictionary
  $scope.newResource;

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
    if (data && data.importer) {
      angular.extend(params, {
        "importer" : data.importer
      });
    }
    return utilities.createResource({
      validate : $scope.checkCreateForm,
      data : data,
      params : params,
      url : '/admin/dictionaries/regions/submitCreate',
      successCallback : function() {
        window.location.reload(true);
       },
      errorCallback : function() {
        alert("Dictionary creation threw an error. Maybe this dictionary already exists. No dictionary has been created.");
        window.location.reload(true);
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
    var importer = data.importer;
    if (!importer || null === importer || '' === importer) {
      utilities.setFocus('newResource_importer');
      return "Importer cannot be empty.";
    }
    return "OK";
  };

});