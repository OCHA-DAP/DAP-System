app.controller('SourcesCtrl', function($scope, $filter, utilities) {

  // /////////
  // Resources
  // /////////
  $scope.resources = function() {
    $scope.organizations = appData['organizations'];
  }
  $scope.resources();

  // The new indicator
  $scope.newResource;

  // ////////////////////
  // Sources management
  // ////////////////////

  // Sort management
  $scope.predicate = 'code';

  // Load sources
  // ============
  $scope.loadSources = function() {
    return utilities.loadResource($scope, 'sources', '/admin/curated/sources/json', function() {
      // $scope.resetNewTranslations();
    });
  }
  $scope.loadSources();

  // Create a source
  // ===============
  $scope.createSource = function(data) {
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
    if (data && data.link) {
      if (data.link.lastIndexOf("http://", 0) !== 0 && data.link.lastIndexOf("https://", 0) !== 0) {
        data.link = "http://" + data.link;
      }
      angular.extend(params, {
        "link" : data.link
      });
    }
    if (data && data.organization) {
      angular.extend(params, {
        "organization" : data.organization.id
      });
    }
    return utilities.createResource({
      validate : $scope.checkCreateForm,
      data : data,
      params : params,
      url : '/admin/curated/sources/submitCreate',
      successCallback : function() {
        $scope.resetNewSource();
        $scope.resetCreateResourceForm();
        $scope.loadSources();
      },
      errorCallback : function() {
        alert("Source creation threw an error. Maybe this source already exists. No source has been created.");
      }
    });
  };

  // Check that the new source is complete
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

  // Reset the new source
  $scope.resetNewSource = function() {
    if (!$scope.newResource) {
      $scope.newResource = {};
    }
    $scope.newResource.code = "";
    $scope.newResource.name = "";
    $scope.newResource.link = "";
    $scope.newResource.organization = "";
  };

  // Reset the create resource form
  $scope.resetCreateResourceForm = function() {
    $scope.createResourceForm.$setPristine();
  };

  // Update a source
  // ===============
  $scope.updateSource = function(data, id) {
    var params = {};
    angular.extend(params, {
      "sourceId" : id
    });
    angular.extend(params, {
      "newName" : data.name
    });
    if (data.link) {
      angular.extend(params, {
        "newLink" : data.link
      });
    }
    if (data.organization) {
      angular.extend(params, {
        "newOrganization" : data.organization
      });
    }

    return utilities.updateResource({
      validate : $scope.checkUpdateForm,
      data : data,
      params : params,
      url : '/admin/curated/sources/submitUpdate',
      successCallback : function() {
        $scope.loadSources();
      },
      errorCallback : function() {
        alert("Source update threw an error. No source has been updated.");
        $scope.loadSources();
      }
    });
  };

  // Check that the updated source is valid
  $scope.checkUpdateForm = function(data) {
    var name = data.name;
    if (!name || null === name || '' === name) {
      return "Name cannot be empty.";
    }
    var link = data.link;
    if (!link || null === link || 'http://' === link || 'https://' === link) {
      data.link = "";
    } else if (link.lastIndexOf("http://", 0) !== 0 && link.lastIndexOf("https://", 0) !== 0) {
      data.link = "http://" + link;
    }
    return "OK";
  };

  // Delete a source
  // ===============
  $scope.deleteSource = function(id) {
    return utilities.deleteResource({
      params : {
        "sourceId" : id
      },
      url : '/admin/curated/sources/submitDelete',
      successCallback : $scope.loadSources,
      errorCallback : function() {
        alert("Source deletion threw an error. Maybe this source is used by some indicator. No source has been deleted.");
      }
    });
  }

  // Get a source by its id
  // ======================
  $scope.getSourceById = function(sourceId) {
    var filteredSources = $filter('filter')($scope.sources, {
      id : sourceId
    });
    var theSource = filteredSources && 0 < filteredSources.length ? filteredSources[0] : null;
    return theSource;
  }
  
  // Show a source's organization
  $scope.showOrganization = function(source) {
    if(!source.organization) {
      return '';
    }
    var selected = $filter('filter')($scope.organizations, {
      id : source.organization.id
    });
    return (selected.length) ? selected[0].fullName : '';
  };

});