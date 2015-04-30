(function () {
	'use strict';
	angular.module('pulsarActivo', ['ngRoute', 'ngResource','uiGmapgoogle-maps', 'btford.socket-io'])
        .config(['uiGmapGoogleMapApiProvider', function(uiGmapGoogleMapApiProvider) {
            uiGmapGoogleMapApiProvider.configure({
                //key: 'your api key',
                //v: '3.17',
                libraries: 'weather,geometry,visualization'
            });
        }])
        .factory('socket', function (socketFactory) {
          return socketFactory({
             //ioSocket: io.connect('http://localhost:3000')
          });
        })
        .config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
            $routeProvider
                .when('/', {
                    templateUrl: 'templates/main.html',
                    controller: 'MainController'
                });
            $locationProvider.html5Mode(false).hashPrefix('!');
        }]);
}());