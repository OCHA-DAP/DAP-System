app.controller('UsersCtrl', function($scope, $filter, $http, $rootScope, utilities) {

  // Sort management
  $scope.predicate = 'id';

  // ////////////////////
  // Users management
  // ////////////////////

  // Load users
  // ==========
  $scope.loadUsers = function() {
    return utilities.loadResource($scope, 'users', '/admin/misc/users/json', function() {
      angular.forEach($scope.users, function(value, key) {
        angular.extend(value, {
          password : '',
          password2 : ''
        });
        // Value is a user
        value.setEditing = function(state) {
          this.isEditing = state;
        }
      });
    });
  };

  /*
  $scope.loadUsers = function() {
    return $http.get(dapContextRoot + '/admin/misc/users/json').success(function(data, status, headers, config) {
      $scope.resource = data;
      $scope.users = data;
      angular.forEach($scope.users, function(value, key) {
        angular.extend(value, {
          password : '',
          password2 : ''
        });
        // Value is a user
        value.setEditing = function(state) {
          this.isEditing = state;
        }
      });
    }).error(function(data, status, headers, config) {
    });
  };
  */
  $scope.loadUsers();

  // Create a user
  // =============

  $scope.createUser = function(data) {
    var id = data ? data.id : "";
    var password = data ? data.password : "";
    var password2 = data ? data.password2 : "";
    var ckanApiKey = data ? data.ckanApiKey : "";
    var role = data ? data.role : "";
    return utilities.createResource({
      validate : $scope.checkCreateForm,
      data : data,
      params : {
        "userId" : id,
        "newPassword" : password,
        "newPassword2" : password2,
        "newCkanApiKey" : ckanApiKey,
        "newRole" : role
      },
      url : '/admin/misc/users/submitCreate',
      successCallback : function() {
        $scope.resetNewUser();
        $scope.resetCreateResourceForm();
        $scope.loadUsers();
      },
      errorCallback : function() {
        alert("User creation threw an error. Maybe this user already exists. No user has been created.");
      }
    });
  }

  // Check that the new user is complete
  $scope.checkCreateForm = function(data) {
    if (!data) {
      utilities.setFocus('newResource_id');
      return "At least some info should be provided.";
    }
    var id = data.id;
    if (!id || null === id || '' === id) {
      utilities.setFocus('newResource_id');
      return "Id cannot be empty.";
    }
    var password = data.password;
    if (!password || null === password || '' === password) {
      utilities.setFocus('newResource_password');
      return "Password cannot be empty.";
    }
    var password2 = data.password2;
    if (!password2 || null === password2 || '' === password2) {
      utilities.setFocus('newResource_password2');
      return "Password confirmation cannot be empty.";
    }
    if (password != password2) {
      utilities.setFocus('newResource_password');
      return "Password and confirmation must match.";
    }
    return "OK";
  };

  // Reset the new user
  $scope.resetNewUser = function() {
    if (!$scope.newResource) {
      $scope.newResource = {};
    }
    $scope.newResource.id = "";
    $scope.newResource.password = "";
    $scope.newResource.password2 = "";
    $scope.newResource.ckanApiKey = "";
    $scope.newResource.role = $scope.roles[0].value;
  };

  // Reset the create resource form
  $scope.resetCreateResourceForm = function() {
    $scope.createResourceForm.$setPristine();
  };

  // Update a user
  // =============
  $scope.updateUser = function(data, id) {
    var params = {};
    angular.extend(params, {
      "userId" : id
    });
    if (data.password) {
      angular.extend(params, {
        "newPassword" : data.password
      });
    }
    if (data.password2) {
      angular.extend(params, {
        "newPassword2" : data.password2
      });
    }
    if (data.ckanApiKey) {
      angular.extend(params, {
        "newCkanApiKey" : data.ckanApiKey
      });
    }
    if (data.role) {
      angular.extend(params, {
        "newRole" : data.role
      });
    }

    return utilities.updateResource({
      validate : $scope.checkUpdateForm,
      data : data,
      params : params,
      url : '/admin/misc/users/submitUpdate',
      successCallback : function() {
        utilities.findSomething($scope.users, "id", id)[0].setEditing(false);
        $scope.loadUsers();
      },
      errorCallback : function() {
        alert("User update threw an error. No user has been updated.");
        $scope.loadUsers();
      }
    });
  };

  // Check that the updated user is valid
  $scope.checkUpdateForm = function(data) {
    var password = data.password;
    var password2 = data.password2;
    if (password != password2) {
      return "Password and confirmation must match.";
    }
    return "OK";
  };

  // Delete a user
  // =================
  $scope.deleteUser = function(id) {
    return utilities.deleteResource({
      params : {
        "userId" : id
      },
      url : '/admin/misc/users/submitDelete',
      successCallback : $scope.loadUsers,
      errorCallback : function() {
        alert("User deletion threw an error. No user has been deleted.");
      }
    });
  };

  // Hide password
  $scope.customize = function(fieldId) {
    var aField = document.getElementById(fieldId);
    aField.type = 'password';
  }

  // /////////////////
  // Roles management
  // /////////////////

  // Load roles
  $scope.loadRoles = function() {
    return utilities.loadResource($scope, 'roles', '/admin/misc/users/roles/json', function() {
      $scope.resetNewUser();
    });
  };
  /*
  $scope.loadRoles = function() {
    return $http.get(dapContextRoot + '/admin/misc/users/roles/json').success(function(data, status, headers, config) {
      $scope.resource = data;
      $scope.roles = data;
    }).error(function(data, status, headers, config) {
    });
  };
  */
  $scope.loadRoles();

  // Show a user's role
  $scope.showRole = function(user) {
    var selected = $filter('filter')($scope.roles, {
      value : user.role
    });
    return (user.role && selected.length) ? selected[0].text : 'Not set';
  };
});