angular.module('demo')
    .controller('homeController', ['$scope', '$http',
        function($scope, $http) {

            $http.get('/resource/').success(function(data) {
                $scope.greeting = data;
            });
        }])
;