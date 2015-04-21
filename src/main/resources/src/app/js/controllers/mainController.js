(function(){
    'use strict';
    angular.module('pulsarActivoManager')
        .controller('mainController', function($scope, uiGmapGoogleMapApi) {
        	$scope.map = {center: {latitude: 40.1451, longitude: -99.6680 }, zoom: 4 };
        	uiGmapGoogleMapApi.then(function(maps) {

    		});
        });
}());