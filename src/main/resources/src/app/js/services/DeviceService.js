angular.module('pulsarActivo')
    .factory('DeviceService', ['$resource',
        function ($resource) {
            return $resource('/devices/:id', { 'id': '@id' }, {
                list: {method: 'GET', url: '/devices', cache: false, isArray: false},
                get: {method: 'GET', cache: false, isArray: false},
                save: {method: 'POST', url: '/devices', cache: false, isArray: false},
                update: {method: 'PUT', cache: false, isArray: false},
                delete: {method: 'DELETE', cache: false, isArray: false}
            });
        }]);

