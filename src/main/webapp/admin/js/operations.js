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

function qtdeLikes(postid) {
    $.getJSON('like?op=qtde&postid=' + postid, function (data) {
        $("#" + postid).find('#likes').first().html(data.msg);
    });
}

function isLiked(postid) {
    qtdeLikes(postid);
    $.getJSON('like?op=verify&postid=' + postid, function (data) {
        if (data.msg === "true") {
            $("#" + postid).find('#bt').first().html('curtiu');
        } else {
            $("#" + postid).find('#bt').first().html('curtir');
        }
    });
}

function like(postid) {
    $.getJSON('like?op=toogle&postid=' + postid, function (data) {
        if (data.msg === "like") {
            $("#" + postid).find('#bt').first().html('curtiu');
        } else {
            $("#" + postid).find('#bt').first().html('curtir');
        }
        qtdeLikes(postid);
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
