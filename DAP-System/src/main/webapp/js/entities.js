var app = angular.module("app", [ "xeditable" ]);

app.run(function(editableOptions) {
  editableOptions.theme = 'bs2'; // Theme : can be 'bs3, 'bs2' or 'default'
});

app.controller('EntitiesCtrl', function($scope, $filter, $http) {

  // Remove from the array <array> the property <property> which has the value <value>
  function remove(array, property, value) {
    angular.forEach(array, function(result, index) {
      var res = result[property];
      if (res == value) {
        array.splice(index, 1);
      }
    });
  }

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
    });
  };

  if (!$scope.entities.length) {
    $scope.loadEntities();
  }

  // Save (update) an entity
  $scope.saveEntity = function(data, id) {
    return $http.post(dapContextRoot + '/admin/curated/entities/submitupdate', "entityId=" + id + "&newName=" + data.name, {
      headers : {
        'Content-Type' : 'application/x-www-form-urlencoded'
      }
    });
  };

  // Remove an entity
  $scope.removeEntity = function(id) {
    if(!confirm("Do you really want to delete this entity ?")) {
      return;
    };
    $http.post(dapContextRoot + '/admin/curated/entities/submitdelete', "entityId=" + id, {
      headers : {
        'Content-Type' : 'application/x-www-form-urlencoded'
      }
    }).success(function(data, status, headers, config) {
      // this callback will be called asynchronously
      // when the response is available
      // alert("Entity  deleted !");
      $scope.loadEntities();
    }).error(function(data, status, headers, config) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
      // alert("Entity " + id + " deletion threw error : \r\n" + data);
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
});