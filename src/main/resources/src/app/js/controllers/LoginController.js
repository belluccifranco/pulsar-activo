angular.module('pulsarActivo')
    .controller('LoginController', ['$scope', '$state', 'AuthService', '$http',
        function ($scope, $state, AuthService, $http) {

            if (AuthService.checkCredential()) {
                $state.go('main');
            }

            $scope.credentials = {};

            $scope.login = function () {
                AuthService.login($scope.credentials).then(function (data) {
                    AuthService.setCredential(data);
                    $http.defaults.headers.common.Authorization = 'Bearer ' + data;
                    $scope.error = false;
                    $state.go("main");
                }, function (error) {
                    AuthService.deleteCredential();
                    $scope.error = true;
                    $state.go("login");
                });
            };

            $scope.logout = function () {
                $http.defaults.headers.common.Authorization = '';
                AuthService.deleteCredential();
                $state.go("login");
            };
        }]);
