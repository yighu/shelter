app.value('SERVER_URL', 'http://shelterapp.appspot.com/');

app.value('CLIENT_RESOURCE_URL', 'api/client');
app.value('CAMP_RESOURCE_URL', 'api/camp');
app.value('IMAGE_RESOURCE_URL', 'api/image');
app.value('SCHEDULER_RESOURCE_URL', 'api/event');
app.value('USER_RESOURCE_URL', '/api/user');
app.value('PRIVILEGE_RESOURCE_URL', '/api/privilege');
app.value('ROLE_RESOURCE_URL', '/api/role');
app.value('FEATURE_RESOURCE_URL', '/api/feature');
app.value('NEWS_RESOURCE_URL', '/api/news');
app.value('NOTES_RESOURCE_URL', '/api/notes');
app.value('CLIENT_LIST_DELAY', 4);
app.value('NEWS_LIST_DELAY', 4);
app.value("NOTES_LIST_DELAY", 300);

app.value('CLIENT_LIST_PAGE_SIZE', 10);
app.value('NEWS_LIST_PAGE_SIZE', 10);
app.value('CAMP_LIST_PAGE_SIZE', 10);
app.value('ADMIN_LIST_PAGE_SIZE', 10);

app.value('IMAGE_RESIZE_WIDTH', 200);



app.directive('fallbackSrc', function () {
    var fallbackSrc = {
        link: function postLink(scope, iElement, iAttrs) {
            iElement.bind('error', function() {
                angular.element(this).attr("src", iAttrs.fallbackSrc);
            });
        }
    }
    return fallbackSrc;
});

var imageURL = function(clientId){
    return 'api/image/client/' + clientId;
};
var imageCampURL = function(campId){
    return 'api/image/camp?campId=' + campId;
};

var checkAdmin = function (RoleService, scope) {
    RoleService.checkAdmin(function (data) {
        scope.isAdmin = data.admin;
    }, function (err) {
        scope.isAdmin = false;
    });

};

//needs to be global to be accessed by PhoneGap
getDirections = function(lat1, lng1, lat2, lng2) {
    var address = 'https://maps.google.com/maps?saddr=' + lat1 + ',' + lng1 + '&daddr=' + lat2 + ',' + lng2;
    var open = window.open(address, '_blank', 'location=yes');
    if (open == null || typeof(open)=='undefined')
        alert("Turn off your pop-up blocker in Settings!");
}

getDirectionsAfterGettingLocation = function (getLocation, lat, lng) {
    getLocation(function (coords) {
        getDirections(coords.latitude, coords.longitude, lat, lng)
    });
    return false;
}

function getScript(url, success){

    var head = document.getElementsByTagName("head")[0], done = false;
    var script = document.createElement("script");
    script.src = url;

    // Attach handlers for all browsers
    script.onload = script.onreadystatechange = function(){
        if ( !done && (!this.readyState ||
            this.readyState == "loaded" || this.readyState == "complete") ) {
            done = true;
            success();
        }
    };

    head.appendChild(script);
}

function endsWith(str, suffix) {
    return str.indexOf(suffix, str.length - suffix.length) !== -1;
}


if (endsWith(window.location.href, 'hybrid')) {
    window.isHybrid = true;
}
else {
    window.isHybrid = false;
}


if (window.isHybrid) {
    getScript('cordova.js', function () {
        //alert('hybrid')
    });
}
else {
    //alert('web');
}

