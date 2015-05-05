(function(){
    'use strict';
    var pulsarActivo = angular.module('pulsarActivo');

    pulsarActivo.factory('LoginService', ['$resource', function ($resource) {
        return $resource("/login", {}, {
            login: {method: 'POST', cache: false, isArray: false}
        });
    }]);

    pulsarActivo.factory('checkCreds', ['$cookies', function ($cookies) {
        return function () {
            var returnVal = false;
            var blogCreds = $cookies.blogCreds;
            if (blogCreds !== undefined && blogCreds !== "") {
                returnVal = true;
            }
            return returnVal;
        };

    }]);

    pulsarActivo.factory('getToken', ['$cookies', function ($cookies) {
        return function () {
            var returnVal = "";
            var blogCreds = $cookies.blogCreds;
            if (blogCreds !== undefined && blogCreds !== "") {
                returnVal = btoa(blogCreds);
            }
            return returnVal;
        };

    }]);

    pulsarActivo.factory('getUsername', ['$cookies', function ($cookies) {
        return function () {
            var returnVal = "";
            var blogUsername = $cookies.blogUsername;
            if (blogUsername !== undefined && blogUsername !== "") {
                returnVal = blogUsername;
            }
            return returnVal;
        };

    }]);

    pulsarActivo.factory('setCreds', ['$cookies', function ($cookies) {
        return function (un, pw) {
            var token = un.concat(":", pw);
            $cookies.blogCreds = token;
            $cookies.blogUsername = un;
        };

    }]);

    pulsarActivo.factory('deleteCreds', ['$cookies', function ($cookies) {
        return function () {
            $cookies.blogCreds = "";
            $cookies.blogUsername = "";
        };
    }]);
}());
