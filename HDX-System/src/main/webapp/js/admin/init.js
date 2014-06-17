var app = angular.module("app", [ "xeditable", "ui.bootstrap", "utilities", 'angular-loading-bar' ]);

app.constant("RESOURCE_TYPES", {
  language : {
    identifier: "code"
  },
  user : {
    identifier: "id"
  },
  entity : {
    identifier: "id"
  },
  source : {
    identifier: "code"
  }
});

app.run(function(editableOptions, $rootScope, RESOURCE_TYPES) {

  // X-editable theme
  editableOptions.theme = 'bs3'; // Theme : can be 'bs3, 'bs2' or 'default'

  // ////////////////////
  // Resources management
  // ////////////////////

  // The new resource
  $rootScope.newResource;
});
