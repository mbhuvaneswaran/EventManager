/**
 * Created by bhuvaneshwaranm on 24/4/15.
 */
var geocoder;
var map;
var marker;
var infowindow;
function initializeMap(lat,lang,title) {
    infowindow=new google.maps.InfoWindow({size: new google.maps.Size(150,50)});
    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(lat,lang);
    var mapOptions = {
        zoom: 8,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    map = new google.maps.Map(document.getElementById('map_canvas'), mapOptions);
    google.maps.event.addListener(map, 'click', function() {
        infowindow.close();
    });
    setMarkerFirstMap(latlng,title);
}


function setMarkerFirstMap(latLng,title){

    map.setZoom(18);
    map.setCenter(latLng);
    marker = new google.maps.Marker({
        position:latLng ,
        map: map,
        title: title
    });

    google.maps.event.addListener(marker, 'click', function() {
        geocodePosition(marker.getPosition());
        if (marker.formatted_address) {
            infowindow.setContent(marker.formatted_address+"<br>coordinates: "+marker.getPosition().toUrlValue(6));
        } else  {
            infowindow.setContent("<br>coordinates: "+marker.getPosition().toUrlValue(6));
        }
        infowindow.open(map, marker);
    });
}

function geocodePosition(pos) {
    geocoder.geocode({
        latLng: pos
    }, function(responses) {
        if (responses && responses.length > 0) {
            marker.formatted_address = responses[0].formatted_address;
        } else {
            marker.formatted_address = 'Cannot determine address at this location.';
        }
        infowindow.setContent(marker.formatted_address+"<br>coordinates: "+marker.getPosition().toUrlValue(6));
        infowindow.open(map, marker);
    });
}

