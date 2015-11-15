angular.module('demo')
    .controller('navigationController', ['$rootScope', '$scope', '$http', '$location', '$route',

        function($rootScope, $scope, $http, $location, $route) {

            $scope.tab = function(route) {
                return $route.current && route === $route.current.controller;
            };

            var authenticate = function(credentials, callback) {

                var headers = credentials ? {
                    authorization : "Basic "
                    + btoa(credentials.username + ":"
                        + credentials.password)
                } : {};

                $http.get('user', {
                    headers : headers
                }).success(function(data) {
                    if (data.name) {
                        $rootScope.authenticated = true;
                        $rootScope.isAdmin = _.find(data.authorities, function(x){return x.authority == 'ROLE_ADMIN';}) ? true : false;
                    } else {
                        $rootScope.authenticated = false;
                        $rootScope.isAdmin = false;
                    }
                    callback && callback($rootScope.authenticated);
                }).error(function() {
                    $rootScope.authenticated = false;
                    $rootScope.isAdmin = false;
                    callback && callback(false);
                });

            }

            authenticate();

            $scope.credentials = {};
            $scope.login = function() {
                authenticate($scope.credentials, function(authenticated) {
                    if (authenticated) {
                        console.log("Login succeeded")
                        $location.path("/");
                        $scope.error = false;
                        $rootScope.authenticated = true;
                    } else {
                        console.log("Login failed")
                        $location.path("/login");
                        $scope.error = true;
                        $rootScope.authenticated = false;
                    }
                })
            };

            $scope.logout = function() {
                $http.post('logout', {}).success(function() {
                    $rootScope.authenticated = false;
                    $location.path("/");
                }).error(function(data) {
                    console.log("Logout failed")
                    console.log(data)
                    $rootScope.authenticated = false;
                });
            }

        }])


;