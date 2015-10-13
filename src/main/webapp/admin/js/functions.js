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