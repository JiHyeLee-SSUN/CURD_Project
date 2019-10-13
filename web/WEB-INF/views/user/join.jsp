<%--
  Created by IntelliJ IDEA.
  Project : curd
  User: leejihye
  Date: 2019-10-07
  Time: 오후 5:31
  desc: 회원가입 양식 JSP 파일
    
  Created by altjd815@gmail.com 
  Blog : https://2-jissun.tistory.com/
  Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>
<link rel="stylesheet" href="/resources/css/join.css">

<div class="container">
    <div></div>
    <div>
        <div class="jumbotron" style="padding-top: 20px;" align="center">
            <form action="/user/joinPost" method="post">
                <h3>회원가입</h3>
                <div class="form-group" style="display: flex;">
                    <input class="form-control" type="text" id="uid" name="uid" style="flex: 3;" placeholder="아이디"
                           autocomplete="off">
                    <div style="flex: 1;"></div>
                    <button type="button" class="btn btn-primary uid-check" style="flex: 1;">중복 검사</button>
                </div>
                <div class="form-group">
                    <span id="id-msg"></span>
                </div>

                <div class="form-group">
                    <input class="form-control" type="password" id="pw" name="pw"
                           placeholder="비밀번호 (특수문자 / 문자 / 숫자 포함 형태의 8~15자리 이내">
                </div>
                <div class="form-group">
                    <span id="pw-msg" style="color: red"></span>
                </div>
                <div class="form-group">
                    <input class="form-control" type="password" id="pwck" name="pwck" placeholder="비밀번호 확인">
                </div>
                <div class="form-group" align="left">
                    <span class="fa fa-check pw-default" style="margin-right: 10px;" id="password-ck-btn"
                          aria-hidden="true"></span>
                    <em class="pw-default" id="password-ck">password check</em>
                </div>
                <div class="form-group">
                    <input class="form-control" type="text" id="name" name="name" placeholder="이름" autocomplete="off">
                </div>
                <div class="form-group">
                    <span id="name-msg" style="color: red"></span>
                </div>
                <div class="form-group" style="text-align: center">
                    <div class="btn-group btn-group-toggle" data-toggle="buttons">
                        <%--<div class="btn-group" data-toggle="buttons">--%>
                        <label class="btn btn-primary active">
                            <input type="radio" name="gender" value="남자" checked="checked">남자
                        </label>
                        <label class="btn btn-primary">
                            <input type="radio" name="gender" value="여자">여자
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <input class="form-control" type="email" id="email" name="email" placeholder="이메일"
                           autocomplete="off">
                </div>
                <div class="form-group">
                    <span id="email-msg"></span>
                </div>
                <input id="submit-btn" class="btn btn-primary form-control" disabled="disabled" type="submit"
                       value="회원가입">
            </form>
        </div>
    </div>
    <div></div>
</div>
<script type="text/javascript" src="/resources/js/user/join.js"></script>
<%@ include file="../include/footer.jsp" %>
