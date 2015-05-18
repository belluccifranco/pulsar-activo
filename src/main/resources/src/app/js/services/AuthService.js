angular.module('pulsarActivo')
    .factory('AuthService', ['$http', '$cookies',
        function ($http, $cookies) {
            return {
                login: function (credentials) {
                    var headers = credentials ? {
                        authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)
                    } : {};
                    return $http.get('/user', {headers: headers});
                },

                logout: function () {
                    var me = this;
                    me.deleteCredential();
                    return $http.post('/logout', {});
                },

                setCredential: function (username, password) {
                    $cookies.myCredential = btoa(username.concat(":", password));
                    $cookies.username = username;
                },

                deleteCredential: function () {
                    $cookies.myCredential = "";
                    $cookies.username = "";
                },

                getUsername: function () {
                    var username = "";
                    var usernameInCookie = $cookies.username;
                    if (usernameInCookie !== undefined && usernameInCookie !== "") {
                        username = usernameInCookie;
                    }
                    return username;
                },

                checkCredential: function () {
                    var isLoggedIn = false;
                    var credentialInCookie = $cookies.myCredential;
                    if (credentialInCookie !== undefined && credentialInCookie !== "") {
                        isLoggedIn = true;
                    }
                    return isLoggedIn;
                }
            };
        }]);