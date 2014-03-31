app.controller('OrganizationsCtrl', function($rootScope, $scope, $filter, utilities) {

  // //////////////////////// //
  // Organizations management //
  // //////////////////////// //

  // Sort management
  $scope.predicate = 'shortName';

  // Load organizations
  // ==================
  $scope.processOrganizationsTranslations = function() {
    var orgs = $scope.organizations;
    
    $scope.shortNames = new Array();
    $scope.fullNames = new Array();

    for(var i in orgs) {
      var org = orgs[i];
      var shortName = {};
      shortName.id = org.id;
      shortName.text_id = org.text_id;
      shortName.default_value = org.shortName;
      shortName.translations = org.shortNameTranslations.translations;
      $scope.shortNames.push(shortName);

      var fullName = {};
      fullName.id = org.id;
      fullName.text_id = org.text_id;
      fullName.default_value = org.fullName;
      fullName.translations = org.fullNameTranslations.translations;
      $scope.fullNames.push(fullName);
    }
  }
  $scope.loadOrganizations = function() {
    return utilities.loadResource($scope, 'organizations', '/admin/curated/organizations/json', function() {
      $scope.processOrganizationsTranslations();
      // $scope.resetNewTranslations();
    });
  }
  $scope.loadOrganizations();

  $rootScope.$watch('i18nUpdate', function () {
    console.log('i18nUpdate changed : ' + $rootScope.i18nUpdate);
    if($rootScope.i18nUpdate) {
      $scope.loadOrganizations();
      $rootScope.i18nUpdate = false;
    }
  });
  
  // Create an organization
  // ======================
  $scope.createOrganization = function(data) {
    var params = {};
    if (data && data.shortName) {
      angular.extend(params, {
        "shortName" : data.shortName
      });
    }
    if (data && data.fullName) {
      angular.extend(params, {
        "fullName" : data.fullName
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
    return utilities.createResource({
      validate : $scope.checkCreateForm,
      data : data,
      params : params,
      url : '/admin/curated/organizations/submitCreate',
      successCallback : function() {
        $scope.resetNewOrganization();
        $scope.resetCreateResourceForm();
        $scope.loadOrganizations();
      },
      errorCallback : function() {
        alert("Organization creation threw an error. Maybe this organization already exists. No organization has been created.");
      }
    });
  };

  // Check that the new organization is complete
  $scope.checkCreateForm = function(data) {
    if (!data) {
      utilities.setFocus('newResource_shortName');
      return "At least some info should be provided.";
    }
    var shortName = data.shortName;
    if (!shortName || null === shortName || '' === shortName) {
      utilities.setFocus('newResource_shortName');
      return "Short name cannot be empty.";
    }
    var fullName = data.fullName;
    if (!fullName || null === fullName || '' === fullName) {
      utilities.setFocus('newResource_fullName');
      return "Full name cannot be empty.";
    }
    return "OK";
  };

  // Reset the new organization
  $scope.resetNewOrganization = function() {
    if (!$scope.newResource) {
      $scope.newResource = {};
    }
    $scope.newResource.shortName = "";
    $scope.newResource.fullName = "";
    $scope.newResource.link = "";
  };

  // Reset the create resource form
  $scope.resetCreateResourceForm = function() {
    $scope.createResourceForm.$setPristine();
  };

  // Update an organization
  // ======================
  $scope.updateOrganization = function(data, id) {
    var params = {};
    angular.extend(params, {
      "organizationId" : id
    });
    angular.extend(params, {
      "newShortName" : data.shortName
    });
    angular.extend(params, {
      "newFullName" : data.fullName
    });
    if (data.link) {
      angular.extend(params, {
        "newLink" : data.link
      });
    }

    return utilities.updateResource({
      validate : $scope.checkUpdateForm,
      data : data,
      params : params,
      url : '/admin/curated/organizations/submitUpdate',
      successCallback : function() {
        $scope.loadOrganizations();
      },
      errorCallback : function() {
        alert("Organization update threw an error. No organization has been updated.");
        $scope.loadOrganizations();
      }
    });
  };

  // Check that the updated organization is valid
  $scope.checkUpdateForm = function(data) {
    var shortName = data.shortName;
    if (!shortName || null === shortName || '' === shortName) {
      return "Short name cannot be empty.";
    }
    var fullName = data.fullName;
    if (!fullName || null === fullName || '' === fullName) {
      return "Full name cannot be empty.";
    }
    var link = data.link;
    if (!link || null === link || 'http://' === link || 'https://' === link) {
      data.link = "";
    } else if (link.lastIndexOf("http://", 0) !== 0 && link.lastIndexOf("https://", 0) !== 0) {
      data.link = "http://" + link;
    }
    return "OK";
  };

  // Delete an organization
  // ======================
  $scope.deleteOrganization = function(id) {
    return utilities.deleteResource({
      params : {
        "organizationId" : id
      },
      url : '/admin/curated/organizations/submitDelete',
      successCallback : $scope.loadOrganizations,
      errorCallback : function() {
        alert("Organization deletion threw an error. Maybe this organization is used by some source. No organization has been deleted.");
      }
    });
  }

  // Get a organization by its id
  // ============================
  $scope.getOrganizationById = function(organizationId) {
    var filteredOrganizations = $filter('filter')($scope.organizations, {
      id : organizationId
    });
    var theOrganization = filteredOrganizations && 0 < filteredOrganizations.length ? filteredOrganizations[0] : null;
    return theOrganization;
  }
});