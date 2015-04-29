/**
 * Created by bhuvanem on 12/17/2014.
 */
define(['app','bootstrap','jquery','dtp',"js/utility/createeventmap.js",'async!http://maps.google.com/maps/api/js?sensor=false'],function(app,bootstrap,dtp){
   // google.maps.event.addDomListener(window, 'load', initialize);
   // initialize();
    return app.controller('AddEventController',['$scope','$location',function($scope,$location){
        initialize();
        $scope.details={
            name:"",
            place:{
                address1:"",
                address2:"",
                latitude:"",
                longitude:""
            },
            invitees:[],
            date:""

        }
        $scope.newEvent=false;
        $('#datetimepicker1').datetimepicker();
        $scope.currentUser="bhuvanesh12526@gmail.com";
        //$scope.details.invitees=[{email:""}];
        $scope.addInvitee=function(){
            $scope.details.invitees.push({email:""})

        }
        $scope.removeInvitee=function(inv){

            $scope.details.invitess?$scope.details.invitees.splice($scope.invitees.indexOf(inv),1):"";
        }
        $scope.addEvent=function(dtp){
            $scope.details.invitees.push({email:$scope.currentUser});
            $scope.details.id=$scope.getID();
            $scope.details.date=$("#datetimepicker1 input")[0].value;
            $scope.details.place.latitude=marker.getPosition().lat();
            $scope.details.place.longitude=marker.getPosition().lng();
            var locEvents=localStorage.getItem("events");
            var Events=locEvents?JSON.parse(localStorage.getItem("events")):"";
            Events?"":Events=[];
            Events.push($scope.details);
            localStorage.setItem('events',JSON.stringify(Events));
            var reqData={
                "description": $scope.details.description,
                "start": {
                    "dateTime": "2015-04-28T15:00:00+05:30"
                },
                "summary": $scope.details.name,
                "end": {
                    "dateTime": "2015-04-28T15:30:00+05:30"
                }
            }
            jQuery.ajax({
                type: "POST",
                url: "https://www.googleapis.com/calendar/v3/calendars/primary/events?access_token="+EventApp.user.token+"&alt=json",
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(reqData),
                success: function(){
                    $location.path("/Events");
                    $scope.$apply();
                }

            });

        }
        $scope.getID=function generateUUID(){
            var d = new Date().getTime();
            var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
                var r = (d + Math.random()*16)%16 | 0;
                d = Math.floor(d/16);
                return (c=='x' ? r : (r&0x3|0x8)).toString(16);
            });
            return uuid;
        };
    }])
})