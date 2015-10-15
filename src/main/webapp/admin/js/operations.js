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

$(document).ready(function () {    
    updateFeed();
    getSuggests();    
    $("#updateButton").hide();
    setInterval(function () {
        updateFeed()
    }, 1000 * 60 * 0.3);

});
