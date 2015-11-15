//angular.module('demo', [ 'ngRoute' ]).config(function($routeProvider, $httpProvider) {
//
//    $httpProvider.interceptors.push(function ($q, $rootScope) {
//        var stack = [];
//        return {
//            // This method is called before $http sends the request to the backend,
//            // so you can modify the configurations and take other actions. This function
//            // receives the request configuration object as a parameter and has to return
//            // a configuration object or a promise.Returning an invalid configuration
//            // object or promise that will be rejected, will make the $http call fail.
//            request: function (config) {
//                console.log("request " + config.url);
//                if (stack.length == 0) {
//                    $rootScope.$broadcast('loading-started');
//                }
//                stack.push(config.url);
//                return config || $q.when(config);
//            },
//            // Sometimes a request cannot be sent or it is rejected by an interceptor.
//            // Request error interceptor captures requests that have been canceled by a
//            // previous request interceptor. It can be used in order to recover the request
//            // and sometimes undo things that have been set up before a request, like removing
//            // overlays and loading indicators, enabling buttons and fields and so on.
//            requestError: function (rejection) {
//                console.log('requestError Failed with', rejection.status, 'status');
//                $rootScope.$broadcast('loading-complete');
//                //// Check if unauthorized
//                //if (rejection.status == "401") {
//                //    alert('Your session is no longer active. Please log in.');
//                //    $location.path("/account/login");
//                //}
//                stack = [];
//                return $q.reject(rejection);
//            },
//            // This method is called right after $http receives the response from the backend,
//            // so you can modify the response and take other actions. This function receives a
//            // response object as a parameter and has to return a response object or a promise.
//            // The response object includes the request configuration, headers, status and data
//            // that returned from the backend. Returning an invalid response object or promise
//            // that will be rejected, will make the $http call fail.
//            response: function (response) {
//                stack = _.filter(stack, function (url) {
//                    return response.config.url != url;
//                });
//                if (stack.length == 0) {
//                    $rootScope.$broadcast('loading-complete');
//                }
//                console.log("response " + response);
//                return response || $q.when(response);
//            },
//            // Sometimes our backend call fails. Other times it might be rejected by a request
//            // interceptor or by a previous response interceptor. In those cases, response error
//            // interceptor can help us to recover the backend call.
//            responseError: function (rejection) {
//                // Handle response errors and do something useful.
//                console.log('responseError Failed with', rejection.status, 'status');
//                stack = [];
//                $rootScope.$broadcast('loading-complete');
//                return $q.reject(rejection);
//            }
//        };
//    });
//
//    $routeProvider
//        .when('/', {
//            templateUrl : 'home.html',
//            controller : 'homeController'
//        })
//        .when('/login', {
//            templateUrl : 'login.html',
//            controller : 'navigationController'
//        })
//        .when('/admin', {
//            templateUrl : 'admin.html',
//            controller : 'adminController'
//        })
//        .when('/actors', {
//            templateUrl : 'actors.html',
//            controller : 'actorsController'
//        })
//        .when('/actors/:id/detail', {
//            templateUrl : 'actorDetail.html',
//            controller : 'actorDetailController'
//        })
//        .otherwise('/');
//
//    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
//
//})
//;