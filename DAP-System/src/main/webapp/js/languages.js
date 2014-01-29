var app = angular.module("app", [ "xeditable" ]);

app.run(function(editableOptions) {
  editableOptions.theme = 'bs2'; // Theme : can be 'bs3, 'bs2' or 'default'
});

app.controller('LanguagesCtrl', function($scope, $filter, $http) {

  // Sort management
  $scope.predicate = 'code';

  // ////////////////////
  // Languages management
  // ////////////////////

  $scope.languages = [];

  // Allow to find a language according to its code
  $scope.findLanguage = function(language_code) {
    return $filter('filter')($scope.languages, {
      code : language_code
    }, true);
  }

  // Load languages
  $scope.loadLanguages = function() {
    return $http.get(dapContextRoot + '/admin/misc/languages/json').success(function(data) {
      $scope.languages = data;
    });
  };

  if (!$scope.languages.length) {
    $scope.loadLanguages();
  }

  // Save (update) a language
  $scope.saveLanguage = function(datas, code) {
    var valid = $scope.checkUpdateForm(datas);
    if ("OK" == valid) {
      return $http.post(dapContextRoot + '/admin/misc/languages/submitupdate', "code=" + code + "&newNativeName=" + datas.native_name, {
        headers : {
          'Content-Type' : 'application/x-www-form-urlencoded'
        }
      }).success(function(data, status, headers, config) {
        // this callback will be called asynchronously
        // when the response is available
        $scope.loadLanguages();
      }).error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        alert("Language update threw an error. No language has been updated.");
        $scope.loadLanguages();
      });
    } else {
      alert("Form not valid ! \r\n" + valid);
      return "";
    }
  };

  // - check that the updated language is valid
  $scope.checkUpdateForm = function(data) {
    var nativeName = data.native_name;
    if ('' == nativeName || null == nativeName) {
      return "Native name cannot be empty.";
    }
    return "OK";
  };

  // Remove a language
  $scope.removeLanguage = function(code) {
    if (!confirm("Do you really want to delete this language ?")) {
      return;
    }

    $http.post(dapContextRoot + '/admin/misc/languages/submitdelete', "code=" + code, {
      headers : {
        'Content-Type' : 'application/x-www-form-urlencoded'
      }
    }).success(function(data, status, headers, config) {
      // this callback will be called asynchronously
      // when the response is available
      // alert("Language deleted !");
      $scope.loadLanguages();
    }).error(function(data, status, headers, config) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
      // alert("Language " + id + " deletion threw error : \r\n" + data);
      alert("Language deletion threw an error. Maybe this language is used in some translation. No language has been deleted.");
    });
  };

  // add language
  // - the new language
  $scope.newlanguage;

  // - reset it
  $scope.resetNewLanguage = function() {
    if (!$scope.newlanguage) {
      $scope.newlanguage = {};
    }
    $scope.newlanguage.code = "";
    $scope.newlanguage.native_name = "";
  };

  // - reset its form
  $scope.resetAddLanguageForm = function() {
    $scope.addLanguageForm.$setPristine();
  };

  // - add it
  $scope.addLanguage = function(data) {
    var valid = $scope.checkForm(data);
    if ("OK" == valid) {
      return $http.post(dapContextRoot + '/admin/misc/languages/submitadd', "code=" + data.code + "&newNativeName=" + data.native_name,
          {
            headers : {
              'Content-Type' : 'application/x-www-form-urlencoded'
            }
          }).success(function(data, status, headers, config) {
        // this callback will be called asynchronously
        // when the response is available
        // alert("Language added !");
        $scope.resetNewLanguage();
        $scope.resetAddLanguageForm();
        $scope.loadLanguages();
      }).error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        // alert("Language addition threw error : \r\n" + data);
        alert("Language addition threw an error. Maybe this language already exists. No language has been created.");
      });
    } else {
      alert("Form not valid ! \r\n" + valid);
    }
  };

  // - check that the new language is complete
  $scope.checkForm = function(data) {
    if (!data) {
      // angular.element( document.querySelector('#newlanguage_code')).focus();
      document.getElementById('newlanguage_code').focus();
      return "At least some info should be provided.";
    }
    var code = data.code;
    if ('' == code || null == code) {
      document.getElementById('newlanguage_code').focus();
      return "Code cannot be empty.";
    }
    var nativeName = data.native_name;
    if ('' == nativeName || null == nativeName) {
      document.getElementById('newlanguage_native_name').focus();
      return "Native name cannot be empty.";
    }
    return "OK";
  };
});