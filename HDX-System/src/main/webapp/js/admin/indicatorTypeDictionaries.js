app.controller('IndicatorTypeDictionariesCtrl', function($scope, utilities) {

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
    if (data && data.importer) {
      angular.extend(params, {
        "importer" : data.importer
      });
    }
    return utilities.createResource({
      validate : $scope.checkCreateForm,
      data : data,
      params : params,
      url : '/admin/dictionaries/indicatorTypes/submitCreate',
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
    var importer = data.importer;
    if (!importer || null === importer || '' === importer) {
      utilities.setFocus('newResource_importer');
      return "Importer cannot be empty.";
    }
    return "OK";
  };

});