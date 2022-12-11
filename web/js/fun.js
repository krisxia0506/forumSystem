function submitFun() {
    var form1 = document.getElementById("formQ");
    form1.submit();
}

function showTime() {
    var Timer = new Date();
    var h = Timer.getHours();
    var m = Timer.getMinutes();
    var s = Timer.getSeconds();
    var d = Timer.getDate();
    var mm = Timer.getMonth();
    var y = Timer.getFullYear();
    var strShow = "" + y + "-" + mm + "-" + d + " " + h + ":" + m + ":" + s;
    if (h < 6)
        strShow += " 熬夜"
    else if (h < 9)
        strShow += " 早上好"
    else if (h < 12)
        strShow += " 上午好"
    else if (h < 14)
        strShow += " 中午好"
    else if (h < 17)
        strShow += " 下午好"
    else if (h < 19)
        strShow += " 傍晚好"
    else if (h < 22)
        strShow += " 晚上好"
    else
        strShow += " 夜深了"
    myspan.innerText = strShow;
    setTimeout("showTime()", 1000)
};

function OnEnter(field) {
    if (field.value === field.defaultValue) {
        field.value = "";
    }
}

function OnExit(field) {
    if (field.value === "") {
        field.value = field.defaultValue;
    }
}

//managePost.jsp
function deletePost(postId) {
    if (confirm("确定删除吗？")) {
        window.location.href = "post?action=delete&postId=" + postId;
    }
}

function deleteType(postTypeId) {
    if (confirm("确定删除吗？")) {
        window.location.href = "theme?action=delete&postTypeId=" + postTypeId;
    }
}

function AjaxCheckUsername() {
    var name = $("[name=username]").val();
    var reg = new RegExp("[\\u4E00-\\u9FFF]+", "g");
    var show = $("#show");
    if (name === "") {
        show.html("用户不能为空")
    } else {
        $.ajax({
            type: "get",
            url: "checkName?username=" + name,
            dataType: "text",
            success: function (data) {
                if (data === "1") {
                    show.html("用户已存在！！！")
                    show.css("color", "red")
                } else {
                    if (name.length < 5 || name.length > 10 || reg.test(name)) {
                        show.html("用户名长度应为5-10位英文字符或数字");
                        show.css("color", "red")
                        $("[name=username]").focus();
                    } else {
                        show.html("用户名可用")
                        show.css("color", "green")
                    }
                }
        }
    })
    }
}

function RegisterCheckPassword() {
    var password = document.getElementById("password")
    var passwordCheck = document.getElementById("passwordCheck")
    var showPassword = document.getElementById("showPassword")
    var showCheckPassword = document.getElementById("showCheckPassword")
    if (password.value.length < 5 || password.value.length > 10) {
        showPassword.innerHTML = "密码长度应为5-10位";
        password.focus();
        return false;
    }
    if (password.value !== passwordCheck.value) {
        showCheckPassword.innerHTML = "两次密码不一致";
        passwordCheck.focus();
        return false;
    }
    return true;
}

function CheckReply() {
    if (document.getElementById("replyContent").value.length === 0) {
        alert('说点什么吧');
        return false;
    }
    return true;
}

function CheckComment() {
    if (document.getElementById("replyContent").value.length === 0) {
        alert('说点什么吧');
        return false;
    }
    return true;
}