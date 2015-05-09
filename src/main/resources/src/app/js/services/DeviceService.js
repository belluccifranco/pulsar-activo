angular.module('pulsarActivo')
    .factory('DeviceService', ['$resource',
        function ($resource) {
            return {
                getById: function () {
                    return $resource('/device/:id', {}, {
                        get: {method: 'GET', cache: false, isArray: false},
                        save: {method: 'POST', cache: false, isArray: false},
                        update: {method: 'PUT', cache: false, isArray: false},
                        delete: {method: 'DELETE', cache: false, isArray: false}
                    });
                },

                getAll: function ($resource) {
                    return $resource('/devices', {}, {
                        get: {method: 'GET', cache: false, isArray: false}
                    });
                }
            };
        }]);

