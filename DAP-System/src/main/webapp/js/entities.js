var app = angular.module("app", [ "xeditable" ]);

app.run(function(editableOptions) {
  editableOptions.theme = 'bs2'; // Theme : can be 'bs3, 'bs2' or 'default'
});

app.controller('EntitiesCtrl', function($scope, $filter, $http) {

  // Sort management
  $scope.predicate = 'type';
  $scope.t_predicate = 'code';

  // /////////////////
  // Types management
  // /////////////////

  $scope.types = [];

  // Load types
  $scope.loadTypes = function() {
    return $http.get(dapContextRoot + '/admin/curated/entitytypes/json').success(function(data) {
      $scope.types = data;
      $scope.resetNewEntity();
    });
  };

  if (!$scope.types.length) {
    $scope.loadTypes();
  }

  // Show a type for a given entity
  $scope.showType = function(entity) {
    if (entity.type) {
      var selected = $filter('filter')($scope.types, {
        id : entity.type
      });
      return selected.length ? selected[0].name : 'Not set';
    } else {
      return entity.type || 'Not set';
    }
  };

  // ////////////////////
  // Entities management
  // ////////////////////

  $scope.entities = [];

  // Load entities
  $scope.loadEntities = function() {
    return $http.get(dapContextRoot + '/admin/curated/entities/json').success(function(data) {
      $scope.entities = data;
      $scope.resetNewTranslations();
    });
  };

  if (!$scope.entities.length) {
    $scope.loadEntities();
  }

  // Save (update) an entity
  $scope.saveEntity = function(data, id) {
    var valid = $scope.checkUpdateForm(data);
    if ("OK" == valid) {

      return $http.post(dapContextRoot + '/admin/curated/entities/submitupdate', "entityId=" + id + "&newName=" + data.name, {
        headers : {
          'Content-Type' : 'application/x-www-form-urlencoded'
        }
      }).success(function(data, status, headers, config) {
        // this callback will be called asynchronously
        // when the response is available
        $scope.loadEntities();
      }).error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        alert("Entity update threw an error. No entity has been updated.");
        $scope.loadEntities();
      });
    } else {
      alert("Form not valid ! \r\n" + valid);
      return "";
    }
  };

  // - check that the updated entity is valid
  $scope.checkUpdateForm = function(data) {
    var name = data.name;
    if ('' == name || null == name) {
      return "Name cannot be empty.";
    }
    return "OK";
  };

  // Remove an entity
  $scope.removeEntity = function(id) {
    if (!confirm("Do you really want to delete this entity ?")) {
      return;
    }
    $http.post(dapContextRoot + '/admin/curated/entities/submitdelete', "entityId=" + id, {
      headers : {
        'Content-Type' : 'application/x-www-form-urlencoded'
      }
    }).success(function(data, status, headers, config) {
      // this callback will be called asynchronously
      // when the response is available
      $scope.loadEntities();
    }).error(function(data, status, headers, config) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
      alert("Entity deletion threw an error. Maybe this entity is used by some indicator. No entity has been deleted.");
    });
  };

  // add entity
  // - the new entity
  $scope.newentity;

  // - reset it
  $scope.resetNewEntity = function() {
    if (!$scope.newentity) {
      $scope.newentity = {};
    }
    $scope.newentity.type = $scope.types[0];
    $scope.newentity.code = "";
    $scope.newentity.name = "";

  };

  // - reset its form
  $scope.resetAddEntityForm = function() {
    $scope.addEntityForm.$setPristine();
  };

  // - add it
  $scope.addEntity = function(data) {
    var valid = $scope.checkForm(data);
    if ("OK" == valid) {
      // alert("Add entity : " + data);
      return $http.post(dapContextRoot + '/admin/curated/entities/submitadd',
          "entityTypeCode=" + data.type.code + "&code=" + data.code + "&name=" + data.name, {
            headers : {
              'Content-Type' : 'application/x-www-form-urlencoded'
            }
          }).success(function(data, status, headers, config) {
        // this callback will be called asynchronously
        // when the response is available
        // alert("Entity added !");
        $scope.resetNewEntity();
        $scope.resetAddEntityForm();
        $scope.loadEntities();
      }).error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        // alert("Entity addition threw error : \r\n" + data);
        alert("Entity addition threw an error. Maybe this entity already exists. No entity has been created.");
      });
    } else {
      alert("Form not valid ! \r\n" + valid);
    }
  };

  // - check that the new entity is complete
  $scope.checkForm = function(data) {
    var code = data.code;
    if ('' == code || null == code) {
      return "Code cannot be empty.";
    }
    var name = data.name;
    if ('' == name || null == name) {
      return "Name cannot be empty.";
    }
    var type = data.type;
    if (null == type) {
      return "Type cannot be empty.";
    }
    return "OK";
  };

  // Get an entity by its id
  $scope.getEntityById = function(entityId) {
    var filteredEntities = $filter('filter')($scope.entities, {
      id : entityId
    });
    var theEntity = filteredEntities && 0 < filteredEntities.length ? filteredEntities[0] : null;
    return theEntity;
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

  // Get a translation value for an entity and a language code
  $scope.getTranslationForEntityAndLanguageCode = function(entity, languageCode) {
    var filteredTranslations = $filter('filter')(entity.translations, {
      code : languageCode
    });
    var theTranslation = filteredTranslations && 0 < filteredTranslations.length ? filteredTranslations[0] : null;
    return theTranslation ? theTranslation.value : null;
  }
  
  // Do we have to show the "Add translation" form ? Only if there are some translations missing
  $scope.showAddTranslation = function(entityId) {

    // Are there some languages ?
    if (!$scope.checkLanguages()) {
      return false;
    }

    // Get the entity
    var theEntity = $scope.getEntityById(entityId);
    return theEntity && theEntity.translations && theEntity.translations.length < $scope.languages.length;
  };

  // Show only the languages for which some translations are missing
  $scope.languagesByAvailableTranslations = function(entityId, index) {
    return function(language) {
      var translation = $scope.getTranslationForEntityAndLanguageCode($scope.getEntityById(entityId), language.code);

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
    for (i = 0; i < $scope.entities.length; i++) {
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
  $scope.addTranslation = function(entity_id, text_id, index) {
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

        // TODO We could improve this with : $scope.loadTranslations(entity_id);
        $scope.loadEntities();

      }).error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        // alert("Entity addition threw error : \r\n" + data);
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

        // TODO We could improve this with : $scope.loadTranslations(entity_id);
        $scope.loadEntities();
      }).error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        alert("Translation update threw an error. No translation has been updated.");

        // TODO We could improve this with : $scope.loadTranslations(entity_id);
        $scope.loadEntities();
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

      // TODO We could improve this with : $scope.loadTranslations(entity_id);
      $scope.loadEntities();
    }).error(function(data, status, headers, config) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
      // alert("Language " + id + " deletion threw error : \r\n" + data);
      alert("Translation deletion threw an error. No translation has been deleted.");
    });
  };
});