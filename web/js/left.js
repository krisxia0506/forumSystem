//获取地址栏参数//可以是中文参数
function getUrlParam(key) {
    // 获取参数
    var url = window.location.search;
    // 正则筛选地址栏
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
    // 匹配目标参数
    var result = url.substr(1).match(reg);
    //返回参数值
    return result ? decodeURIComponent(result[2]) : null;
}

if (getUrlParam("keyword")) {
    document.getElementById("q").value = getUrlParam("keyword");
}

//跑马灯效果
function light() {
    var a = document.getElementsByClassName("a101");
    setTimeout(function () {
        a[0].style.background = " #ffd2d2";
    }, 0);
    setTimeout(function () {
        a[0].style.background = "";
    }, 1000);

    setTimeout(function () {
        a[1].style.background = " #ffd2d2";
    }, 1000);
    setTimeout(function () {
        a[1].style.background = "";
    }, 2000);

    setTimeout(function () {
        a[2].style.background = " #ffd2d2";
    }, 2000);
    setTimeout(function () {
        a[2].style.background = "";
    }, 3000);

    setTimeout(function () {
        a[3].style.background = " #ffd2d2";
    }, 3000);
    setTimeout(function () {
        a[3].style.background = "";
    }, 4000);

    setTimeout(function () {
        a[4].style.background = " #ffd2d2";
    }, 4000);
    setTimeout(function () {
        a[4].style.background = "";
    }, 5000);

    setTimeout(function () {
        a[5].style.background = " #ffd2d2";
    }, 5000);
    setTimeout(function () {
        a[5].style.background = "";
    }, 6000);


}

function a() {
    setInterval(light, 6000);
}

light();
a();
