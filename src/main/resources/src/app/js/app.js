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
    .config(['$routeProvider', function ($routeProvider) {
        //ask for credentials using resolve for each route
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
            });
    }]);
