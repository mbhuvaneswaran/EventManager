/**
 * Created by bhuvanem on 12/17/2014.
 */
require.config({

        paths: {
            angular: "../bower_components/angular/angular",
            angularResource: "../bower_components/angular-resource/angular-resource",
            angularRoute: "../bower_components/angular-route/angular-route",
            bootstrap:"../bower_components/bootstrap/dist/js/bootstrap.min",
            jquery:"../bower_components/jquery/dist/jquery.min",
            dtp:"../bower_components/eonasdan-bootstrap-datetimepicker/src/js/bootstrap-datetimepicker",
            moment:"../bower_components/moment/min/moment.min",
            tooltip:"../bower_components/bootstrap/js/tooltip",
            'async':'../bower_components/requirejs-plugins/src/async',
            'login':'utility/login'
        },
        shim: {

            'angularRoute': ['angular'],
            'bootstrap':['jquery'],
            'dtp':['bootstrap','moment'],
            'tooltip':['bootstrap'],
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
    'angular','angularRoute','app','routes','login'
    ],
    function(angular,angularRoute,app,routes){
        var eventApp;
       localStorage.getItem('EventApp')?(eventApp=JSON.parse(localStorage.getItem('EventApp'))):"";

        function afterLogin(details,token){

                debugger;
                details.token=token;
                window.EventApp={user:details};
                localStorage.setItem("EventApp",JSON.stringify(EventApp))
            $("#userImage")[0].src=details.picture;
                var $html = angular.element(document.getElementsByTagName('html')[0]);

                angular.element().ready(function() {
                    angular.resumeBootstrap([app['name']]);
                });

        }
        if(eventApp && eventApp.user){
            validateToken(eventApp.user.token,afterLogin)
        }
        else{
            login(afterLogin);
        }


    }


)

