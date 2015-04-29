/**
 * Created by bhuvaneshwaranm on 28/4/15.
 */
var OAUTHURL    =   'https://accounts.google.com/o/oauth2/auth?';
var VALIDURL    =   'https://www.googleapis.com/oauth2/v1/tokeninfo?access_token=';
var SCOPE       =   'email profile https://www.googleapis.com/auth/calendar';
var CLIENTID    =   '653430833410-b28rjer2lsmho0bb5imgermlo7jmmbdc.apps.googleusercontent.com';
var REDIRECT    =   location.protocol+'//'+location.hostname+(location.port ? ':'+location.port: '')+"/EventManager";
var LOGOUT      =   'http://accounts.google.com/Logout';
var TYPE        =   'token';
var _url        =   OAUTHURL + 'scope=' + SCOPE + '&client_id=' + CLIENTID + '&redirect_uri=' + REDIRECT + '&response_type=' + TYPE;
var acToken;
var tokenType;
var expiresIn;
var user;
var loggedIn    =   false;

function login(callback) {
    var win         =   window.open(_url, "windowname1", 'width=800, height=600');

    var pollTimer   =   window.setInterval(function() {
        try {
            console.log(win.document.URL);
            if (win.document.URL.indexOf(REDIRECT) != -1) {
                window.clearInterval(pollTimer);
                var url =   win.document.URL;
                acToken =   gup(url, 'access_token');
                tokenType = gup(url, 'token_type');
                expiresIn = gup(url, 'expires_in');
                win.close();

                validateToken(acToken,callback);
            }
        } catch(e) {
        }
    }, 500);
}

function validateToken(token,callback) {
    $.ajax({
        url: VALIDURL + token,
        data: null,
        success: function(responseText){
            if(responseText.error){
                console.log(responseText.error_description);
                login(callback);
            }
            else{
                getUserInfo(callback,token);
                loggedIn = true;
            }


        },
        error:function(response){
          login(callback);
        },
        dataType: "jsonp"
    });
}

function getUserInfo(callback,token) {
    $.ajax({
        url: 'https://www.googleapis.com/oauth2/v1/userinfo?access_token=' + token,
        data: null,
        success: function(resp) {
           callback(resp,token);
        },
        dataType: "jsonp"
    });
}

//credits: http://www.netlobo.com/url_query_string_javascript.html
function gup(url, name) {
    name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
    var regexS = "[\\#&]"+name+"=([^&#]*)";
    var regex = new RegExp( regexS );
    var results = regex.exec( url );
    if( results == null )
        return "";
    else
        return results[1];
}