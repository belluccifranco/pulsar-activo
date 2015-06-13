angular.module('pulsarActivo', ['ngRoute', 'ngResource', 'ngCookies', 'uiGmapgoogle-maps' ,'btford.socket-io'])
    .factory('socket', function (socketFactory) {
         return socketFactory({
            ioSocket: io.connect('http://' + location.hostname + ':3000')
         });
     })
    .filter('range', function () {
        return function (input, total) {
            total = parseInt(total);
            for (var i = 0; i < total; i++)
                input.push(i);
            return input;
        };
    })
    .config(['uiGmapGoogleMapApiProvider', function (uiGmapGoogleMapApiProvider) {
        uiGmapGoogleMapApiProvider.configure({
            //key: 'your api key',
            //v: '3.17',
            libraries: 'weather,geometry,visualization'
        });
    }])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
                redirectTo: '/login'
            })
            .when('/main', {
                templateUrl: 'templates/main.html',
                controller: 'MainController'
            }).when('/login', {
                templateUrl: 'templates/login.html',
                controller: 'LoginController'
            }).when('/devices', {
                redirectTo: '/devices/1'
            }).when('/devices/:page', {
                templateUrl: 'templates/devices.html',
                controller: 'DevicesController'
            }).when('/device/new', {
                templateUrl: 'templates/deviceForm.html',
                controller: 'newDeviceController'
            }).when('/device/:id/edit', {
                templateUrl: 'templates/deviceForm.html',
                controller: 'editDeviceController'
            }).when('/device/:id/show', {
                templateUrl: 'templates/deviceShow.html',
                controller: 'showDeviceController'
            });
    }])
    .run(function ($rootScope, $location, AuthService) {
        $rootScope.$on("$routeChangeStart", function (event, next, current) {
            if (AuthService.checkCredential() === false) {
                $rootScope.showMenu = false;
                if (next.templateUrl === "/templates/login.html") {
                } else {
                    $location.path("/login");
                }
            } else {
                $rootScope.showMenu = true;
            }
        });
    });