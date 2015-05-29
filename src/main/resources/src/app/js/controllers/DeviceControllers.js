(function () {
    'use strict';

    /*var devices = {
            1: {
                id: 1,
                name: 'Caminante 01'
            },
            2: {
                id: 2,
                name: 'Caminante 05'
            },
            3: {
                id: 3,
                name: 'Caminante 08'
            },
            4: {
                id: 4,
                name: 'Caminante 09'
            },
            5: {
                id: 5,
                name: 'Caminante 10'
            },
            6: {
                id: 6,
                name: 'EMI 01'
            },
            7: {
                id: 7,
                name: 'EMI 04'
            },
            8: {
                id: 8,
                name: 'EMI 08'
            }
        },
        fleets = {
            1: {
                id: 1,
                name: 'Caminantes Resistencia',
                devices: [devices[1], devices[2], devices[3], devices[4], devices[5]]
            },
            2: {
                id: 2,
                name: 'EMI',
                devices: [devices[6], devices[7], devices[8]]
            }
        },
        groups = {
            1: {
                id: 1,
                name: '911 Policía del Chaco',
                fleets: [fleets[1]]
            },
            2: {
                id: 2,
                name: 'Municipalidad de Resistencia',
                fleets: [fleets[2]]
            }
        }; */

    angular.module('pulsarActivo')
        .controller('DevicesController', ['$scope', '$routeParams', 'DeviceService',
            function ($scope, $routeParams, DeviceService) {
                var page = $routeParams.page || 1;

                $scope.devices = [];

                DeviceService.list(
                    { page: page},
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
        .controller('newDeviceController', ['$scope', 'DeviceService', '$location',
            function($scope, DeviceService, $location) {
                var entity = new DeviceService({
                        name: '',
                        imei: '',
                        type: 'PANICBUTTON',
                        providerType: 'PERSONAL'
                    });

                $scope.action = 'Nuevo';
                $scope.formData = entity;

                $scope.save = function () {
                    if ($scope.form.$invalid) {
                        $scope.$broadcast('record:invalid');
                    } else {
                        $scope.formData.$save(
                            function (response) {
                                $location.path('/devices/1');
                            },
                            function(errorResponse) {
                                console.log($errorResponse);
                            }
                        );
                    }
                }
            }
        ]).controller('editDeviceController', ['$scope', '$routeParams', 'DeviceService', '$location',
            function($scope, $routeParams, DeviceService, $location) {
                var id = $routeParams.id;
                $scope.action = 'Editar';

                DeviceService.get(
                    { id: id },
                    function (response) {
                        console.log(response);
                        $scope.formData = response;
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
                                $location.path('/devices/1');
                            },
                            function(errorResponse) {
                                console.log($errorResponse);
                            }
                        );
                    }
                }
            }
        ]).controller('showDeviceController', ['$scope', '$routeParams', 'DeviceService',
            function($scope, $routeParams, DeviceService) {
                var id = $routeParams.id;

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