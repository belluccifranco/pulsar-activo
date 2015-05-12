angular.module('pulsarActivo')
    .controller('LoginController', ['$scope', '$location', 'AuthService', '$rootScope',
        function ($scope, $location, AuthService, $rootScope) {

            //use $cookie instead
            if ($rootScope.authenticated) {
                $location.path("/main");
            }

            $scope.credentials = {};

            $scope.login = function () {
                AuthService.login($scope.credentials, function () {
                    if ($rootScope.authenticated) {
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
                        $rootScope.authenticated = false;
                        $location.path("/");
                    }).error(function () {
                        $rootScope.authenticated = false;
                    });
            };

        }]);



