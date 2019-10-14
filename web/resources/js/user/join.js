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

$(function () {

    // uid 입력창 keyup
    uidObj.on('keyup', function () {
        uidResult = false;
        idMsg.text("");
        finalCheck();
    });
    // uid 중복검사 button click
    $(".uid-check").on('click', function () {
        joinIdCheck();
    });
    // pw 입력창 keyup
    pwObj.on('keyup', function () {
        pwCheck();
    });
    pwckObj.on('keyup', function () {
        pwCheck();
    });
    // 이름 입력창 keyup
    nameObj.on('keyup', function () {
        nameCheck();
    });
    // email 입력창 keyup
    emailObj.on('keyup', function () {
        joinEmailCheck();
    });
});

/* 1. ajax uid 중복 체크 */

let uidResult = false ;
let uidObj = $("#uid");
let idMsg = $("#id-msg");

function joinIdCheck() {
    uidResult = false;
    var uid = uidObj.val();
    if(!regIdType(uid)){
        idMsg.removeClass('id-msg-green').addClass('id-msg-red');
        idMsg.text("소문자,숫자로 시작하는 특수문자[ - , _ ]조합으로 6~12자리내에 구성하세요.");
        return;
    }
    $.ajax({
        type    : "GET",
        url     : "/user/joinUidCheck?uid=" + uid,
        dataType: "text",
        success : function (result) {
            if (result == "UID_DUP") {
                idMsg.removeClass('id-msg-green').addClass('id-msg-red');
                idMsg.text("이미 사용중인 아이디입니다.");

            } else if (uid == "" || uid == null) {
                idMsg.removeClass('id-msg-green').addClass('id-msg-red');
                idMsg.text("아이디는 필수항목입니다.");

            } else if (result == "SUCCESS") {
                uidResult = true;
                idMsg.removeClass('id-msg-red').addClass('id-msg-green');
                idMsg.text("멋진 아이디네요!");
            }
            finalCheck();

        }, error: function (request, status, error) {
            console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
        }

    });
}

/* 2.PW 일치 체크 */

let passwordCkBtn = $("#password-ck-btn");
let passwordCk = $("#password-ck");
let pwMsg = $("#pw-msg");
let pwObj = $("#pw");
let pwckObj = $("#pwck");
let pwResult = false;

function pwCheck() {

    pwResult = false;
    if ((pwObj.val() == pwckObj.val()) && regPasswordType(pwObj.val()) && regPasswordType(pwckObj.val())) {
        pwMsg.text("");
        passwordCkBtn.removeClass('pw-default').addClass('pw-correct-button');
        passwordCk.removeClass('pw-default').addClass('pw-correct');
        pwResult = true;
    } else {
        if (!regPasswordType(pwObj.val())) {
            pwMsg.text("비밀번호는 특수문자 / 영어대소문자 / 숫자 포함 형태의 8~15자리 이내여야 합니다.");
        } else {
            pwMsg.text("");
        }
        passwordCkBtn.removeClass('pw-correct-button').addClass('pw-default');
        passwordCk.removeClass('pw-correct').addClass('pw-default');
    }
    finalCheck();

}

/* 3. 이름 입력 check */

let nameObj = $("#name");
let nameMsg = $("#name-msg");
let nameResult = false;

function nameCheck() {
    if (nameObj.val() == "" || nameObj.val() == null) {
        nameMsg.text("이름은 필수항목입니다.");
        nameResult = false;
    } else {
        nameMsg.text("");
        nameResult = true;
    }
    finalCheck();
}

/* 4. email address 중복 체크 */

let emailObj = $("#email");
let emailResult = false;
let emailMsg = $("#email-msg");


function joinEmailCheck() {

    var email =emailObj.val();

    if(!regEmailType(email)){
        emailMsg.removeClass('email-msg-green').addClass('email-msg-red');
        emailMsg.text("형식에 맞지 않는 이메일입니다.");
        emailResult = false;
        finalCheck();
        return;
    }
    $.ajax({
        type    : "GET",
        url     : "/user/joinEmailCheck?email=" + email,
        dataType: "text",
        success : function (result) {
            if (result == "EMAIL_DUP") {
                emailMsg.removeClass('email-msg-green').addClass('email-msg-red');
                emailMsg.text("이미 사용중인 이메일입니다.");
                emailResult = false;
            } else if (email == "" || email == null) {
                emailMsg.removeClass('email-msg-green').addClass('email-msg-red');
                emailMsg.text("이메일은 필수항목입니다.");
                emailResult = false;
            } else if (result == "SUCCESS") {
                emailMsg.removeClass('email-msg-red').addClass('email-msg-green');
                emailMsg.text("멋진 이메일이네요!");
                emailResult = true;
            }
            finalCheck();
        }
    });
}

/* 5. 회원가입 submit 버튼 */

let submitBtn = $("#submit-btn");

function finalCheck() {
    if (uidResult && pwResult && nameResult && emailResult) {
        submitBtn.removeAttr("disabled");
    } else {
        submitBtn.attr("disabled", "disabled");
    }
    console.log("uidResult : "+uidResult + " pwResult : "+pwResult + " nameResult : "+nameResult + " emailResult : "+emailResult);
}

/* 6. input값  정규식 */
// 아이디 유효성
function regIdType(data){
    // 아이디 유효성 체크(소문자,숫자로 시작하는 특수문자(-,_)조합으로 6~12자리)
    let regex = /^[a-z0-9]+[a-z0-9_-]{6,12}$/g;
    return regex.test(data);
}

// 비밀번호 유효성
function regPasswordType(data) {
    // 비밀번호(패스워드) 유효성 체크 (문자, 숫자, 특수문자의 조합으로 8~15자리)
    let regex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}/;

    return regex.test(data);
}

//이메일 유효성
function regEmailType(data){
    let regex = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    return regex.test(data);
}



