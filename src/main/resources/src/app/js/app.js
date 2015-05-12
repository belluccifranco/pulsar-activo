angular.module('pulsarActivo', ['ngRoute', 'ngResource', 'ngCookies', 'uiGmapgoogle-maps' /*,'btford.socket-io'*/])
    /*.factory('socket', function (socketFactory) {
     return socketFactory({
     ioSocket: io.connect('http://localhost:3000')
     });
     })*/
    .filter('range', function() {
      return function(input, total) {
        total = parseInt(total);
        for (var i=0; i<total; i++)
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
            }).when('/devices/:page',{
                templateUrl: 'templates/devices.html',
                controller: 'DevicesController'
            });
    }]);
