// ******************* //
// The I18n controller //
// ******************* //
app.controller('I18nCtrl', function($scope, $filter, $http, utilities, $rootScope) {

  // /////////
  // Resources
  // /////////
  $scope.resources = function() {
    $scope.languages = appData['languages'];
  }
  $scope.resources();

  $rootScope.filterCount = 0;
  
  // ////////////////////
  // Languages management
  // ////////////////////

  /*
  // Load the languages
  utilities.loadResource($scope, 'languages', '/admin/misc/languages/json', function() {
    console.log("Languages loaded for scope [" + $scope + "]");
  });
  */
  
  // Check the languages are there
  $scope.checkLanguages = function() {
    var result = ($scope.languages && 0 < $scope.languages.length);
    // console.log("Check languages [" + result + "]");
    return result;
  }

  // Get a language by its code
  $scope.getLanguageByCode = function(languageCode) {
    var filteredLanguages = $filter('filter')($scope.languages, {
      code : languageCode
    });
    var theLanguage = filteredLanguages && 0 < filteredLanguages.length ? filteredLanguages[0] : null;
    return theLanguage;
  }

  // ////////////////////////
  // Translations management
  // ////////////////////////

  // Utilities
  // =========

  // Get a translation value for a resource, a resource identifier and a language code
  $scope.getTranslationForResourceAndLanguageCode = function(resource, identifier, languageCode) {
    var filterParam = {};
    filterParam[identifier] = languageCode;
    var filteredTranslations = $filter('filter')(resource.translations, filterParam);
    var theTranslation = filteredTranslations && 0 < filteredTranslations.length ? filteredTranslations[0] : null;
    return theTranslation ? theTranslation.value : null;
  }

  // Load the translations for a resource and a resource identifier from the server
  $scope.loadTranslations = function(instance, resource, identifier) {
    return $http.get(dapContextRoot + '/admin/translations/getFor/json', {
      params : {
        resource : resource,
        identifier : identifier
      }
    }).success(function(data, status, headers, config) {
      instance.translations = data;
    }).error(function(data, status, headers, config) {
    });
  }
});

//*************************** //
//The Translations controller //
//*************************** //
app.controller('TranslationsCtrl', function($scope, $filter, utilities, $rootScope) {

  // Sort management
  $scope.t_predicate = 'code';

  // Show only the languages for which some translations are missing
  // This is very VERY costly as it gets called by Angular everytime a value change anywhere in the model, it seems.
  // Maybe find another solution
  $scope.languagesByAvailableTranslations = function(resource, identifier, id) {
    //console.log("Filter :  languagesByAvailableTranslations [" + ++$rootScope.filterCount + "]");
    return function(language) {
      // console.log("\r\nNew filter call for id [" + id + "]");
      var theResource = utilities.findSomething(resource, identifier, id);
      // console.log("Found resource [" + theResource[0].id + "]");
      var translation = null;
      if (theResource && theResource[0] && theResource[0].translations) {
        translation = $scope.getTranslationForResourceAndLanguageCode(theResource[0], "code", language.code);
      }

      // At the same time, select by default the first missing language
      if (!translation) {
        // console.log("No translation found for resource [" + theResource[0].id + "] and language code [" + language.code + "]");
        if (!$scope.newTranslation) {
          // console.log("No new translation in scope, creating new one...");
          $scope.newTranslation = {};
          $scope.newTranslation.value = "";
          $scope.newTranslation.language = null;
        }
        else {
          // console.log("New translation already in scope with language code [" + ($scope.newTranslation.language ? $scope.newTranslation.language.code : "no language defined") + "]");
        }
        if (!$scope.newTranslation.language || null === $scope.newTranslation.language || (!$scope.newTranslation.language.code)) {
          $scope.newTranslation.language = language;
          // console.log("New translation filled with language code [" + $scope.newTranslation.language.code + "]");
        }
        else {
          // console.log("New translation already filled with language code [" + $scope.newTranslation.language.code + "]");
        }
      }
      else {
        // console.log("Translation found for resource [" + theResource[0].id + "] and language code [" + language.code + "]");
        if ($scope.newTranslation && $scope.newTranslation.language && language.code === $scope.newTranslation.language.code) {
          $scope.newTranslation.language = null;
        }
      }
      return null === translation;
    }
  };

  // Do we have to show the "Add translation" form ? 
  // Only if there are some translations missing for a given resource
  $scope.addLanguage = false;
  $scope.showAddTranslation = function(resource, identifier, id) {
    // console.log("showAddTranslation : resource [" + resource + "], identifier [" + identifier + "], id [" + id + "]");

    // Are there some languages ?
    if (!$scope.checkLanguages()) {
      return false;
    }

    // Get the resource
    var theResource = utilities.findSomething(resource, identifier, id);
    var result = theResource && theResource[0]
        && (!theResource[0].translations || theResource[0].translations.length < $scope.languages.length);
    $scope.addLanguage = !result;
    return result;
  };

  // Create a translation
  // ====================
  $scope.newTranslation;
  $scope.createTranslation = function(text_id, instance, resource, identifier) {
    if (!$scope.newTranslation) {
      return;
    }
    var data = $scope.newTranslation;
    if (!data) {
      return;
    }
    var params = {};
    angular.extend(params, {
      "textId" : text_id
    });
    if (data.language && data.language.code) {
      angular.extend(params, {
        "languageCode" : data.language.code
      });
    }
    if (data.value) {
      angular.extend(params, {
        "translationValue" : data.value
      });
    }
    
    // Adding text_id to data to set the focus on the field in case of error
    angular.extend(data, {
      "textId" : text_id
    });
    
    return utilities.createResource({
      validate : $scope.checkCreateForm,
      data : data,
      params : params,
      url : '/admin/translations/submitCreate',
      successCallback : function() {
        console.log("Translation created for language [" + data.language.code + "]");
        $scope.resetNewTranslation();
        $scope.loadTranslations(instance, resource, identifier);
      },
      errorCallback : function() {
        console.log("Translation error creation for language [" + data.language.code + "]");
        alert("Translation creation threw an error. Maybe this translation already exists. No translation has been created.");
      }
    });
  };

  // Check that the new translation is complete
  $scope.checkCreateForm = function(data) {
    var value = data.value;
    if (!value || null === value || '' === value) {
      utilities.setFocus("newTranslation_" + data.textId + "_value");
      return "Translation cannot be empty.";
    }
    return "OK";
  };

  // Reset it
  $scope.resetNewTranslation = function() {
    console.log("Resetting new translation...");
    if (!$scope.newTranslation) {
      $scope.newTranslation = {};
    }
    $scope.newTranslation.value = "";
    $scope.newTranslation.language = null;
    $scope.resetCreateTranslationForm();
  };

  // Reset the create translation form
  $scope.resetCreateTranslationForm = function() {
    for ( var theForm in $scope.t_rowform) {
      theForm.$setPristine();
    }
  };

  // Update a translation
  // ====================
  $scope.updateTranslation = function(data, text_id, language_code, instance, resource, identifier) {
    var params = {};
    angular.extend(params, {
      "textId" : text_id
    });
    angular.extend(params, {
      "languageCode" : language_code
    });
    if (data.value) {
      angular.extend(params, {
        "translationValue" : data.value
      });
    }

    return utilities.updateResource({
      validate : $scope.checkUpdateForm,
      data : data,
      params : params,
      url : '/admin/translations/submitUpdate',
      successCallback : function() {
        console.log("Translation updated for language [" + language_code + "]");
        $scope.loadTranslations(instance, resource, identifier);
      },
      errorCallback : function() {
        console.log("Translation update error for language [" + language_code + "]");
        alert("Translation update threw an error. No translation has been updated.");
        $scope.loadTranslations(instance, resource, identifier);
      }
    });
  };

  // Check that the new translation is complete
  $scope.checkUpdateForm = function(data) {
    var value = data.value;
    if (!value || null === value || '' === value) {
      return "Translation cannot be empty.";
    }
    return "OK";
  };

  // Delete a translation
  // ====================
  $scope.deleteTranslation = function(text_id, language_code, instance, resource, identifier) {
    return utilities.deleteResource({
      params : {
        "textId" : text_id,
        "languageCode" : language_code
      },
      url : '/admin/translations/submitDelete',
      successCallback : function() {
        console.log("Translation deleted for language [" + language_code + "]");
        $scope.resetNewTranslation();
        $scope.loadTranslations(instance, resource, identifier);
      },
      errorCallback : function() {
        console.log("Translation deletion error for language [" + language_code + "]");
        alert("Translation deletion threw an error. No translation has been deleted.");
      }
    });
  };
});