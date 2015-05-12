angular.module('pulsarActivo')
    .factory('AuthService', ['$http', '$rootScope',
        function ($http, $rootScope) {
            return {
                login: function (credentials, callback) {

                    var headers = credentials ? {
                        authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)
                    } : {};

                    $http.get('/user', {headers: headers})
                        .success(function (data) {
                            if (data.name) {
                                $rootScope.authenticated = true;
                            } else {
                                $rootScope.authenticated = false;
                            }
                            callback && callback();
                        }).error(function () {
                            $rootScope.authenticated = false;
                            callback && callback();
                        });
                },

                logout: function () {
                    return $http.post('/logout', {});
                }
            };
        }]);