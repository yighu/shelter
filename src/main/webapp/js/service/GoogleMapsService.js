app.factory('GoogleMapsService', function($rootScope, PhoneGapLocationService) {
    var svc = {};


    var createMarker = function (lat, lng, color, title, map) {

        var marker = new google.maps.Circle({
            center: new google.maps.LatLng(lat, lng),
            title: title,
            radius: 60,
            strokeColor: "black",
            strokeOpacity: 1,
            strokeWeight: 4,
            fillColor: color,
            fillOpacity: 0.8
        });

    var content1 = '<h1>'+ '<span style=\'font-weight: bold; font-size: large;\'>' + this.title + '</span>' +
                 '</h1>' + '<br>' + '<br>' +
                 '<p><a href="https://maps.google.com/maps?saddr='+this.lat+','+this.lng+'&daddr='
                 +this.lat+','+this.lng+'">'+ 'Get Directions</a> '+ '</p>';


        var infowindow = new google.maps.InfoWindow();
        google.maps.event.addListener(centerMarker, 'click', function () {
            infowindow.setContent(content1);
            infowindow.open(map, this);
        });

        if (map) {
            marker.setMap(map);
        }

        return marker;
    };

    //CREATE A NEW FUNCTION FOR CREATELISTMAP
    var createMap = function(elem, center) {
        var map = new google.maps.Map(elem, {
            zoom: 14,
            center: new google.maps.LatLng(center.lat, center.lng),
            mapTypeId: google.maps.MapTypeId.ROADMAP
        });
        return map;
    };



    var createListMap = function(elem, center) {
        var listMap = new google.maps.Map(elem, {
            zoom: 14,
            center: new google.maps.LatLng(center.lat, center.lng),
            mapTypeId: google.maps.MapTypeId.ROADMAP
        });
        return listMap;
    };


    var placeMarkers = function (map, locations, center) {
        var latLng = null;
        var infowindow = new google.maps.InfoWindow();

        if (center) {
            latLng = new google.maps.LatLng(center.lat, center.lng);
        }


        if (latLng) {
            switch (center.badge){
              case "Violent": var fillcolor = "red";
              break;
              case "Armed": var fillcolor = "yellow";
              break;
              default: var fillcolor = "green";
              break;
            }

            switch (center.status){
               case "Inactive": var fillopacity = .2;
               break;
               default: var fillopacity = .7;
               break;
            }

            var centerMarker = new google.maps.Marker({
                position:latLng,
                icon:{
                    path: google.maps.SymbolPath.CIRCLE,
                    scale: 9,
                    strokeWeight: 2,
                    fillColor: fillcolor,
                    fillOpacity: fillopacity
                },
                title: center.title
            });

            //var address = 'https://maps.google.com/maps?saddr=' + center.lat + ',' + center.lng + '&daddr=' + loc.lat + ',' + loc.lng;

            var content1 = '<h1>'+ '<span style=\'font-weight: bold; font-size: large;\'>' + center.title + '</span>' +
                         '</h1>' + '<br>'
                         //'<p><a href="https://maps.google.com/maps?daddr='
                         //+center.lat+','+center.lng+'">'+ 'Get Directions</a> '+ '</p>' +
                 //+ '<p><a target="_blank" onClick="window.open(\'' + address + '\', \'_blank\', \'location=yes\');">Get Directions</a></p>';
            + '<p><a target="_blank" onClick="getDirectionsAfterGettingLocation(' + PhoneGapLocationService.getLocation + ', ' + center.lat + ', ' + center.lng + ');">Get Directions</a></p>';

            //NEED TO FIND A WAY TO GET CURRENT LOCATION FOR START ADDRESS ABOVE


            google.maps.event.addListener(centerMarker, 'click', function() {
                infowindow.setContent(content1);
                infowindow.open(map, this);
            });

            centerMarker.setMap(map);
        }
    };

    var placeListMarkers = function (map, locations, center) {
        var latLng2 = null;
        var infowindow = new google.maps.InfoWindow();

        if (center) {
            latLng2 = new google.maps.LatLng(center.lat, center.lng);
        }


        if (latLng2) {
            var centerMarker = new google.maps.Marker({
                position:latLng2,
                icon:{
                    path: google.maps.SymbolPath.FORWARD_CLOSED_ARROW,
                    scale: 6,
                    strokeWeight: 2,
                    fillColor: "blue",
                    fillOpacity: 0.07
                },
                title: center.title
            });


            google.maps.event.addListener(centerMarker, 'click', function() {
                infowindow.setContent(this.title);
                infowindow.open(map, this);
            });

            centerMarker.setMap(map);
            var infowindow = new google.maps.InfoWindow();
            for (i in locations) {
                var loc = locations[i];
                var latLng3 = new google.maps.LatLng(loc.lat, loc.lng);
                switch (loc.badge){
                        case "Violent": var fillcolor = "red";
                        break;
                        case "Armed": var fillcolor = "yellow";
                        break;
                        default: var fillcolor = "green";
                        break;
                        }

                switch (loc.status){
                    case "Inactive": var fillopacity = .12;
                    break;
                    default: var fillopacity = .7;
                    break;
                }

                var content = '<h1>'+ '<span style=\'font-weight: bold; font-size: large;\'>' + loc.title + '</span>' +
                 '</h1>' + '<br>' + loc.badge + '<br>' + loc.status + '<br>' + loc.description +  '<br>' + '<br>'+
                 '<p><a href="#/camp/detail">'+ 'Camp Details</a> '+ '</p>'+
                 //'<p><a href="https://maps.google.com/maps?saddr='+center.lat+','+center.lng+'&daddr='+loc.lat+','+loc.lng+'">'+ 'Get Directions</a> '+ '</p>' +
                 //'<p><a target="_blank" onClick="getDirections(' + center.lat + ', ' + center.lng + ', ' + loc.lat + ', ' + loc.lng + ');">Get Directions</a></p>'
                 '<p><a target="_blank" onClick="getDirectionsAfterGettingLocation(' + PhoneGapLocationService.getLocation + ', ' + loc.lat + ', ' + loc.lng + ');">Get Directions</a></p>';



                var campMarker = new google.maps.Marker({
                     position:latLng3,
                     icon:{
                         path: google.maps.SymbolPath.CIRCLE,
                         scale: 7,
                         strokeWeight:2,
                         fillColor: fillcolor,
                         fillOpacity: fillopacity
                      },
                     title: loc.title
                });
                campMarker.set("id", loc.id);

                google.maps.event.addListener(campMarker,'click', (function(campMarker,content,infowindow){
                    return function() {
                        $rootScope.campId = campMarker.id;
                        infowindow.setContent(content );//+ '<br>' + '<br>'+'<input type="button" value="details" id="details">');
                        infowindow.open(map,campMarker);
                    };
                })(campMarker,content,infowindow));

                campMarker.setMap(map);
            }
        }
    };

    svc.createMap = createMap;
    svc.placeMarkers = placeMarkers;
    svc.createListMap = createListMap;
    svc.placeListMarkers = placeListMarkers;

    return svc;
});