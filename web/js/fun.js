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
function deleteNews(id) {
    if (confirm("确定删除吗？")) {
        window.location.href = "news?action=del&id=" + id;
    }
}

function registerCheck () {
    var name = $("[name=username]").val();
    if (name===""){
        $("#show").html("用户不能为空")
    }else {
    $.ajax({
        type:"get",
        url:"checkName?username="+name,
        dataType:"text",
        success:function (data) {
            //alert(data);
            if (data==="1"){
                $("#show").html("用户已存在！！！")
            }else {
                $("#show").html("用户名可用")
            }
        }
    })}
};