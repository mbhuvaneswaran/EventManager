/**
 * Created by bhuvanem on 12/17/2014.
 */
define(['app',"js/service/eventappservice.js"],function(app){

    return app.controller('EventsController',['$scope','$location',"EventService",function($scope,$location,EventService){
    $scope.newEvent=false;
        var events;
        //localStorage.getItem('events')?(events=JSON.parse(localStorage.getItem('events'))):"";
        //$scope.events=events?events:[];
        EventService.getEvents()
            .done(function(response){
                $scope.events=response;
                $scope.$apply();
            })
        $scope.getEventDetails=function(event){
            $location.path("/Events/"+event._id);
        }
    }])

})