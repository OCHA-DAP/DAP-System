app.controller('EntityTypesCtrl', function($scope, $filter, utilities) {

  // ///////////////////////
  // Entity types management
  // ///////////////////////

  // Sort management
  $scope.predicate = 'code';

  // Load entity types
  // =================
  $scope.loadEntityTypes = function() {
    return utilities.loadResource($scope, 'entityTypes', '/admin/curated/entityTypes/json', function() {
      // $scope.resetNewTranslations();
    });
  }
  $scope.loadEntityTypes();

  // Create an entity type
  // =====================
  $scope.createEntityType = function(data) {
    var params = {};
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
      url : '/admin/curated/entityTypes/submitCreate',
      successCallback : function() {
        $scope.resetNewEntityType();
        $scope.resetCreateResourceForm();
        $scope.loadEntityTypes();
      },
      errorCallback : function() {
        alert("Entity type creation threw an error. Maybe this entity type already exists. No entity type has been created.");
      }
    });
  };

  // Check that the new entityType is complete
  $scope.checkCreateForm = function(data) {
    if (!data) {
      utilities.setFocus('newResource_code');
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
    return "OK";
  };

  // Reset the new entity type
  $scope.resetNewEntityType = function() {
    if (!$scope.newResource) {
      $scope.newResource = {};
    }
    $scope.newResource.code = "";
    $scope.newResource.name = "";
  };

  // Reset the create resource form
  $scope.resetCreateResourceForm = function() {
    $scope.createResourceForm.$setPristine();
  };

  // Update a entityType
  // ===============
  $scope.updateEntityType = function(data, id) {
    var params = {};
    angular.extend(params, {
      "entityTypeId" : id
    });
    angular.extend(params, {
      "newName" : data.name
    });
    return utilities.updateResource({
      validate : $scope.checkUpdateForm,
      data : data,
      params : params,
      url : '/admin/curated/entityTypes/submitUpdate',
      successCallback : function() {
        $scope.loadEntityTypes();
      },
      errorCallback : function() {
        alert("Entity type update threw an error. No entity type has been updated.");
        $scope.loadEntityTypes();
      }
    });
  };

  // Check that the updated entityType is valid
  $scope.checkUpdateForm = function(data) {
    var name = data.name;
    if (!name || null === name || '' === name) {
      return "Name cannot be empty.";
    }
    return "OK";
  };

  // Delete an entity type
  // =====================
  $scope.deleteEntityType = function(id) {
    return utilities.deleteResource({
      params : {
        "entityTypeId" : id
      },
      url : '/admin/curated/entityTypes/submitDelete',
      successCallback : $scope.loadEntityTypes,
      errorCallback : function() {
        alert("Entity type deletion threw an error. Maybe this entity type is used by some entity. No entity type has been deleted.");
      }
    });
  }

  // Get an entity type by its id
  // ============================
  $scope.getEntityTypeById = function(entityTypeId) {
    var filteredEntityTypes = $filter('filter')($scope.entityTypes, {
      id : entityTypeId
    });
    var theEntityType = filteredEntityTypes && 0 < filteredEntityTypes.length ? filteredEntityTypes[0] : null;
    return theEntityType;
  }
});