angular.module('pulsarActivo')
    .controller('LoginController', ['$scope', '$location', 'AuthService',
        function ($scope, $location, AuthService) {

            if (AuthService.checkCredential()) {
                $location.path("/main");
            }

            $scope.credentials = {};

            $scope.login = function () {
                AuthService.login($scope.credentials)
                    .success(function (data) {
                        if (data.name) {
                            AuthService.setCredential($scope.credentials.username, $scope.credentials.password);
                            $scope.error = false;
                            $location.path("/main");
                        } else {
                            AuthService.deleteCredential();
                            $scope.error = true;
                            $location.path("/login");
                        }
                    }).error(function () {
                        AuthService.deleteCredential();
                        $scope.error = true;
                        $location.path("/login");
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



