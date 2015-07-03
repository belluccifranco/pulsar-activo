(function () {
    'use strict';

    angular.module('pulsarActivo', ['ui.router', 'ngResource', 'ngCookies', 'uiGmapgoogle-maps' ,'btford.socket-io'])
        .factory('socket', function (socketFactory) {
            return socketFactory({
                ioSocket: io.connect('http://' + location.hostname + ':3000')
            });
        })
        .filter('range', function () {
            return function (input, total) {
                total = parseInt(total);
                for (var i = 0; i < total; i++) {
                    input.push(i);
                }
                return input;
            };
        })
        .config(['uiGmapGoogleMapApiProvider', function (uiGmapGoogleMapApiProvider) {
            uiGmapGoogleMapApiProvider.configure({
                //key: 'your api key',
                v: '3.exp',
                libraries: 'weather,geometry,visualization'
            });
        }])
        .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            $urlRouterProvider.otherwise("/");

            $stateProvider
                .state('home', {
                    url: '/',
                    redirectTo: "/login"
                })
                .state('main', {
                    url: '/main',
                    templateUrl: 'templates/main.html',
                    controller: 'MainController'
                })
                .state('login',{
                    url: '/login',
                    templateUrl: 'templates/login.html',
                    controller: 'LoginController'
                })
                .state('devices', {
                    url: '/devices/:page',
                    templateUrl: 'templates/devices.html',
                    controller: 'DevicesController'
                })
                .state('newdevice', {
                    url: '/device/new',
                    templateUrl: 'templates/deviceForm.html',
                    controller: 'newDeviceController'
                })
                .state('editdevice', {
                    url: '/device/:id/edit',
                    templateUrl: 'templates/deviceForm.html',
                    controller: 'editDeviceController'
                })
                .state('showdevice', {
                    url: '/device/:id/show',
                    templateUrl: 'templates/deviceShow.html',
                    controller: 'showDeviceController'
                });
        }])
        .run(["$rootScope", "$location", "$state", "AuthService", function ($rootScope, $location, $state, AuthService) {
            $rootScope.$on("$stateChangeStart", function (event, toState, toParams, fromState, fromParams) {
                if (AuthService.checkCredential() === false) {
                    $rootScope.showMenu = false;
                    $location.path("/login");
                    //$state.go("login");
                    //da error si pongo esto $state.go("login"); (bucle infinito?)
                } else {
                    $rootScope.showMenu = true;
                    if (toState.name === 'home') {
                        $state.go("main");
                        event.preventDefault();
                    }
                }
            });
        }]);
}());