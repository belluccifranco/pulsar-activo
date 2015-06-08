angular.module('pulsarActivo')
    .controller('LoginController', ['$scope', '$location', 'AuthService', '$http',
        function ($scope, $location, AuthService, $http) {

            if (AuthService.checkCredential()) {
                $location.path("/main");
            }

            $scope.credentials = {};

            $scope.login = function () {
                AuthService.login($scope.credentials).then(function (data) {
                    AuthService.setCredential(data);
                    $http.defaults.headers.common.Authorization = 'Bearer ' + data;
                    $scope.error = false;
                    $location.path("/main");
                }, function (error) {
                    AuthService.deleteCredential();
                    $scope.error = true;
                    $location.path("/login");
                });
            };

            $scope.logout = function () {
                $http.defaults.headers.common.Authorization = '';
                AuthService.deleteCredential();
                $location.path("/login");
            };
        }]);



