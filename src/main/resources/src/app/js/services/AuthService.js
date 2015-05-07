(function () {
    'use strict';

    angular.module('pulsarActivo')
        .factory('AuthService', ['$http',
            function ($http) {
                return {
                    login: function (credentials) {
                        var headers = {
                            authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)
                        };
                        return $http.get('/user', {headers: headers});
                    }
                };
            }]);
}());
