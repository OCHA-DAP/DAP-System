app.controller('EntitiesCtrl', function($scope, $filter, utilities) {

  // /////////
  // Resources
  // /////////
  $scope.allowNullForEntityCodes = function() {
    if ($scope.entityCodes && $scope.entityCodes[0] && "" !== $scope.entityCodes[0].id) {
      $scope.entityCodes.splice(0, 0, {
        id : "",
        code : ""
      });
    }
  }

  $scope.loadEntityCodes = function() {
    return utilities.loadResource($scope, 'entityCodes', '/admin/curated/entityCodes/json', function() {
      $scope.allowNullForEntityCodes();
    });
  }

  $scope.resources = function() {
    $scope.entityTypes = appData['entityTypes'];
    $scope.loadEntityCodes();
  }
  $scope.resources();

  // The new indicator
  // Reset the new entity
  $scope.resetNewEntity = function() {
    if (!$scope.newResource) {
      $scope.newResource = {};
    }
    $scope.newResource.entityType = null;
    $scope.newResource.code = "";
    $scope.newResource.name = "";
    $scope.newResource.parentId = "";
  };
  $scope.resetNewEntity();

  // ///////////////////////
  // Entity types management
  // ///////////////////////

  // Sort management
  $scope.predicate = 'entityType';

  // Show an entity type for a given entity
  $scope.showEntityType = function(entity) {
    if (entity.entityType) {
      var selected = $filter('filter')($scope.entityTypes, {
        id : entity.entityType
      });
      return selected.length ? selected[0].name : 'Not set';
    } else {
      return entity.entityType || 'Not set';
    }
  };

  // ////////////////////
  // Entities management
  // ////////////////////

  $scope.fromIndex = 0;
  $scope.howMuch = 10;
  $scope.pagination;

  // Load entities
  $scope.loadEntities = function() {
    var fromIndex = $scope.fromIndex;
    var howMuch = $scope.howMuch;
    return utilities.loadPaginatedResource($scope, 'entities', '/admin/curated/entities/paginated/json', fromIndex, howMuch, function() {
    });
  };
  $scope.loadEntities();

  // Pagination
  $scope.paginate = function(numPage) {
    // alert("Calling page " + numPage);
    $scope.fromIndex = (numPage - 1) * $scope.howMuch;
    $scope.loadEntities();
  }
  $scope.updatePagination = function(pagination) {
    if (!$scope.pagination) {
      $scope.pagination = {};
    }
    $scope.pagination.totalNumber = pagination.totalNumber;
    $scope.pagination.currentPage = pagination.currentPage;
    $scope.pagination.maxSize = pagination.maxSize;
    $scope.pagination.nbPages = pagination.nbPages;
  }

  // Show an entity's parent
  $scope.showParent = function(entity) {
    if (entity.parent && entity.parent.id) {
      var selected = utilities.findSomething($scope.entities, "id", entity.parent.id);
      var result = (selected.length) ? selected[0].code : "";
      return result;
    } else
      return "";
  };

  // Filter entity codes
  $scope.filterEntityCodes = function(entityId) {
    return function(entityCode) {
      return entityCode.id !== entityId;
    }
  }

  // Create an entity
  // ================
  $scope.createEntity = function(data) {
    var params = {};
    if (data && data.entityType && data.entityType.code) {
      angular.extend(params, {
        "entityTypeCode" : data.entityType.code
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
    if (data && data.parentId && "" !== data.parentId) {
      angular.extend(params, {
        "parent" : data.parentId
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
        $scope.loadEntityCodes();
      },
      errorCallback : function() {
        alert("Entity creation threw an error. Maybe this entity already exists. No entity has been created.");
      }
    });
  };

  // Check that the new entity is complete
  $scope.checkCreateForm = function(data) {
    if (!data) {
      utilities.setFocus('newResource_entityType');
      return "At least some info should be provided.";
    }
    var entityType = data.entityType;
    if (null === entityType) {
      utilities.setFocus('newResource_entityType');
      return "Entity type cannot be empty.";
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
    return "OK";
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
    angular.extend(params, {
      "parent" : data.parent
    });

    return utilities.updateResource({
      validate : $scope.checkUpdateForm,
      data : data,
      params : params,
      url : '/admin/curated/entities/submitUpdate',
      successCallback : function() {
        $scope.loadEntities();
        $scope.loadEntityCodes();
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

  // Delete an entity
  // ================
  $scope.deleteEntity = function(id) {
    return utilities.deleteResource({
      params : {
        "entityId" : id
      },
      url : '/admin/curated/entities/submitDelete',
      successCallback : function() {
        $scope.loadEntities();
        $scope.loadEntityCodes();
      },
      errorCallback : function() {
        alert("Entity deletion threw an error. Maybe this entity is used by some indicator or is a parent of another entity. No entity has been deleted.");
      }
    });
  }

  // Get an entity by its id
  // =======================
  $scope.getEntityById = function(entityId) {
    var filteredEntities = $filter('filter')($scope.entities, {
      id : entityId
    });
    var theEntity = filteredEntities && 0 < filteredEntities.length ? filteredEntities[0] : null;
    return theEntity;
  }
});