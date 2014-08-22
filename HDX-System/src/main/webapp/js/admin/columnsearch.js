app.directive('columnsearch', [ function() {
  return {
    restrict : 'E',
    transclude : true,
    replace : true,
    templateUrl : hdxContextRoot + '/js/admin/columnsearch.html',
    scope: {
      param: '=param'
    },
    link : function(scope, element, attrs, controller) {
      scope.clearSearch = function() {
        scope.param = '';
      }
    }
  };
} ]);