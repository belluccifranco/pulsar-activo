(function () {
    'use strict';

    var devices = {
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
        };

    angular.module('pulsarActivo')
        .controller('MainController', ['$scope', 'uiGmapGoogleMapApi', 'DeviceService',
            function ($scope, uiGmapGoogleMapApi, DeviceService) {
                $scope.groups = groups;
                $scope.map = {center: {latitude: -27.4856987, longitude: -58.8023838}, zoom: 13};

                DeviceService.get({id: 1},
                    function success(response) {
                        console.log("Success:" + JSON.stringify(response));
                    },
                    function error(errorResponse) {
                        console.log("Error:" + JSON.stringify(errorResponse));
                    }
                );

                uiGmapGoogleMapApi.then(function (maps) {

                });
            }]);
}());