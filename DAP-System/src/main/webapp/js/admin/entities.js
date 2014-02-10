app.controller('EntitiesCtrl', function($scope, $filter, utilities) {

  // /////////////////
  // Types management
  // /////////////////

  // Sort management
  $scope.predicate = 'type';

  // Load types
  $scope.loadTypes = function() {
    return utilities.loadResource($scope, 'types', '/admin/curated/entityTypes/json', function() {
      // $scope.resetNewTranslations();
    });
  };
  $scope.loadTypes();

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

  // Load entities
  $scope.loadEntities = function() {
    return utilities.loadResource($scope, 'entities', '/admin/curated/entities/json', function() {
    });
  };
	$scope.loadEntities();

  // Create an entity
  // ================
  $scope.createEntity = function(data) {
    var params = {};
    if (data && data.type && data.type.code) {
      angular.extend(params, {
        "entityTypeCode" : data.type.code
      });
    }
    if (data && data.code) {
      angular.extend(params, {
        "code" : data.code
      });
    }
    if (data && data.name) {
      angular.extend(params, {
        "name" : data.name
      });
    }
    return utilities.createResource({
      validate : $scope.checkCreateForm,
      data : data,
      params : params,
      url : '/admin/curated/entities/submitCreate',
      successCallback : function() {
        $scope.resetNewEntity();
        $scope.resetCreateResourceForm();
        $scope.loadEntities();
      },
      errorCallback : function() {
        alert("Entity creation threw an error. Maybe this entity already exists. No entity has been created.");
      }
    });
  };

  // Check that the new entity is complete
  $scope.checkCreateForm = function(data) {
    if (!data) {
      utilities.setFocus('newResource_type');
      return "At least some info should be provided.";
    }
    var code = data.code;
    if (!code || null === code || '' === code) {
      utilities.setFocus('newResource_code');
      return "Code cannot be empty.";
    }
    var name = data.name;
    if (!name || null === name || '' === name) {
      utilities.setFocus('newResource_name');
      return "Name cannot be empty.";
    }
    var type = data.type;
    if (null === type) {
      return "Type cannot be empty.";
    }
    return "OK";
  };

  // Reset the new entity
  $scope.resetNewEntity = function() {
    if (!$scope.newResource) {
      $scope.newResource = {};
    }
    $scope.newResource.type = null;
    $scope.newResource.code = "";
    $scope.newResource.name = "";
  };

  // Reset the create resource form
  $scope.resetCreateResourceForm = function() {
    $scope.createResourceForm.$setPristine();
  };

  // Update an entity
  // ================
  $scope.updateEntity = function(data, id) {
    var params = {};
    angular.extend(params, {
      "entityId" : id
    });
    angular.extend(params, {
      "newName" : data.name
    });

    return utilities.updateResource({
      validate : $scope.checkUpdateForm,
      data : data,
      params : params,
      url : '/admin/curated/entities/submitUpdate',
      successCallback : function() {
        $scope.loadEntities();
      },
      errorCallback : function() {
        alert("Entity update threw an error. No entity has been updated.");
        $scope.loadEntities();
      }
    });
  };

  // Check that the updated entity is valid
  $scope.checkUpdateForm = function(data) {
    var name = data.name;
    if (!name || null === name || '' === name) {
      return "Name cannot be empty.";
    }
    return "OK";
  };

  // Delete a entity
  // ===============
  $scope.deleteEntity = function(id) {
    return utilities.deleteResource({
      params : {
        "entityId" : id
      },
      url : '/admin/curated/entities/submitDelete',
      successCallback : $scope.loadEntities,
      errorCallback : function() {
        alert("Entity deletion threw an error. Maybe this entity is used by some indicator. No entity has been deleted.");
      }
    });
  }
  
  // Get a entity by its id
  // ======================
  $scope.getEntityById = function(entityId) {
    var filteredEntities = $filter('filter')($scope.entities, {
      id : entityId
    });
    var theEntity = filteredEntities && 0 < filteredEntities.length ? filteredEntities[0] : null;
    return theEntity;
  }
});