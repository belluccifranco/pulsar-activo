(function(){
    'use strict';

    angular.module('pulsarActivo')
        .factory('Device', ['$resource',
            function ($resource) {
                return $resource('/device/:id', {}, {
                    get:    { method: 'GET',    cache: false, isArray: false },
                    save:   { method: 'POST',   cache: false, isArray: false },
                    update: { method: 'PUT',    cache: false, isArray: false },
                    delete:	{ method: 'DELETE',	cache: false, isArray: false }
                });
            }
        ])
        .factory('Devices', ['$resource',
            function ($resource) {
                return $resource('/devices', {}, {
                    get: { method: 'GET', cache: false, isArray: false }
                });
            }
        ]);
}());