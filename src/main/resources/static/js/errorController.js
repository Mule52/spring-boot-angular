angular.module('demo')
    .controller('errorController', ['$scope', '$http',
        function($scope, $http) {

            // TODO: this is received 403 only at this time, so update it later.
            console.log("errorController called");
        }])
;