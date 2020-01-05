/**
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2020-01-05
 Time: 오후 5:43
 desc: like javascript

 Created by altjd815@gmail.com
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 **/
$(function () {
    $(".like-button").on('click',function (event) {
        let target =$(event.target).find('img').attr('class');
        let bno = $("#bno").val();

        if(target == "unlike"){
            likeHandler(bno,event);
        } else if (target == "like"){
            unlikeHandler(bno,event);
        } else {
            alert("추천기능이 되지 않습니다. 문의메일을 보내주세요");
            console.log(target);
        }
    });
});

// 추천 ajax
function likeHandler(bno,event) {
    $.ajax({
        type    : "GET",
        url     : "/boards/like?bno=" + bno,
        dataType: "text",
        success : function (result) {
            likeRender(event);
            console.log("go likeRender");
        }, error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
}

// 추천 DOM
function likeRender(event) {
    let htmls ="";
    $(event.target).find('img').empty();
    htmls +="추천<"+"br"+">";
    htmls += "<img class=\"like\"";
    htmls += "src='/resources/img/like.png'";
    htmls += "alt=\"추천\">";
    $(event.target).html(htmls);
}

function unlikeHandler(bno,event) {
    $.ajax({
        type    : "GET",
        url     : "/boards/unlike?bno=" + bno,
        dataType: "text",
        success : function (result) {
            unlikeRender(event);
            console.log("go unlikeRender");
        }, error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }
    });
    
}

// 추천해제 DOM
function unlikeRender(event) {
    let htmls ="";
    $(event.target).find('img').empty();
    htmls +="추천<"+"br"+">";
    htmls += "<img class=\"unlike\"";
    htmls += "src='/resources/img/unlike.png'";
    htmls += "alt=\"비추천\">";
    $(event.target).html(htmls);
}