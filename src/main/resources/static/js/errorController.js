angular.module('demo')
    .controller('errorController', ['$scope', '$http',
        function($scope, $http) {

            console.log("errorController called");
        }])
;