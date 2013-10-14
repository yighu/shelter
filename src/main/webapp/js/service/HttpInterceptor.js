
/*//be sure to inject $scope and $location
var changeLocation = function(url, forceReload) {
    $scope = $scope || angular.element(document).scope();
    if(forceReload || $scope.$$phase) {
        window.location = url;
    }
    else {
        //only use this if you want to replace the history stack
        //$location.path(url).replace();

        //this this if you want to change the URL and add it to the history stack
        $location.path(url);
        $scope.$apply();
    }
};

  */

app.config(function($httpProvider) {

    var httpInterceptor = ['$location', '$q', function ($location, $q) {

        function success(response) {
            return response;
        }

        function error(response) {
            var status = response.status;
            if (status == 401) {
                window.location = '/login?redirect=#' + $location.path();
                return;
            }
            else if (status == 400) {
                console.log('400', $location.path());
                console.log('response.data', response.data);
            }
            // otherwise
            return $q.reject(response);

        }

        return function(promise) {
            return promise.then(success, error);
        }
    }];

    $httpProvider.responseInterceptors.push(httpInterceptor);
});
