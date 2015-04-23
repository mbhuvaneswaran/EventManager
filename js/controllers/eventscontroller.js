/**
 * Created by bhuvanem on 12/17/2014.
 */
define(['app'],function(app){

    return app.controller('EventsController',['$scope','$location',function($scope,$location){
    $scope.newEvent=false;
        var events;
        localStorage.getItem('events')?(events=JSON.parse(localStorage.getItem('events'))):"";
        $scope.events=events?events:[];
        $scope.getEventDetails=function(event){
            $location.path("/Events/"+event.id);
        }
    }])

})