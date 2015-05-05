(function () {
    'use strict';

    angular.module('pulsarActivo')
        .factory('AuthService', ['$resource', '$cookies',
            function ($resource, $cookies) {
                return {
                    login: function ($resource) {
                        return $resource("/login", {},
                            {
                                login: {method: 'POST', cache: false, isArray: false}
                            });
                    },

                    checkCreds: function () {
                        var returnVal = false;
                        var blogCreds = $cookies.blogCreds;
                        if (blogCreds !== undefined && blogCreds !== "") {
                            returnVal = true;
                        }
                        return returnVal;
                    },

                    getToken: function () {
                        var returnVal = "";
                        var blogCreds = $cookies.blogCreds;
                        if (blogCreds !== undefined && blogCreds !== "") {
                            returnVal = btoa(blogCreds);
                        }
                        return returnVal;
                    },

                    getUsername: function () {
                        var returnVal = "";
                        var blogUsername = $cookies.blogUsername;
                        if (blogUsername !== undefined && blogUsername !== "") {
                            returnVal = blogUsername;
                        }
                        return returnVal;
                    },

                    setCreds: function (un, pw) {
                        var token = un.concat(":", pw);
                        $cookies.blogCreds = token;
                        $cookies.blogUsername = un;
                    },

                    deleteCreds: function () {
                        $cookies.blogCreds = "";
                        $cookies.blogUsername = "";
                    }
                };
            }]);

}());
