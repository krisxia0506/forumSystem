function CollectionButton(id) {
    var name = $(".collection").text();//已收藏 或 收藏
    var url
    if (name === "已收藏") {
        url = 'collect?action=delete&postId=' + id;
    } else if (name === "收藏") {
        url = 'collect?action=add&postId=' + id;
    }
    $.ajax({
        type: "get",
        async: false,
        url: url,
        dataType: "text",
        success: function (data) {
            if (data === "collectSuccess") {
                console.log('收藏成功');
                $(".collection").text('已收藏')
            } else if (data === "cancelCollectSuccess") {
                console.log('取消收藏成功');
                $(".collection").text('收藏')
            }
        }
    })

}

