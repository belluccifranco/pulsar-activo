angular.module('pulsarActivo')
    .factory('DeviceService', ['$resource',
        function ($resource) {
            return $resource('/device/:id', { 'id': '@id' }, {
                list: {method: 'GET', url: '/devices/:page', cache: false, isArray: false},
                get: {method: 'GET', cache: false, isArray: false},
                save: {method: 'POST', url: '/device', cache: false, isArray: false},
                update: {method: 'PUT', cache: false, isArray: false},
                delete: {method: 'DELETE', cache: false, isArray: false}
            });
        }]);

