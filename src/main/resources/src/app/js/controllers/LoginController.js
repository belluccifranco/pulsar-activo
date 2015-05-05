(function () {
    'use strict';

    angular.module('pulsarActivo')
        .controller('LoginController', ['$scope', '$location', 'AuthService',
            function ($scope, $location, AuthService) {

                $scope.submit = function () {
                    $scope.sub = true;
                    var postData = {
                        "username": $scope.username,
                        "password": $scope.password
                    };

                    AuthService.login({}, postData,
                        function success(response) {
                            console.log("Success:" + JSON.stringify(response));
                            if (response.authenticated) {
                                AuthService.setCreds($scope.username, $scope.password);
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

            }]);
}());

