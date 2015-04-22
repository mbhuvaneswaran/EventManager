/**
 * Created by bhuvanem on 12/17/2014.
 */
define(['app','bootstrap','dtp',],function(app,bootstrap,dtp){

    return app.controller('AddEventController',['$scope',function($scope){
        $scope.details={
            name:"",
            place:{
                address1:"",
                address2:""
            },
            date:""

        }
        $scope.newEvent=false;
        $('#datetimepicker1').datetimepicker();
        $scope.currentUser="bhuvanesh12526@gmail.com";
        $scope.details.invitees=[{email:""}];
        $scope.addInvitee=function(){
            $scope.details.invitees.push({email:""})

        }
        $scope.removeInvitee=function(inv){
            $scope.details.invitees.splice($scope.invitees.indexOf(inv),1);
        }
        $scope.addEvent=function(){
            var Events=JSON.parse(localStorage.getItem("events"));
            Events?"":Events=[];
            Events.push($scope.details);
            localStorage.setItem('events',JSON.stringify(Events));

        }
    }])
})