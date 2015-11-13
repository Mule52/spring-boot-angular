angular.module('demo', [ 'ngRoute' ]).config(function($routeProvider, $httpProvider) {

	$routeProvider
		.when('/', {
			templateUrl : 'home.html',
			controller : 'homeController'
		})
		.when('/login', {
			templateUrl : 'login.html',
			controller : 'navigationController'
		})
		.when('/admin', {
			templateUrl : 'admin.html',
			controller : 'adminController'
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