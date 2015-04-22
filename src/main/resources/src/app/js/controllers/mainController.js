(function(){
    'use strict';
    angular.module('pulsarActivoManager')
        .controller('mainController', function($scope, uiGmapGoogleMapApi) {
        	$scope.map = {center: {latitude: -27.4856987, longitude: -58.8023838 }, zoom: 13 };
        	uiGmapGoogleMapApi.then(function(maps) {

    		});
        });
}());