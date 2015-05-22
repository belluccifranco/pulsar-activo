(function () {
    'use strict';
    angular.module('pulsarActivo')
        .controller('MenuController', ['$scope', '$location',
            function ($scope, $location) {
                $scope.isActive = function (viewLocation) {
                    return $location.path().indexOf(viewLocation) == 0;
                };
            }]
        );
}());