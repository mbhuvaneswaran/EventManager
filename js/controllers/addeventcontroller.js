/**
 * Created by bhuvanem on 12/17/2014.
 */
define(['app','bootstrap','dtp',],function(app,bootstrap,dtp){

    return app.controller('AddEventController',['$scope','$location',function($scope,$location){
        $scope.details={
            name:"",
            place:{
                address1:"",
                address2:""
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
        $scope.addEvent=function(){
            $scope.details.invitees.push({email:$scope.currentUser});
            $scope.details.id=$scope.getID();
            $scope.details.date=$("#datetimepicker1").datepicker( 'getDate' );
            var Events=JSON.parse(localStorage.getItem("events"));
            Events?"":Events=[];
            Events.push($scope.details);
            localStorage.setItem('events',JSON.stringify(Events));
            $location.path("/Events");
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