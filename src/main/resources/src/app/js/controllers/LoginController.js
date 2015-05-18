angular.module('pulsarActivo')
    .controller('LoginController', ['$scope', '$location', 'AuthService',
        function ($scope, $location, AuthService) {

            if (AuthService.checkCredential()) {
                $location.path("/main");
            }

            $scope.credentials = {};

            $scope.login = function () {
                AuthService.login($scope.credentials, function () {
                    if (AuthService.checkCredential()) {
                        $location.path("/main");
                        $scope.error = false;
                    } else {
                        $location.path("/login");
                        $scope.error = true;
                    }
                });
            };

            $scope.logout = function () {
                AuthService.logout()
                    .success(function () {
                        AuthService.deleteCredential();
                        $location.path("/");
                    }).error(function () {
                        AuthService.deleteCredential();
                    });
            };

        }]);



