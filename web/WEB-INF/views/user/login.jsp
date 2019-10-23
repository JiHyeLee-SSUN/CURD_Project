<%--
  Created by IntelliJ IDEA.
  Project : curd
  User: leejihye
  Date: 2019-10-20
  Time: 오전 1:57
  desc: 로그인 화면
    
  Created by altjd815@gmail.com 
  Blog : https://2-jissun.tistory.com/
  Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

<div class="container">
    <div class="col-lg-2 col-xl-2"></div>
    <div class="col-lg-auto col-xl-auto">
        <div class="jumbotron" style="padding-top: 20px;" align="center">
            <form action="/user/loginPost" method="post">

                <h3>로그인</h3>
                <div class="form-group">
                    <input class="form-control" type="text" id="uid" name="uid" value="${cookie.savedId.value }"
                           placeholder="아이디" autocomplete="off">
                </div>
                <div class="form-group">
                    <input class="form-control" type="password" id="pw" name="pw" placeholder="비밀번호">
                </div>
                <c:if test="${param.login_error == 1 }">
                    <div class="alert alert-warning" role="alert">
                            <span style="color: red">아이디 또는 비밀번호 오류</span>
                    </div>
                </c:if>
                <div class="form-group">
                    <input style="margin-right: 5px" type="checkbox" name="useCookie">로그인 유지
                    <input type="hidden" name="uri" value="${param.uri }">
                </div>
                <div class="form-group">
                    <input id="submit-btn" class="btn btn-primary form-control" type="submit" value="로그인">
                </div>
                <div class="form-group">
                    <h6>아직 회원이 아니신가요?</h6>
                    <input onclick="location.href='/user/join'" class="btn btn-primary form-control" type="button"
                           value="회원가입">
                </div>
            </form>
        </div>
    </div>
    <div class="col-lg-2 col-xl-2"></div>
</div>

<!-- uid, pw null 체크 -->
<script>
    $("#submit-btn").on('click',function () {
        var uid = $("#uid").val();
        var pw = $("#pw").val();

        if (uid == null || uid == "") {
            alert("아이디를 입력해주세요.");
            return false;
        } else if (pw == null || pw == "") {
            alert("비밀번호를 입력해주세요.");
            return false;
        }
    });
</script>

<%@ include file="../include/footer.jsp" %>
