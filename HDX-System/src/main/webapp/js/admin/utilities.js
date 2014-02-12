angular.module('utilities', []).factory('utilities', [ '$filter', '$http', function($filter, $http) {

  // Available functions
  return {
    showMessage : showMessage,
    findSomething : findSomething,
    encodeParams : encodeParams,
    setFocus : setFocus,
    post : post,
    loadResource : loadResource,
    createResource : createResource,
    updateResource : updateResource,
    deleteResource : deleteResource
  };

  // Show a message
  function showMessage(message) {
    alert(message);
  }

  // Find something
  function findSomething(where, what, when) {
    var param = {};
    param[what] = when;
    var result = $filter('filter')(where, param, true);
    return result;
  }

  // Create the url parameters string
  function encodeParams(data) {
    var result = [];
    for ( var d in data)
      result.push(encodeURIComponent(d) + "=" + encodeURIComponent(data[d]));
    return result.join("&");
  }

  // Set the focus on the given field
  function setFocus(fieldName) {
    var theField = document.getElementById(fieldName);
    if (theField) {
      theField.focus();
    }
  }

  // Post a request
  // In options, there must be :
  // - an url to post to
  // - some params
  // - a callback for success
  // - a callback for error
  function post(options) {
    return $http.post(dapContextRoot + options.url, encodeParams(options.params), {
      headers : {
        'Content-Type' : 'application/x-www-form-urlencoded'
      }
    }).success(function(data, status, headers, config) {
      // this callback will be called asynchronously
      // when the response is available
      if (options.successCallback) {
        options.successCallback();
      }
    }).error(function(data, status, headers, config) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
      if (options.errorCallback) {
        options.errorCallback();
      }
    });
  }
  ;

  // ////////////////////
  // Resources management
  // ////////////////////

  // Load a JSON resource from a given url into a given scope under a given name
  // - theScope : the variable into which the result will be put
  // - name : the name under which the result will be put into the scope (then available as <theScope>.<name>)
  // - url : the JSON service
  function loadResource(theScope, name, url, successCallback, errorCallback) {
    return $http.get(dapContextRoot + url).success(function(data, status, headers, config) {
      theScope.resource = data;
      // eval("theScope." + name + " = data;");
      theScope[name] = data;
      if (successCallback) {
        successCallback();
      }
    }).error(function(data, status, headers, config) {
      if (errorCallback) {
        errorCallback();
      }
    });
  }
  ;

  // Create a resource
  // =================

  // Create
  function createResource(options) {
    return updateResource(options);
  }

  // Update a resource
  // =================
  function updateResource(options) {
    var valid = options.validate(options.data);
    if ("OK" === valid) {
      return post(options);
    } else {
      alert("Form not valid ! \r\n" + valid);
      return "NOK";
    }
  }

  // Delete a resource
  // =================
  function deleteResource(options) {
    if (!confirm("Do you really want to delete this item ?")) {
      return;
    }
    return post(options);
  }
  ;

} ]);