angular.module('pulsarActivo')
    .factory('DeviceService', ['$resource',
        function ($resource) {
            return $resource('/api/devices/:id', { 'id': '@id' }, {
                listAll: {method: 'GET', url: '/api/devices', cache: false, isArray: true},
                list: {method: 'GET', url: '/api/devices', cache: false, isArray: false},
                get: {method: 'GET', cache: false, isArray: false},
                save: {method: 'POST', url: '/api/devices', cache: false, isArray: false},
                update: {method: 'PUT', cache: false, isArray: false},
                delete: {method: 'DELETE', cache: false, isArray: false}
            });
        }]);

