<%--
  Created by IntelliJ IDEA.
  Project : curd
  User: leejihye
  Date: 2019-10-07
  Time: 오후 2:13
  desc: header file
    
  Created by altjd815@gmail.com 
  Blog : https://2-jissun.tistory.com/
  Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>CRUD BOARD</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%-- bootstrap--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" >
    <%-- //bootstrap--%>
    <script type="text/javascript" src="https://cdn.emailjs.com/sdk/2.3.2/email.min.js"></script>
    <%--jquery--%>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script>
        var url = location.href;
        var idxBoard = url.indexOf("board");
        var idxAbout = url.indexOf("about");

        if (idxBoard != -1) {
            $("#board").addClass("active");
            $("#home").removeClass("active");
        }
        if (idxAbout != -1) {
            $("#about").addClass("active");
            $("#home").removeClass("active");
        }
    </script>

</head>

<body>

<!-- nav -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">CURD Board</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02"
            aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor02">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/boards">Board</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/about">About</a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    메뉴
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <c:if test="${login.uid == null }">
                        <a class="dropdown-item" href="/user/login"><i class="fa fa-sign-in"></i> 로그인</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/user/join"><i class="fa fa-user"></i> 회원가입</a>
                    </c:if>

                    <c:if test="${login.uid != null }">
                        <a class="dropdown-item" href="/user/logout" onclick="return confirm('로그아웃 하시겠습니까?');">
                            <i class="fa fa-sign-out"></i> 로그아웃</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#"><i class="fa fa-user"></i> 내정보</a>
                    </c:if>
                </div>
            </li>
            <li style="padding: 10px">
                <h5 style="color: white;"></h5>
            </li>
            <li style="padding: 10px">
                <h5 style="color: white;">
                    <c:if test="${login.uid == null }">
                        GUEST님 반갑습니다.
                    </c:if>
                    <c:if test="${login.uid != null }">
                        ${login.uid }님 반갑습니다.
                    </c:if>
                </h5>
            </li>
        </ul>
    </div>
</nav>




