function submitPost() {
    $('#place').val($('#pac-input').val());
    var frm = $('#newPost');

    $.post(frm.attr('action'), frm.serialize(), function (data) {
        if (data.sucess) {
            $('#newPost').each(function () {
                this.reset();
            });
            $('#pac-input').val("");
            $('#post-result').html(data.msg);
            updateFeed();
            window.location.reload(true);
        } else {
            $('#post-result').html(data.msg);
        }
    });

}

function updateFeed() {
    $.getJSON('newsFeed', function (data) {
        if (data.msg > loadPosts) {
            $("#updateButton").show();
        }
    });
}

function getSuggests() {
    $.ajax({
        url: "suggests"
    }).done(function (data) {        
        console.log(data)
    });
}



function getPrefer(user) {
    $.getJSON('prefer?op=get', function (data) {
        chooseTheme(data.font, data.colorTheme);
    });
}

function setPrefer(user, font, theme) {
    $.getJSON('prefer?op=set&font='+font+'&theme='+theme, function (data) {
        chooseTheme(data.font, data.colorTheme);
    });
}

/**
 * trocar o css aqui!
 */
function chooseTheme(font, themeName) {
    //todo
    console.log(font+" -- "+themeName);    
}

function find(q) {    
    $.getJSON('findUser?q='+q.value, function (data) {
        listUsers(data);
    });
}

function listUsers(json) {    
    var html = "<ul>";
    for (var i = 0; i < json.length; i++) {
        html+="<li><a href='profile?user="+json[i].email+"'>"+json[i].name+"</a></li>";
    }
    if(json.length === 0){
        html+="<li>Nenhum resultado</li>";
    }
    html+="</ul>";
    $("#resultado").html(html);
}

$(document).ready(function () {    
    updateFeed();
    getSuggests();
    $("#updateButton").hide();
    setInterval(function () {
        updateFeed()
    }, 1000 * 60 * 0.3);

});
