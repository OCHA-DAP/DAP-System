app.directive('search', [ function() {
  return {
    restrict : 'E',
    transclude : true,
    replace : true,
    templateUrl : hdxContextRoot + '/js/admin/search.html',
    link : function(scope, element, attrs, controller) {
      scope.title = attrs['title'];
      if(!scope.search) {
        scope.search = {};
      }
      scope.clearSearch = function() {
        scope.search.$ = "";
      }
    }
  };
} ]);