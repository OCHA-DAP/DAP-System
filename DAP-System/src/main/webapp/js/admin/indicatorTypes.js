var app = angular.module("app", [ "xeditable" ]);

app.run(function(editableOptions) {
  editableOptions.theme = 'bs2'; // Theme : can be 'bs3, 'bs2' or 'default'
});

app.controller('IndicatorTypesCtrl', function($scope, $filter, $http) {

  // Sort management
  $scope.predicate = 'code';
  $scope.t_predicate = 'code';

  // ////////////////////
  // IndicatorTypes management
  // ////////////////////

  $scope.indicatorTypes = [];

  // Load indicatorTypes
  $scope.loadIndicatorTypes = function() {
    return $http.get(dapContextRoot + '/admin/curated/indicatortypes/json').success(function(data) {
      $scope.indicatorTypes = data;
      $scope.resetNewTranslations();
    });
  };

  if (!$scope.indicatorTypes.length) {
    $scope.loadIndicatorTypes();
  }

  $scope.valueTypes = [];

  // Load value types
  $scope.loadValueTypes = function() {
    return $http.get(dapContextRoot + '/admin/curated/indicatortypes/valuetypes/json').success(function(data) {
      $scope.valueTypes = data;
      $scope.resetNewIndicatorType();
    });
  };

  if (!$scope.valueTypes.length) {
    $scope.loadValueTypes();
  }

  // Show an indicator type's value type
  $scope.showValueType = function(indicatorType) {
    var selected = $filter('filter')($scope.valueTypes, {
      value : indicatorType.valueType
    });
    return (indicatorType.valueType && selected.length) ? selected[0].text : 'Not set';
  };

  // Save (update) an indicatorType
  $scope.saveIndicatorType = function(data, id) {
    var valid = $scope.checkUpdateForm(data);
    if ("OK" === valid) {

      return $http.post(dapContextRoot + '/admin/curated/indicatortypes/submitupdate', "indicatorTypeId=" + id + "&newName=" + data.name + "&newUnit=" + data.unit + "&newValueType=" + data.valueType, {
        headers : {
          'Content-Type' : 'application/x-www-form-urlencoded'
        }
      }).success(function(data, status, headers, config) {
        // this callback will be called asynchronously
        // when the response is available
        $scope.loadIndicatorTypes();
      }).error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        alert("Indicator type update threw an error. No indicator type has been updated.");
        $scope.loadIndicatorTypes();
      });
    } else {
      alert("Form not valid ! \r\n" + valid);
      return "";
    }
  };

  // - check that the updated indicator type is valid
  $scope.checkUpdateForm = function(data) {
    var name = data.name;
    if ('' === name || null === name) {
      return "Name cannot be empty.";
    }
    var unit = data.unit;
    if ('' === unit || null === unit) {
      return "Unit cannot be empty.";
    }
    var valueType = data.valueType;
    if ('' === valueType || null === valueType) {
      return "Value type cannot be empty.";
    }
    return "OK";
  };

  // Remove an indicator type
  $scope.removeIndicatorType = function(id) {
    if (!confirm("Do you really want to delete this indicator type ?")) {
      return;
    }
    $http.post(dapContextRoot + '/admin/curated/indicatortypes/submitdelete', "indicatorTypeId=" + id, {
      headers : {
        'Content-Type' : 'application/x-www-form-urlencoded'
      }
    }).success(function(data, status, headers, config) {
      // this callback will be called asynchronously
      // when the response is available
      $scope.loadIndicatorTypes();
    }).error(function(data, status, headers, config) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
      alert("Indicator type deletion threw an error. Maybe this indicator type is used by some indicator. No indicator type has been deleted.");
    });
  };

  // add indicatorType
  // - the new indicatorType
  $scope.newindicatorType;

  // - reset it
  $scope.resetNewIndicatorType = function() {
    if (!$scope.newindicatorType) {
      $scope.newindicatorType = {};
    }
    $scope.newindicatorType.code = "";
    $scope.newindicatorType.name = "";
    $scope.newindicatorType.unit = "";
    $scope.newindicatorType.valueType = $scope.valueTypes ? $scope.valueTypes[0].value : "";

  };

  // - reset its form
  $scope.resetAddIndicatorTypeForm = function() {
    $scope.addIndicatorTypeForm.$setPristine();
  };

  // - add it
  $scope.addIndicatorType = function(data) {
    var valid = $scope.checkForm(data);
    if ("OK" === valid) {
      // alert("Add indicatorType : " + data);
      return $http.post(dapContextRoot + '/admin/curated/indicatortypes/submitadd', "code=" + data.code + "&name=" + data.name + "&unit=" + data.unit + "&valueType=" + data.valueType, {
        headers : {
          'Content-Type' : 'application/x-www-form-urlencoded'
        }
      }).success(function(data, status, headers, config) {
        // this callback will be called asynchronously
        // when the response is available
        // alert("IndicatorType added !");
        $scope.resetNewIndicatorType();
        $scope.resetAddIndicatorTypeForm();
        $scope.loadIndicatorTypes();
      }).error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        // alert("IndicatorType addition threw error : \r\n" + data);
        alert("Indicator type addition threw an error. Maybe this indicator type already exists. No indicator type has been created.");
      });
    } else {
      alert("Form not valid ! \r\n" + valid);
    }
  };

  // - check that the new indicatorType is complete
  $scope.checkForm = function(data) {
    var code = data.code;
    if ('' === code || null === code) {
      return "Code cannot be empty.";
    }
    var name = data.name;
    if ('' === name || null === name) {
      return "Name cannot be empty.";
    }
    var unit = data.unit;
    if ('' === unit || null === unit) {
      return "Unit cannot be empty.";
    }
    var valueType = data.valueType;
    if ('' === valueType || null === valueType) {
      return "Value type cannot be empty.";
    }
    return "OK";
  };

  // Get an indicator type by its id
  $scope.getIndicatorTypeById = function(indicatorTypeId) {
    var filteredIndicatorTypes = $filter('filter')($scope.indicatorTypes, {
      id : indicatorTypeId
    });
    var theIndicatorType = filteredIndicatorTypes && 0 < filteredIndicatorTypes.length ? filteredIndicatorTypes[0] : null;
    return theIndicatorType;
  }

  // ////////////////////////
  // Translations management
  // ////////////////////////

  $scope.languages = [];

  // Load languages
  $scope.loadLanguages = function() {
    return $http.get(dapContextRoot + '/admin/misc/languages/json').success(function(data) {
      $scope.languages = data;
      $scope.resetNewTranslations();
    });
  };

  // Are the languages there ?
  $scope.checkLanguages = function() {
    return $scope.languages && 0 < $scope.languages.length;
  }

  if (!$scope.checkLanguages()) {
    $scope.loadLanguages();
  }

  // Get a language by its code
  $scope.getLanguageByCode = function(languageCode) {
    var filteredLanguages = $filter('filter')($scope.languages, {
      code : languageCode
    });
    var theLanguage = filteredLanguages && 0 < filteredLanguages.length ? filteredLanguages[0] : null;
    return theLanguage;
  }

  // Get a translation value for an indicator type and a language code
  $scope.getTranslationForIndicatorTypeAndLanguageCode = function(indicatorType, languageCode) {
    var filteredTranslations = $filter('filter')(indicatorType.translations, {
      code : languageCode
    });
    var theTranslation = filteredTranslations && 0 < filteredTranslations.length ? filteredTranslations[0] : null;
    return theTranslation ? theTranslation.value : null;
  }

  // Do we have to show the "Add translation" form ? Only if there are some translations missing
  $scope.showAddTranslation = function(indicatorTypeId) {

    // Are there some languages ?
    if (!$scope.checkLanguages()) {
      return false;
    }

    // Get the indicatorType
    var theIndicatorType = $scope.getIndicatorTypeById(indicatorTypeId);
    return theIndicatorType && theIndicatorType.translations && theIndicatorType.translations.length < $scope.languages.length;
  };

  // Show only the languages for which some translations are missing
  $scope.languagesByAvailableTranslations = function(indicatorTypeId, index) {
    return function(language) {
      var translation = $scope.getTranslationForIndicatorTypeAndLanguageCode($scope.getIndicatorTypeById(indicatorTypeId), language.code);

      // At the same time, select by default the first missing language
      if (!translation) {
        if (!$scope.newtranslation[index]) {
          $scope.newtranslation[index] = {};
          $scope.newtranslation[index].value = "";
        }
        if (!$scope.newtranslation[index].language || (!$scope.newtranslation[index].language.code)) {
          $scope.newtranslation[index].language = language;
        }
      }
      return null === translation;
    }
  };

  // add translation
  // - the new translation
  $scope.newtranslation;

  // - reset it
  $scope.resetNewTranslations = function() {
    if (!$scope.newtranslation) {
      $scope.newtranslation = [];
    }
    for (i = 0; i < $scope.indicatorTypes.length; i++) {
      $scope.newtranslation[i] = {};
      $scope.newtranslation[i].value = "";
    }
    $scope.resetAddTranslationForms();
  };

  // - reset its form
  $scope.resetAddTranslationForms = function() {
    for ( var theForm in $scope.t_rowform) {
      theForm.$setPristine();
    }
  };

  // - add it
  $scope.addTranslation = function(indicatorType_id, text_id, index) {
    var data = $scope.newtranslation[index];
    var valid = $scope.checkTranslation(data);
    if ("OK" === valid) {
      return $http.post(dapContextRoot + '/admin/translations/submitadd',
          "textId=" + text_id + "&languageCode=" + data.language.code + "&translationValue=" + data.value, {
            headers : {
              'Content-Type' : 'application/x-www-form-urlencoded'
            }
          }).success(function(data, status, headers, config) {
        // this callback will be called asynchronously
        // when the response is available
        // alert("Translation added !");
        $scope.resetNewTranslations();
        $scope.resetAddTranslationForms();

        // TODO We could improve this with : $scope.loadTranslations(indicatorType_id);
        $scope.loadIndicatorTypes();

      }).error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        // alert("IndicatorType addition threw error : \r\n" + data);
        alert("Translation addition threw an error. Maybe this translation already exists. No translation has been created.");
      });
    } else {
      alert("Form not valid ! \r\n" + valid);
    }
  };

  // - check that the new translation is complete
  $scope.checkTranslation = function(data) {
    var value = data.value;
    if ('' === value || null === value) {
      return "Translation cannot be empty.";
    }
    return "OK";
  };

  // Save (update) a translation
  $scope.saveTranslation = function(data, text_id, language_code) {
    // alert(data + ", " + id)
    var valid = $scope.checkUpdateTranslation(data);
    if ("OK" === valid) {

      return $http.post(dapContextRoot + '/admin/translations/submitupdate',
          "textId=" + text_id + "&languageCode=" + language_code + "&translationValue=" + data.value, {
            headers : {
              'Content-Type' : 'application/x-www-form-urlencoded'
            }
          }).success(function(data, status, headers, config) {
        // this callback will be called asynchronously
        // when the response is available

        // TODO We could improve this with : $scope.loadTranslations(indicatorType_id);
        $scope.loadIndicatorTypes();
      }).error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        alert("Translation update threw an error. No translation has been updated.");

        // TODO We could improve this with : $scope.loadTranslations(indicatorType_id);
        $scope.loadIndicatorTypes();
      });
    } else {
      alert("Form not valid ! \r\n" + valid);
      return "";
    }
  };

  // - check that the new translation is complete
  $scope.checkUpdateTranslation = function(data) {
    var value = data.value;
    if ('' === value || null === value) {
      return "Translation cannot be empty.";
    }
    return "OK";
  };
  
  // Remove a translation
  $scope.removeTranslation = function(text_id, language_code) {
    if (!confirm("Do you really want to delete this translation ?")) {
      return;
    }

    $http.post(dapContextRoot + '/admin/translations/submitdelete', "textId=" + text_id + "&languageCode=" + language_code, {
      headers : {
        'Content-Type' : 'application/x-www-form-urlencoded'
      }
    }).success(function(data, status, headers, config) {
      // this callback will be called asynchronously
      // when the response is available
      // alert("Language deleted !");

      // TODO We could improve this with : $scope.loadTranslations(indicatorType_id);
      $scope.loadIndicatorTypes();
    }).error(function(data, status, headers, config) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
      // alert("Language " + id + " deletion threw error : \r\n" + data);
      alert("Translation deletion threw an error. No translation has been deleted.");
    });
  };
});