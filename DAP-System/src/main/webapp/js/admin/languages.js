app.controller('LanguagesCtrl', function($scope, $filter, $http, $rootScope, utilities) {
  
  // ////////////////////
  // Languages management
  // ////////////////////

  // Sort management
  $scope.predicate = 'code';

  // Load languages
  // ==============
  $scope.loadLanguages = function() {
    return utilities.loadResource($scope, 'languages', '/admin/misc/languages/json');
  }
  $scope.loadLanguages();

  // Create a language
  // =================
  $scope.createLanguage = function(data) {
    var code = data ? data.code : "";
    var nativeName = data ? data.native_name : "";
    return utilities.createResource({
      validate : $scope.checkCreateForm,
      data : data,
      params : {
        "code" : code,
        "newNativeName" : nativeName
      },
      url : '/admin/misc/languages/submitCreate',
      successCallback : function() {
        $scope.resetNewLanguage();
        $scope.resetCreateResourceForm();
        $scope.loadLanguages();
      },
      errorCallback : function() {
        alert("Language creation threw an error. Maybe this language already exists. No language has been created.");
      }
    });
  }

  // Check that the new language is complete
  $scope.checkCreateForm = function(data) {
    if (!data) {
      utilities.setFocus('newResource_code');
      return "At least some info should be provided.";
    }
    var code = data.code;
    if (!code || null === code || '' === code) {
      utilities.setFocus('newResource_code');
      return "Code cannot be empty.";
    }
    var nativeName = data.native_name;
    if (!nativeName || null === nativeName || '' === nativeName) {
      utilities.setFocus('newResource_native_name');
      return "Native name cannot be empty.";
    }
    return "OK";
  };

  // Reset the new language
  $scope.resetNewLanguage = function() {
    if (!$scope.newResource) {
      $scope.newResource = {};
    }
    $scope.newResource.code = "";
    $scope.newResource.native_name = "";
  };

  // Reset the create resource form
  $scope.resetCreateResourceForm = function() {
    $scope.createResourceForm.$setPristine();
  };

  // Update a language
  // =================
  $scope.updateLanguage = function(data, code) {
    return utilities.updateResource({
      validate : $scope.checkUpdateForm,
      data : data,
      params : {
        "code" : code,
        "newNativeName" : data.native_name
      },
      url : '/admin/misc/languages/submitUpdate',
      successCallback : function() {
        $scope.loadLanguages();
      },
      errorCallback : function() {
        alert("Language update threw an error. No language has been updated.");
        $scope.loadLanguages();
      }
    });
  };

  // Check that the updated language is valid
  $scope.checkUpdateForm = function(data) {
    var nativeName = data.native_name;
    if (!nativeName || null === nativeName || '' === nativeName) {
      return "Native name cannot be empty.";
    }
    return "OK";
  };

  // Delete a language
  // =================
  $scope.deleteLanguage = function(code) {
    return utilities.deleteResource({
      params : {
        "code" : code
      },
      url : '/admin/misc/languages/submitDelete',
      successCallback : $scope.loadLanguages,
      errorCallback : function() {
        alert("Language deletion threw an error. Maybe this language is used in some translation. No language has been deleted.");
      }
    });
  };
});