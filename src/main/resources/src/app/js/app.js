(function () {
    'use strict';

    angular.module('pulsarActivo', ['ngRoute', 'ngResource', 'ngCookies', 'uiGmapgoogle-maps', 'btford.socket-io'])
        .config(['uiGmapGoogleMapApiProvider', function (uiGmapGoogleMapApiProvider) {
            uiGmapGoogleMapApiProvider.configure({
                //key: 'your api key',
                //v: '3.17',
                libraries: 'weather,geometry,visualization'
            });
        }])
        .config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
            $routeProvider
                .when('/', {
                    templateUrl: 'templates/main.html',
                    controller: 'MainController'
                }).when('/login', {
                    templateUrl: 'templates/login.html',
                    controller: 'LoginController'
                });
            $locationProvider.html5Mode(false).hashPrefix('!');
        }])
        .factory('socket', function (socketFactory) {
            return socketFactory({
                //ioSocket: io.connect('http://localhost:3000')
            });
        });
}());