var app = angular.module("app", [ "xeditable" ]);

app.run(function(editableOptions) {
  editableOptions.theme = 'bs2'; // Theme : can be 'bs3, 'bs2' or 'default'
});

app.controller('UsersCtrl', function($scope, $filter, $http) {

  // Remove from the array <array> the property <property> which has the value <value>
  function remove(array, property, value) {
    angular.forEach(array, function(result, index) {
      var res = result[property];
      if (res == value) {
        array.splice(index, 1);
      }
    });
  }

  // ////////////////////
  // Users management
  // ////////////////////

  $scope.users = [];

  // Load users
  $scope.loadUsers = function() {
    return $http.get(dapContextRoot + '/admin/users/json').success(function(data) {
      $scope.users = data;
      angular.forEach($scope.users, function(value, key){
        console.log(key + ': ' + value);
      });      
    });
  };

  if (!$scope.users.length) {
    $scope.loadUsers();
  }

  // Save (update) a user
  $scope.saveUser = function(data, id) {
    return $http.post(dapContextRoot + '/admin/users/submitupdate', "userId=" + id + "&newPassword=" + data.password + "&newPassword2=" + data.password2 + "&newCkanApiKey=" + data.ckanApiKey + "&newRole=" + data.role, {
      headers : {
        'Content-Type' : 'application/x-www-form-urlencoded'
      }
    });
  };

  // Remove a user
  $scope.removeUser = function(id) {
    if(!confirm("Do you really want to delete this user ?")) {
      return;
    };
    $http.post(dapContextRoot + '/admin/users/submitdelete', "userId=" + id, {
      headers : {
        'Content-Type' : 'application/x-www-form-urlencoded'
      }
    }).success(function(data, status, headers, config) {
      // this callback will be called asynchronously
      // when the response is available
      // alert("User  deleted !");
      $scope.loadUsers();
    }).error(function(data, status, headers, config) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
      // alert("User " + id + " deletion threw error : \r\n" + data);
      alert("User deletion threw an error. No user has been deleted.");
    });
  };

  // add user
  // - the new user
  $scope.newuser;

  // - reset it
  $scope.resetNewUser = function() {
    if (!$scope.newuser) {
      $scope.newuser = {};
    }
    $scope.newuser.id = "";
    $scope.newuser.password = "";
    $scope.newuser.password2 = "";
    $scope.newuser.ckanApiKey = "";
    $scope.newuser.role = "";
  };

  // - reset its form
  $scope.resetAddUserForm = function() {
    $scope.addUserForm.$setPristine();
  };

  // - add it
  $scope.addUser = function(data) {
    var valid = $scope.checkForm(data);
    if ("OK" == valid) {
      // alert("Add user : " + data);
      return $http.post(dapContextRoot + '/admin/users/submitadd',
          "userId=" + id + "&newPassword=" + data.password + "&newPassword2=" + data.password2 + "&newCkanApiKey=" + data.ckanApiKey + "&newRole=" + data.role, {
            headers : {
              'Content-Type' : 'application/x-www-form-urlencoded'
            }
          }).success(function(data, status, headers, config) {
        // this callback will be called asynchronously
        // when the response is available
        // alert("User added !");
        $scope.resetNewUser();
        $scope.resetAddUserForm();
        $scope.loadUsers();
      }).error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        // alert("User addition threw error : \r\n" + data);
        alert("User addition threw an error. Maybe this user already exists. No user has been created.");
      });
    } else {
      alert("Form not valid ! \r\n" + valid);
    }
  };

  // - check that the new user is complete
  $scope.checkForm = function(data) {
    var id = data.id;
    if ('' == id || null == id) {
      return "Id cannot be empty.";
    }
    var password = data.password;
    if ('' == password || null == password) {
      return "Password cannot be empty.";
    }
    var password2 = data.password2;
    if ('' == password2 || null == password2) {
      return "Password confirmation cannot be empty.";
    }
    if (password != password2) {
      return "Password and confirmation must match.";
    }
    return "OK";
  };
});