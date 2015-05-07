/**
 * Created by bhuvaneshwaranm on 23/4/15.
 */
define(['app', 'tooltip', 'jquery', "js/utility/eventdetailsmap.js","js/service/eventappservice.js", 'async!http://maps.google.com/maps/api/js?sensor=false'], function (app, jq) {

    return app.controller('EventController', ['$scope', '$routeParams','EventService', function ($scope, $routeParams,EventService) {
        $scope.accomNum = 0;
        $scope.user = EventApp.user.email;
        $scope.newEvent = false;
        $scope.EventId = $routeParams.EventID;
        debugger;



        EventService.getEventDetails($scope.EventId)
            .done(function(response){
                $scope.details = response;




               // $scope.details.invitees.splice($scope.details.invitees.indexOf($scope.details.transport),1);
                initializeMap($scope.details.place.latitude, $scope.details.place.longitude, $scope.details.name);
                $scope.$apply();
               // $scope.events = events ? events : [];
            })

        function getFilterFunction(id) {
            var eventID = id;
            return function (event) {
                return event.id == eventID;
            }
        }

        setTimeout(function () {
            jQuery('[data-toggle="tooltip"]').tooltip();
        }, 0);
        $scope.linkToUser = function (invt) {
            if(invt.transport!=null){
            if (invt.transport.linkTo) {
                delete(invt.transport.linkTo);
                $scope.accomNum--;
                return;
            }
            else {
                if ($scope.accomNum < $scope.details.transport.canAccomodate) {
                    invt.transport.linkTo = $scope.user;
                    $scope.accomNum++;
                }
            }

            }
            else{
                invt.transport={};
                invt.transport.linkTo = $scope.user;
                $scope.accomNum++;
            }

        }
        $scope.gotoEvent = function () {
            debugger;
            $scope.details.rsvp=true;
            EventService.editEvent($scope.details)
                .done(function(response){
                        debugger;
                })
                .fail(function(response){

                })
        }
        $scope.iAmOut = function () {
            debugger;
            $scope.details.rsvp=false;
        }
    }])

})