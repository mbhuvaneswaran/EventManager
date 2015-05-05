/**
 * Created by bhuvaneshwaranm on 29/4/15.
 */
define(['app','jquery'], function (app) {
        window.endpoint="http://localhost:8080/testService/Event"
        return app.service('EventService', [
            function($resource, $rootScope) {

                this.addEvent = function (event) {
                    return jQuery.ajax({
                        type: "POST",
                        url: endpoint + "/AddEvent?access_token="+EventApp.user.token+"",
                        contentType: 'application/json',
                        dataType: 'json',
                        data: angular.toJson(event)
                    })
                }
                this.getEvents=function(){
                    return jQuery.ajax({
                        type: "GET",
                        url: endpoint + "/GetEvents?access_token="+EventApp.user.token+"",
                        contentType: 'application/json',
                        dataType: 'json'

                    })
                }
                this.getEventDetails=function(id){
                    return jQuery.ajax({
                        type: "GET",
                        url: endpoint + "/GetEventDetails/"+id+"?access_token="+EventApp.user.token+"",
                        contentType: 'application/json',
                        dataType: 'json'

                    })
                }
            }]);
}
)

