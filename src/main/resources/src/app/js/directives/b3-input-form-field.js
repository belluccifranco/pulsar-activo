angular.module('pulsarActivo')
    .directive('b3InputFormField', function() {
        return {
            restrict: 'EA',
            templateUrl: '../templates/form/b3-input-form-field.html',
            replace: true,
            scope: {
                record: '=',
                field: '@',
                type: '@',
                required: '@'
            },
            link: function($scope, element, attr) {
                $scope.$on('record:invalid', function () {
                    $scope[$scope.field].$setDirty();
                });
            }
        }
    })