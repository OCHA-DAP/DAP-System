var app = angular.module("app", [ "xeditable" ]);

app.run(function(editableOptions) {
  editableOptions.theme = 'bs2'; // Theme : can be 'bs3, 'bs2' or 'default'
});

app.controller('SourcesCtrl', function($scope, $filter, $http) {

  // Sort management
  $scope.predicate = 'code';
  $scope.t_predicate = 'code';

  // ////////////////////
  // Sources management
  // ////////////////////

  $scope.sources = [];

  // Load sources
  $scope.loadSources = function() {
    return $http.get(dapContextRoot + '/admin/curated/sources/json').success(function(data) {
      $scope.sources = data;
      $scope.resetNewTranslations();
    });
  };

  if (!$scope.sources.length) {
    $scope.loadSources();
  }

  // Save (update) a source
  $scope.saveSource = function(data, id) {
    var valid = $scope.checkUpdateForm(data);
    if ("OK" == valid) {

      return $http.post(dapContextRoot + '/admin/curated/sources/submitupdate', "sourceId=" + id + "&newName=" + data.name, {
        headers : {
          'Content-Type' : 'application/x-www-form-urlencoded'
        }
      }).success(function(data, status, headers, config) {
        // this callback will be called asynchronously
        // when the response is available
        $scope.loadSources();
      }).error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        alert("Source update threw an error. No source has been updated.");
        $scope.loadSources();
      });
    } else {
      alert("Form not valid ! \r\n" + valid);
      return "";
    }
  };

  // - check that the updated source is valid
  $scope.checkUpdateForm = function(data) {
    var name = data.name;
    if ('' == name || null == name) {
      return "Name cannot be empty.";
    }
    return "OK";
  };

  // Remove a source
  $scope.removeSource = function(id) {
    if (!confirm("Do you really want to delete this source ?")) {
      return;
    }
    $http.post(dapContextRoot + '/admin/curated/sources/submitdelete', "sourceId=" + id, {
      headers : {
        'Content-Type' : 'application/x-www-form-urlencoded'
      }
    }).success(function(data, status, headers, config) {
      // this callback will be called asynchronously
      // when the response is available
      $scope.loadSources();
    }).error(function(data, status, headers, config) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
      alert("Source deletion threw an error. Maybe this source is used by some indicator. No source has been deleted.");
    });
  };

  // add source
  // - the new source
  $scope.newsource;

  // - reset it
  $scope.resetNewSource = function() {
    if (!$scope.newsource) {
      $scope.newsource = {};
    }
    $scope.newsource.code = "";
    $scope.newsource.name = "";

  };

  // - reset its form
  $scope.resetAddSourceForm = function() {
    $scope.addSourceForm.$setPristine();
  };

  // - add it
  $scope.addSource = function(data) {
    var valid = $scope.checkForm(data);
    if ("OK" == valid) {
      // alert("Add source : " + data);
      return $http.post(dapContextRoot + '/admin/curated/sources/submitadd', "code=" + data.code + "&name=" + data.name, {
        headers : {
          'Content-Type' : 'application/x-www-form-urlencoded'
        }
      }).success(function(data, status, headers, config) {
        // this callback will be called asynchronously
        // when the response is available
        // alert("Source added !");
        $scope.resetNewSource();
        $scope.resetAddSourceForm();
        $scope.loadSources();
      }).error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        // alert("Source addition threw error : \r\n" + data);
        alert("Source addition threw an error. Maybe this source already exists. No source has been created.");
      });
    } else {
      alert("Form not valid ! \r\n" + valid);
    }
  };

  // - check that the new source is complete
  $scope.checkForm = function(data) {
    var code = data.code;
    if ('' == code || null == code) {
      return "Code cannot be empty.";
    }
    var name = data.name;
    if ('' == name || null == name) {
      return "Name cannot be empty.";
    }
    return "OK";
  };

  // Get a source by its id
  $scope.getSourceById = function(sourceId) {
    var filteredSources = $filter('filter')($scope.sources, {
      id : sourceId
    });
    var theSource = filteredSources && 0 < filteredSources.length ? filteredSources[0] : null;
    return theSource;
  }

  // ////////////////////////
  // Translations management
  // ////////////////////////

  $scope.languages = [];

  // Load languages
  $scope.loadLanguages = function() {
    return $http.get(dapContextRoot + '/admin/languages/json').success(function(data) {
      $scope.languages = data;
      $scope.resetNewTranslations();
    });
  };

  if (!$scope.checkLanguages) {
    $scope.loadLanguages();
  }

  // Are the languages there ?
  $scope.checkLanguages = function() {
    return !$scope.languages || 0 == $scope.languages.length;
  }

  // Get a language by its code
  $scope.getLanguageByCode = function(languageCode) {
    var filteredLanguages = $filter('filter')($scope.languages, {
      code : languageCode
    });
    var theLanguage = filteredLanguages && 0 < filteredLanguages.length ? filteredLanguages[0] : null;
    return theLanguage;
  }

  // Get a translation value for a source and a language code
  $scope.getTranslationForSourceAndLanguageCode = function(source, languageCode) {
    var filteredTranslations = $filter('filter')(source.translations, {
      code : languageCode
    });
    var theTranslation = filteredTranslations && 0 < filteredTranslations.length ? filteredTranslations[0] : null;
    return theTranslation ? theTranslation.value : null;
  }

  // Do we have to show the "Add translation" form ? Only if there are some translations missing
  $scope.showAddTranslation = function(sourceId) {

    // Are there some languages ?
    if (!$scope.checkLanguages) {
      return false;
    }

    // Get the source
    var theSource = $scope.getSourceById(sourceId);
    return theSource && theSource.translations && theSource.translations.length < $scope.languages.length;
  };

  // Show only the languages for which some translations are missing
  $scope.languagesByAvailableTranslations = function(sourceId, index) {
    return function(language) {
      var translation = $scope.getTranslationForSourceAndLanguageCode($scope.getSourceById(sourceId), language.code);

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
      return null == translation;
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
    for (i = 0; i < $scope.sources.length; i++) {
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
  $scope.addTranslation = function(source_id, text_id, index) {
    var data = $scope.newtranslation[index];
    var valid = $scope.checkTranslation(data);
    if ("OK" == valid) {
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

        // TODO We could improve this with : $scope.loadTranslations(source_id);
        $scope.loadSources();

      }).error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        // alert("Source addition threw error : \r\n" + data);
        alert("Translation addition threw an error. Maybe this translation already exists. No translation has been created.");
      });
    } else {
      alert("Form not valid ! \r\n" + valid);
    }
  };

  // - check that the new translation is complete
  $scope.checkTranslation = function(data) {
    var value = data.value;
    if ('' == value || null == value) {
      return "Translation cannot be empty.";
    }
    return "OK";
  };

  // Save (update) a translation
  $scope.saveTranslation = function(data, text_id, language_code) {
    // alert(data + ", " + id)
    var valid = $scope.checkUpdateTranslation(data);
    if ("OK" == valid) {

      return $http.post(dapContextRoot + '/admin/translations/submitupdate',
          "textId=" + text_id + "&languageCode=" + language_code + "&translationValue=" + data.value, {
            headers : {
              'Content-Type' : 'application/x-www-form-urlencoded'
            }
          }).success(function(data, status, headers, config) {
        // this callback will be called asynchronously
        // when the response is available

        // TODO We could improve this with : $scope.loadTranslations(source_id);
        $scope.loadSources();
      }).error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        alert("Translation update threw an error. No translation has been updated.");

        // TODO We could improve this with : $scope.loadTranslations(source_id);
        $scope.loadSources();
      });
    } else {
      alert("Form not valid ! \r\n" + valid);
      return "";
    }
  };

  // - check that the new translation is complete
  $scope.checkUpdateTranslation = function(data) {
    var value = data.value;
    if ('' == value || null == value) {
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

      // TODO We could improve this with : $scope.loadTranslations(source_id);
      $scope.loadSources();
    }).error(function(data, status, headers, config) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
      // alert("Language " + id + " deletion threw error : \r\n" + data);
      alert("Translation deletion threw an error. No translation has been deleted.");
    });
  };
});