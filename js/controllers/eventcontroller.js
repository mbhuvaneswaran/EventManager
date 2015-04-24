/**
 * Created by bhuvaneshwaranm on 23/4/15.
 */
define(['app','tooltip','jquery',"js/utility/eventdetailsmap.js",'async!http://maps.google.com/maps/api/js?sensor=false'],function(app,jq){

    return app.controller('EventController',['$scope','$routeParams',function($scope,$routeParams){
        $scope.accomNum=0;
        $scope.user="bhuvanesh12526@gmail.com";
        $scope.newEvent=false;
        $scope.EventId=$routeParams.EventID;
debugger;


        var events=JSON.parse(localStorage.getItem('events'));
        $scope.details=events.filter(getFilterFunction($scope.EventId))[0];
        initializeMap($scope.details.place.latitude,$scope.details.place.longitude,$scope.details.name);
        $scope.events=events?events:[];
        function getFilterFunction(id){
            var eventID=id;
            return function(event){
                return event.id==eventID;
            }
        }
        setTimeout(function(){
            jQuery('[data-toggle="tooltip"]').tooltip();
        },0);
        $scope.linkToUser=function(invt){
            if(invt.linkTo){
                delete(invt.linkTo);
                $scope.accomNum--;
                return;
            }
            else{
                if($scope.accomNum<$scope.details.transport.canAccomodate){
                    invt.linkTo=$scope.user;
                    $scope.accomNum++;
                }

            }


        }
        $scope.gotoEvent=function(){
            debugger;
        }
        $scope.iAmOut=function(){
            debugger;
        }
    }])

})