angular.module('demo')
    .controller('adminController', ['$rootScope', '$scope', '$http', '$location', '$route',
        function($rootScope, $scope, $http, $location, $route) {

            // TODO: avoid scope soup, controllers should be JS classes.
            // TODO: all API calls should be in a service.

            // INIT
            (function () {
                $scope.heading = 'ADMINS ONLY';
            }());
        }]
    )
;
