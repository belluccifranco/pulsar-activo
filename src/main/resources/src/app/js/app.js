(function () {
	'use strict';
	angular.module('pulsarActivoManager', ['ngRoute', 'ngResource','uiGmapgoogle-maps'])
        .config(['uiGmapGoogleMapApiProvider', function(uiGmapGoogleMapApiProvider) {
            uiGmapGoogleMapApiProvider.configure({
                //key: 'your api key',
                //v: '3.17',
                libraries: 'weather,geometry,visualization'
            });
        }])
        .config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
            $routeProvider
                .when('/', {
                    templateUrl: 'templates/main.html',
                    controller: 'mainController'
                });
            $locationProvider.html5Mode(false).hashPrefix('!');
        }]);
}());