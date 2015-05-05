/**
 * Created by bhuvanem on 12/17/2014.
 */
require.config({

        paths: {
            angular: "../thirdparty/angular/angular",
            angularResource: "../thirdparty/angular/angular-resource",
            angularRoute: "../thirdparty/angular/angular-route",
            jquery: "../thirdparty/jquery/jquery-1.8.3.min",
            foundation: "../thirdparty/foundation/js/foundation.min"
        },
        shim: {

            'angularRoute': ['angular'],
            'foundation': ['jquery'],
            'angular':{
                exports:"angular"
            }
        },
    priority: [
        "angular"
    ]
    });
window.name = "NG_DEFER_BOOTSTRAP!";
require([
    'angular','angularRoute','app','routes'
    ],
    function(angular,angularRoute,app,routes){

        var $html = angular.element(document.getElementsByTagName('html')[0]);

        angular.element().ready(function() {
            angular.resumeBootstrap([app['name']]);
        });
    }


)

