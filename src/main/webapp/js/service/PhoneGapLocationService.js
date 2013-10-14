app.factory('PhoneGapLocationService', function () {
    var locationService = {};

    locationService.getLocation = function (callback) {
        navigator.geolocation.getCurrentPosition(function (position) {
                callback(position.coords);
            },
            function (err) {
                alert('Error getting location ' + err);
            });
    }

    return locationService;
});
