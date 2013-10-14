app.factory('PhoneGapCameraService', function ($resource, SERVER_URL, IMAGE_RESOURCE_URL) {
    var imageFactory = {};


    var imageQuery = $resource(IMAGE_RESOURCE_URL + "/client/:id", {}, {
        query:{method:'GET'}

    });

    /*var imageUpload = $resource(IMAGE_RESOURCE_URL + "/upload/:id", {}, {
        upload:{method:'POST'}

    });*/

    var upload = function(options, captureFail, uploadSuccess, uploadFail, URI) {
        if (options.type == 'camera')  {
            takePhoto(options.id, captureFail, uploadSuccess, uploadFail,URI);
        }
        else {
            useExistingPhoto(options.id, captureFail, uploadSuccess, uploadFail,URI);
        }
    }

    // use an existing photo from the library:
    var useExistingPhoto = function(id, captureFail, uploadSuccess, uploadFail,URI) {
        capture(id, Camera.PictureSourceType.SAVEDPHOTOALBUM, captureFail, uploadSuccess, uploadFail,URI);
    };

    // take a new photo:
    var takePhoto = function(id, captureFail, uploadSuccess, uploadFail,URI) {
        capture(id, Camera.PictureSourceType.CAMERA, captureFail, uploadSuccess, uploadFail,URI);
    };

    // capture either new or existing photo:
    var capture = function(id, sourceType, onCaptureFail, uploadSuccess, uploadFail,URI) {
        navigator.camera.getPicture(function(imageURI) {
            onCaptureSuccess(id, imageURI, uploadSuccess, uploadFail,URI);
        }, onCaptureFail, {
            destinationType: Camera.DestinationType.FILE_URI,
            sourceType: sourceType,
            allowEdit : true,
            quality : 50,
            encodingType: Camera.EncodingType.PNG,
            targetWidth: 200,
            targetHeight: 200
            //correctOrientation: true
        });
    };

    // if photo is captured successfully, then upload to server:
    var onCaptureSuccess = function(id, imageURI, uploadSuccess, uploadFail,URI) {
        var ft, options, params;

        options = new FileUploadOptions();
        // parameter name of file:
        options.fileKey = "imageContent";
        // name of the file:
        options.fileName = imageURI.substr(imageURI.lastIndexOf('/') + 1);
        options.chunkedMode = false;
        // mime type:
        //options.mimeType = "text/plain";
        /*params = {
            val1: "some value",
            val2: "some other value"
        };*/
        options.params = params;
        ft = new FileTransfer();
        //try {
            ft.upload(imageURI, SERVER_URL + IMAGE_RESOURCE_URL + URI + id, uploadSuccess, uploadFail, options);
        //}
        //catch(error) {
        //    alert(error);
        //}
    };

    imageFactory.getImage = imageQuery.query;
    imageFactory.uploadImage = upload;

    return imageFactory;


});

