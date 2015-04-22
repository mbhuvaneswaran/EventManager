/**
 * Created by bhuvanem on 12/17/2014.
 */
require.config({

        paths: {
            angular: "../bower_components/angular/angular",
            angularResource: "../bower_components/angular-resource/angular-resource",
            angularRoute: "../bower_components/angular-route/angular-route",
            bootstrap:"../bower_components/bootstrap/dist/js/bootstrap.min.js"
        },
        shim: {

            'angularRoute': ['angular'],
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

