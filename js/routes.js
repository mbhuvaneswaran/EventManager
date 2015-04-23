/**
 * Created by bhuvanem on 12/17/2014.
 */
define(['angular', 'app','js/controllers/homecontroller.js','js/controllers/eventscontroller.js','js/controllers/eventcontroller.js','js/controllers/addeventcontroller.js'], function(angular,app,HomeController) {

    return app.config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/Home', {
            templateUrl: 'templates/home.html',
            controller: 'HomeController'
        });
        
        $routeProvider.when('/Events/:EventID',{
            templateUrl:'templates/eventdetails.html',
            controller:'EventController'
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