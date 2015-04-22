/**
 * Created by bhuvanem on 12/17/2014.
 */
define(['app'],function(app){

    return app.controller('EventsController',['$scope',function($scope){
    $scope.newEvent=false;
        $scope.events=JSON.parse(localStorage.getItem('events'))?"":[];
    }])

})