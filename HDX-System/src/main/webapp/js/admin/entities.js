app.controller('EntitiesCtrl', function($scope, $filter, utilities) {

  // /////////
  // Resources
  // /////////
  $scope.resources = function() {
    $scope.entityTypes = appData['entityTypes'];
  }
  $scope.resources();

  // The new indicator
  $scope.newResource;

  // ///////////////////////
  // Entity types management
  // ///////////////////////

  // Sort management
  $scope.predicate = 'entityType';

  /*
  // Load entity types
  $scope.loadEntityTypes = function() {
    return utilities.loadResource($scope, 'entityTypes', '/admin/curated/entityTypes/json', function() {
      // $scope.resetNewTranslations();
    });
  };
  $scope.loadEntityTypes();
  */

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

  // Reset the new entity
  $scope.resetNewEntity = function() {
    if (!$scope.newResource) {
      $scope.newResource = {};
    }
    $scope.newResource.entityType = null;
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

  // Delete an entity
  // ================
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