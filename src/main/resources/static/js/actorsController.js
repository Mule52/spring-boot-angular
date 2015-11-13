angular.module('demo')
    .controller('actorsController', ['$rootScope', '$scope', '$http', '$location', '$route',
    function($rootScope, $scope, $http, $location, $route) {

        // TODO: avoid scope soup, controllers should be JS classes.
        // TODO: all API calls should be in a service.

        $scope.tab = function(route) {
            return $route.current && route === $route.current.controller;
        };

        // TODO: sample app, create this as a service call.
        var getActors = function(pg, sz){
            $http.get('/api/actor/' + $scope.page + '/' + $scope.size).success(function(data) {
                $scope.actors = data.content;
                $scope.totalElements = data.totalElements;
                $scope.totalPages = data.totalPages;
                $scope.totalPagesArray = _.range(data.totalPages);
                $scope.currentPage = data.number;
            });
        };

        $scope.goToPage = function(pg){
            if (pg < 0 || pg >= $scope.totalPages) return;
            $scope.page = pg;
            getActors($scope.page, $scope.size);
        };

        $scope.deleteActor = function(actor){
            // TODO: wire this up with a modal service
            $http.delete('/api/actor/' + actor.actorId).success(function(data){
                $scope.goToPage($scope.page);
            });
        };

        // INIT
        (function () {
            $scope.heading = 'Actors';
            $scope.actors = [];
            $scope.page = 0;
            $scope.size = 20;
            $scope.totalElements = 0;
            $scope.totalPages = 0;
            $scope.totalPagesArray = [];
            $scope.currentPage = 0;
            getActors($scope.page, $scope.size);
        }());
    }]
    )

    .controller('actorDetailController', ['$rootScope', '$scope', '$http', '$routeParams',
        function($rootScope, $scope, $http, $routeParams) {

            // TODO: avoid scope soup, controllers should be JS classes.

            $scope.save = function(actor){
                if (actor.actorId) {
                    return $http.put('/api/actor', JSON.stringify(actor)).success(function(data){
                        $scope.actor = data;
                        if ($scope.actor.lastUpdate) {
                            var date = new Date($scope.actor.lastUpdate);
                            $scope.actor.lastUpdatedString = date.toLocaleDateString() + " " + date.toLocaleTimeString();
                        }
                    });
                }
                else {
                    return $http.post('/api/actor', JSON.stringify(actor)).success(function(data){
                        $scope.actor = data;
                        if ($scope.actor.lastUpdate) {
                            var date = new Date($scope.actor.lastUpdate);
                            $scope.actor.lastUpdatedString = date.toLocaleDateString() + " " + date.toLocaleTimeString();
                        }
                    });
                }
            };

            // INIT
            (function () {
                $scope.heading = 'Actor Detail';

                $http.get('/api/actor/' + $routeParams.id).success(function(data) {
                    $scope.actorOrig = jQuery.extend(true, {}, data);
                    $scope.actor = data;
                    if ($scope.actor.lastUpdate) {
                        var date = new Date($scope.actor.lastUpdate);
                        $scope.actor.lastUpdatedString = date.toLocaleDateString() + " " + date.toLocaleTimeString();
                    }
                });
            }());
        }]
    )
;
