(function () {
    'use strict';

    angular.module('pulsarActivo')
        .controller('DevicesController', ['$scope', '$stateParams', 'DeviceService',
            function ($scope, $stateParams, DeviceService) {
                var page = $stateParams.page || 1;

                $scope.devices = [];

                DeviceService.list(
                    { page: page },
                    function success(response) {
                        console.log("Success:" + JSON.stringify(response));
                        $scope.devices = response.content;
                        $scope.page = page;
                        $scope.totalPages = response.totalPages;
                    },
                    function error(errorResponse) {
                        console.log("Error:" + JSON.stringify(errorResponse));
                    }
                );
            }
        ])
        .controller('newDeviceController', ['$scope', 'DeviceService', '$state',
            function($scope, DeviceService, $state) {
                $scope.formData = new DeviceService({
                    name: '',
                    imei: '',
                    phoneNumber: '',
                    type: 'PANICBUTTON',
                    carrier: null
                });

                $scope.action = 'Nuevo';

                $scope.save = function () {
                    if ($scope.form.$invalid) {
                        $scope.$broadcast('record:invalid');
                    } else {
                        $scope.formData.$save(
                            function (response) {
                                $state.go('devices', { page: 1 });
                            },
                            function(errorResponse) {
                                console.log(errorResponse);
                            }
                        );
                    }
                };
            }
        ]).controller('editDeviceController', ['$scope', '$stateParams', 'DeviceService', '$state',
            function($scope, $stateParams, DeviceService, $state) {
                var id = $stateParams.id;
                $scope.action = 'Editar';

                DeviceService.get(
                    { id: id },
                    function (response) {
                        //console.log(response);
                        $scope.formData = response;
                        //$scope.device = response;
                    },
                    function (errorResponse) {
                        console.log("Error:" + JSON.stringify(errorResponse));
                    }
                );

                $scope.save = function () {
                    if ($scope.form.$invalid) {
                        $scope.$broadcast('record:invalid');
                    } else {
                        $scope.formData.$save(
                            function (response) {
                                $state.go('devices', { page: 1 });
                            },
                            function(errorResponse) {
                                console.log(errorResponse);
                            }
                        );
                    }
                };
            }
        ]).controller('showDeviceController', ['$scope', '$stateParams', 'DeviceService',
            function($scope, $stateParams, DeviceService) {
                var id = $stateParams.id;

                DeviceService.get(
                    { id: id },
                    function (response) {
                        console.log(response);
                        $scope.device = response;
                    },
                    function (errorResponse) {
                        console.log("Error:" + JSON.stringify(errorResponse));
                    }
                );
            }
        ]);
}());