/**
 * Created by bhuvanem on 12/17/2014.
 */
define(['angular', 'app','controllers/homecontroller','controllers/taskscontroller'], function(angular, app,HomeController) {

    return app.config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/Home', {
            templateUrl: 'templates/home.html',
            controller: 'HomeController'
        });
        $routeProvider.when('/Tasks', {
            templateUrl: 'templates/tasks.html',
            controller: 'TaskController'
    });
        $routeProvider.when('/Tasks/:TaskID',{
            templateUrl:'template/taskDetail.html',
            controller:'TaskController'
        });

        $routeProvider.otherwise({redirectTo: '/Home'});
    }]);

});