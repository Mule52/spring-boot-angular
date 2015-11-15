angular.module('demo', [ 'ngRoute' ]).config(function($routeProvider, $httpProvider) {

	$httpProvider.interceptors.push(function ($q, $rootScope, $window) {
		var stack = [];
		return {
			request: function (config) {
				//console.log("request " + config.url);
				stack.push(config.url);
				return config || $q.when(config);
			},
			requestError: function (rejection) {
				//console.log('requestError Failed with', rejection.status, 'status');
				stack = [];
				return $q.reject(rejection);
			},
			response: function (response) {
				stack = _.filter(stack, function (url) {
					return response.config.url != url;
				});
				//console.log("response " + response);
				return response || $q.when(response);
			},
			responseError: function (rejection) {
				//console.log('responseError Failed with', rejection.status, 'status');
				stack = [];
				// Check if unauthorized
				if (rejection.status === 403){
					$window.location.href = $window.location.protocol + '//' + $window.location.host + '/#/info?err=' + rejection.status;
					rejection.status = 200; // Avoid $compile:tpload errors on 403 status code response
					return rejection;
				}
				return $q.reject(rejection);
			}
		};
	});

	// TODO: Create authorization service to handle attempts at pages.
	$routeProvider
			.when('/', {
				templateUrl : 'home.html',
				controller : 'homeController'
			})
			.when('/info', {
				templateUrl : 'info.html',
				controller : 'errorController'
			})
			.when('/login', {
				templateUrl : 'login.html',
				controller : 'navigationController'
			})
			.when('/admin', {
				templateUrl : 'admin.html',
				controller : 'adminController',
				resolve: {
					"check":function($rootScope, $location){
						if (!$rootScope.isAdmin){
							$location.path('/');
							alert("You are not authorized to view this page.");
						}
					}
				}
			})
			.when('/actors', {
				templateUrl : 'actors.html',
				controller : 'actorsController'
			})
			.when('/actors/:id/detail', {
				templateUrl : 'actorDetail.html',
				controller : 'actorDetailController'
			})
			.otherwise('/');

		$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
	})
;