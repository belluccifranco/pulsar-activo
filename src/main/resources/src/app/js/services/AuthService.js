angular.module('pulsarActivo')
    .factory('AuthService', ['$http', '$cookies',
        function ($http, $cookies) {
            return {
                login: function (credentials) {
                    return $http.post('/login', credentials).then(function (response) {
                        return response.data.token;
                    });
                },

                setCredential: function (token) {
                    $cookies.token = token;
                },

                deleteCredential: function () {
                    $cookies.token = "";
                },

                checkCredential: function () {
                    var isLoggedIn = false;
                    var credentialInCookie = $cookies.token;
                    if (credentialInCookie !== undefined && credentialInCookie !== "") {
                        isLoggedIn = true;
                    }
                    return isLoggedIn;
                }
            };
}]);