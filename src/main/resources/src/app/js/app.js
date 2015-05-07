(function () {
    'use strict';

    angular.module('pulsarActivo', ['ngRoute', 'ngResource', 'ngCookies', 'uiGmapgoogle-maps' /*,'btford.socket-io'*/])
        /*.factory('socket', function (socketFactory) {
         return socketFactory({
         ioSocket: io.connect('http://localhost:3000')
         });
         })*/
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
        .config(['$httpProvider', function ($httpProvider) {

            $httpProvider.defaults.xsrfHeaderName = 'X-CSRF-TOKEN';
            $httpProvider.defaults.xsrfCookieName = 'CSRF-TOKEN';

            function b(a) {
                return a ? (a ^ Math.random() * 16 >> a / 4).toString(16) : ([1e16] + 1e16).replace(/[01]/g, b)
            }
            $httpProvider.interceptors.push(function () {
                return {
                    'request': function (config) {
                        // put a new random secret into our CSRF-TOKEN Cookie after each response
                        document.cookie = 'CSRF-TOKEN=' + b();
                        return config;
                    }
                };
            });
        }]);
}());