function isFollowing(email) {   
    $.getJSON('follow?op=verify&email=' + email, function (data) {
        if (data.msg === "true") {
            $("#bt").html('seguindo');
        } else {
            $("#bt").html('seguir');
        }
    });
}

function getNfollowers(email) {    
    $.getJSON('follow?op=qtde&email=' + email, function (data) {      
            $("#qtde").html(data.msg);        
    });
}


function follow(email){
    $.getJSON('follow?op=toogle&email=' + email, function (data) {
        if (data.msg === "follow") {
            $("#bt").html('seguindo');
        } else {
            $("#bt").html('seguir');
        }
        getNfollowers(email);
    });
}

$(document).ready(function () {

});
