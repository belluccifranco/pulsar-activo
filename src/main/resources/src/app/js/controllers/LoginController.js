(function () {
    'use strict';

    angular.module('pulsarActivo')
        .controller('LoginController', ['$scope', '$location', 'AuthService',
            function ($scope, $location, AuthService) {

                $scope.credentials = {};
                $scope.login = function () {

                    AuthService.login($scope.credentials)
                        .success(function () {
                            console.log('LOGIN OK !!!');
                            $scope.error = false;
                            $location.path("/main");
                        }).error(function () {
                            console.log('REJECTED !!!');
                            $scope.error = true;
                            $location.path("/login");
                        });
                };
            }]);
}());


