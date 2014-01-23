var app = angular.module("app", [ "xeditable" ]);

app.run(function(editableOptions) {
  editableOptions.theme = 'bs2'; // Theme : can be 'bs3, 'bs2' or 'default'
});

app.controller('UsersCtrl', function($scope, $filter, $http) {

  // Sort management
  $scope.predicate = 'id';
  
  // ////////////////////
  // Users management
  // ////////////////////

  $scope.users = [];
  
  // Allow to find a user according to its id
  $scope.findUser = function(user_id) {
    return $filter('filter')($scope.users, {
      id : user_id
    }, true);
  }
  
  // Load users
  $scope.loadUsers = function() {
    return $http.get(dapContextRoot + '/admin/users/json').success(function(data) {
      $scope.users = data;
      angular.forEach($scope.users, function(value, key) {
        angular.extend(value, {password:'', password2:''});
        console.log(key + ': ' + value);
        // Value is a user
        value.setEditing = function(state) {
          this.isEditing = state;
        }
      });
    });
  };

  if (!$scope.users.length) {
    $scope.loadUsers();
  }

  ///////////////////
  // Roles management
  ///////////////////
  
  $scope.roles = [];

  // Load roles
  $scope.loadRoles = function() {
    return $http.get(dapContextRoot + '/admin/users/roles/json').success(function(data) {
      $scope.roles = data;
      $scope.resetNewUser();
    });
  };

  if (!$scope.roles.length) {
    $scope.loadRoles();
  }

  // Show a user's role
  $scope.showRole = function(user) {
    var selected = $filter('filter')($scope.roles, {
      value : user.role
    });
    return (user.role && selected.length) ? selected[0].text : 'Not set';
  };
  
  // Save (update) a user
  $scope.saveUser = function(datas, id) {
    var valid = $scope.checkUpdateForm(datas);
    if ("OK" == valid) {
      $http.post(
          dapContextRoot + '/admin/users/submitupdate',
          "userId=" + id + (datas.password ? "&newPassword=" + datas.password : "") + (datas.password2 ? "&newPassword2=" + datas.password2 : "") + (datas.ckanApiKey ? "&newCkanApiKey=" + datas.ckanApiKey : "")
              + "&newRole=" + datas.role, {
            headers : {
              'Content-Type' : 'application/x-www-form-urlencoded'
            }
          }).success(function(data, status, headers, config) {
        // this callback will be called asynchronously
        // when the response is available
        $scope.findUser(id)[0].setEditing(false);
        $scope.loadUsers();
      }).error(function(data, status, headers, config) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        alert("User update threw an error. No user has been updated.");
      });
    } else {
      alert("Form not valid ! \r\n" + valid);
    }
  };
  
  // - check that the updated user is valid
  $scope.checkUpdateForm = function(data) {
    var password = data.password;
    var password2 = data.password2;
    if (password != password2) {
      return "Password and confirmation must match.";
    }
    return "OK";
  };

  // Remove a user
  $scope.removeUser = function(id) {
    if (!confirm("Do you really want to delete this user ?")) {
      return;
    }

    $http.post(dapContextRoot + '/admin/users/submitdelete', "userId=" + id, {
      headers : {
        'Content-Type' : 'application/x-www-form-urlencoded'
      }
    }).success(function(data, status, headers, config) {
      // this callback will be called asynchronously
      // when the response is available
      // alert("User deleted !");
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
    $scope.newuser.role = $scope.roles[0].value;
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
      return $http.post(
          dapContextRoot + '/admin/users/submitadd',
          "userId=" + data.id + "&newPassword=" + data.password + "&newPassword2=" + data.password2 + "&newCkanApiKey=" + data.ckanApiKey
              + "&newRole=" + data.role, {
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
    if (!data) {
      return "At least some info should be provided.";
    }
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