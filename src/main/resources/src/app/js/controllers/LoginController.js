(function () {
    'use strict';

    angular.module('pulsarActivo')
        .controller('LoginController', function ($scope, $location, LoginService, setCreds) {

            $scope.submit = function () {
                $scope.sub = true;
                var postData = {
                    "username": $scope.username,
                    "password": $scope.password
                };

                LoginService.login({}, postData,
                    function success(response) {
                        console.log("Success:" + JSON.stringify(response));
                        if (response.authenticated) {
                            setCreds($scope.username, $scope.password)
                            $location.path('/');
                        } else {
                            $scope.error = "Login Failed"
                        }

                    },
                    function error(errorResponse) {
                        console.log("Error:" + JSON.stringify(errorResponse));
                    }
                );

            };

        });
}());

