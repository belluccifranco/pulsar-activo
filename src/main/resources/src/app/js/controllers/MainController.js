(function () {
    'use strict';

    angular.module('pulsarActivo')
        .controller('MainController', ['$scope', 'uiGmapGoogleMapApi', 'DeviceService', 'socket', 'uiGmapIsReady',
            function ($scope, uiGmapGoogleMapApi, DeviceService, socket, uiGmapIsReady) {
                var $alertsModal = $('#myModal').modal({ show: false });

                $scope.events = [];
                $scope.alerts = [];

                /*-- Socket --------------------------------------------------------------------*/
                socket.forward('eventreport', $scope);
                $scope.$on('socket:eventreport', function (ev, data) {
                  var event = JSON.parse(data),
                      m = findMarkerById(event.device.id);

                  //add event to events list
                  $scope.events.unshift(event);

                  //update device

                  //update marker position
                  m.latitude = event.lat;
                  m.longitude = event.lng;

                  if (event.type === 'ALERT') {
                    $scope.alerts.push(event);
                    $alertsModal.modal('show');
                  }
                });

                /*-- Device -------------------------------------------------------------------*/
                DeviceService.listAll(
                    {},
                    function success(response) {
                        //console.log("Success:" + JSON.stringify(response));
                        $scope.devices = response;
                    },
                    function error(errorResponse) {
                        console.log("Error:" + JSON.stringify(errorResponse));
                    }
                );


                /*-- Maps ---------------------------------------------------------------------*/
                $scope.map = {control: {}, markerControl: {}, center: {latitude: -27.4856987, longitude: -58.8023838}, zoom: 13};
                $scope.markers = [];

                $scope.markersEvents = {
                    'click': function(gm, eName, m) {
                        _.each($scope.markers, function(mker) {
                            if (mker.id !== m.id) {
                                mker.show = false;
                            }
                        });
                        m.show = true;
                    }
                };

                function findMarkerById(id) {
                    var i;
                    for (i=0; i < $scope.markers.length; i +=1) {
                        if ($scope.markers[i].id == id) {
                            return $scope.markers[i];
                        }
                    }

                    return null;
                }

                function findGMapMarkerById(id) {
                    var i, gmarkers = $scope.map.markerControl.getGMarkers();
                    for (i=0; i < gmarkers.length; i +=1) {
                        if (gmarkers[i].model.id == id) {
                            return gmarkers[i];
                        }
                    }

                    return null;
                }

                $scope.showDeviceInMaps = function (device) {
                    var m = findMarkerById(device.id),
                        gmarker = findGMapMarkerById(device.id);

                    $scope.map.center.latitude = m.latitude;
                    $scope.map.center.longitude = m.longitude;
                    $scope.map.zoom = 15;

                    google.maps.event.trigger(gmarker, 'click');
                };

                uiGmapGoogleMapApi.then(function (maps) {
                    function createMarker(device) {
                        return {
                            latitude: device.lat,
                            longitude: device.lng,
                            title: device.name,
                            id: device.id,
                            type: device.type,
                            icon: device.type === 'AVL' ? '/images/car.png' : '/images/phones.png'
                        };
                    }

                    function showDevicesPositions() {
                        var i, markers = [];
                        for (i = 0; i < $scope.devices.length; i += 1) {
                            markers.push(createMarker($scope.devices[i]));
                        }

                        $scope.markers = markers;
                    }

                    uiGmapIsReady.promise(1).then(function(instances) {
                        showDevicesPositions();
                    });
                });
            }]);
}());