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

function find(q) {
    $(".result").show();
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
        $(".result").hide();
        html+="<li>Nenhum resultado</li>";
    }
    html+="</ul>";
    $("#resultado").html(html);
}
