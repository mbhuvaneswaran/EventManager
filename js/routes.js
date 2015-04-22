/**
 * Created by bhuvanem on 12/17/2014.
 */
define(['angular', 'app','js/controllers/homecontroller.js','js/controllers/eventscontroller.js'], function(angular,app,HomeController) {

    return app.config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/Home', {
            templateUrl: 'templates/home.html',
            controller: 'HomeController'
        });
        
        $routeProvider.when('/Tasks/:TaskID',{
            templateUrl:'../templates/taskDetail.html',
            controller:'TaskController'
        });
 $routeProvider.when('/Events',{
            templateUrl:'templates/events.html',
            controller:'EventsController'
        });
 $routeProvider.when('/AddEvent',{
            templateUrl:'templates/addevent.html',
            controller:'AddEventController'
        });
        $routeProvider.otherwise({redirectTo: '/Home'});
    }]);

});