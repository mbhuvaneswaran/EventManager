/**
 * Created by bhuvanem on 12/17/2014.
 */
define(['app'],function(app){

    return app.controller('HomeController',['$scope','$rootScope',function($scope,$rootScope){
        $scope.test="hai";
        debugger;
        $scope.user=EventApp.user.name;

    }]);
});