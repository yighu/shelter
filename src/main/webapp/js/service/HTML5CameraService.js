/*app.factory('formDataObject', function() {
    return function(data) {
        var fd = new FormData();
        angular.forEach(data, function(value, key) {
            fd.append(key, value);
        });
        return fd;
    };
});*/

app.factory('HTML5CameraService', function ($http, $resource, IMAGE_RESOURCE_URL) {
    var imageFactory = {};


    var imageQuery = $resource(IMAGE_RESOURCE_URL + "/client/:id", {}, {
        query:{method:'GET'}

    });

    var upload = function(data, success, error,URI) {
        var payload = new FormData();
        payload.append( 'imageContent', data.file);

        // populate payload
        $http.post(IMAGE_RESOURCE_URL + URI + data.id, payload, {
            headers: { 'Content-Type': false },
            transformRequest: function(data) { return data; }
        }).success(success).error(error);
    }



    imageFactory.getImage = imageQuery.query;
    imageFactory.uploadImage = upload;


    return imageFactory;


});

