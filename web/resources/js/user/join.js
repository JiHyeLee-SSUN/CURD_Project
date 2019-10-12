/**
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2019-10-07
 Time: 오후 6:31
 desc: /views/user/join.jsp의 javascript 파일

 Created by altjd815@gmail.com
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 **/

var uidResult = false;
var pwResult = false;
var nameResult = false;
var emailResult = false;
var submitBtn = $("#submit-btn");
var uidObj = $("#uid");
var pwObj = $("#pw");
var pwckObj = $("#pwck");
var emailObj = $("#email");
var uid = $("#uid").val();
var idMsg = $("#id-msg");

$(function () {
    // uid 입력창 keyup시
    uidObj.on('keyup', function () {
        joinIdCheck();
    });

    // pw 입력창 keyup시
    pwObj.on('keyup', function () {
        pwCheck();
        finalCheck();
    });

    // pwck 입력창 keyup시
    pwckObj.on('keyup', function () {
        pwCheck();
        finalCheck();
    });

    // email 입력창 keyup시
    emailObj.on('keyup', function () {
        joinEmailCheck();
    });


    // name 입력창 keyup시
    nameObj.on('keyup',function () {
        nameCheck();
        finalCheck();
    });
});

<!-- 1. password 일치 확인 -->
function pwCheck() {
    var passwordCkBtn = $("#password-ck-btn");
    var passwordCk = $("#password-ck");

    if (pwObj.val() == pwckObj.val() && pwObj.val().length >= 4 && pwckObj.val().length >= 4) {
        passwordCkBtn.css("color", "#6ce945");
        passwordCk.css("color", "black");

        pwResult = true;
    } else {
        passwordCkBtn.css("color", "gray");
        passwordCk.css("color", "gray");

        pwResult = false;
    }
}

<!-- 2. ajax uid 중복 체크 -->
function joinIdCheck() {
    uid = $("#uid").val();
    idMsg = $("#id-msg");

    $.ajax({
        type    : "GET",
        url     : "/user/joinUidCheck?uid=" + uid,
        dataType: "text",
        success : function (result) {
            if (result == "UID_DUP") {
                idMsg.text("이미 사용중인 아이디입니다.");
                uidResult = false;
            } else if (uid == "" || uid == null) {
                idMsg.text("아이디는 필수항목입니다.");
                uidResult = false;
            } else if (result == "SUCCESS") {
                idMsg.text("");
                uidResult = true;
            }
            finalCheck();
            return uidResult;
        }, error:function(request,status,error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }

    });
}

<!-- 3. ajax email 중복 체크 -->
function joinEmailCheck() {
    var email = $("#email").val();
    var emailMsg = $("#email-msg");

    $.ajax({
        type    : "GET",
        url     : "/user/joinEmailCheck?email=" + email,
        dataType: "text",
        success : function (result) {
            if (result == "EMAIL_DUP") {
                emailMsg.text("이미 사용중인 이메일입니다.");
                emailResult = false;
            } else if (email == "" || email == null) {
                emailMsg.text("이메일은 필수항목입니다.");
                emailResult = false;
            } else if (result == "SUCCESS") {
                emailMsg.text("");
                emailResult = true;
            }
            finalCheck();
            return emailResult;
        },error:function(request,status,error){
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }
    });
}

<!-- 4. name null 체크 -->
var nameObj = $("#name");
var nameMsg = $("#name-msg");

function nameCheck() {
    if (nameObj.val() == "" || nameObj.val() == null) {
        nameMsg.text("이름은 필수항목입니다.");
        nameResult = false;
    } else {
        nameMsg.text("");
        nameResult = true;
    }

    return nameResult;
}

<!-- 1, 2, 3, 4 메소드 결과를 받아 "회원가입 버튼" 활성화 결정 -->
function finalCheck() {
    if (uidResult && pwResult && nameResult && emailResult) {
        submitBtn.removeAttr("disabled");
    } else {
        submitBtn.attr("disabled", "disabled");
    }
}

