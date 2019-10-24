<%--
  Created by IntelliJ IDEA.
  Project : curd
  User: leejihye
  Date: 2019-10-24
  Time: 오후 4:48
  desc: 글쓴이 소개글
    
  Created by altjd815@gmail.com 
  Blog : https://2-jissun.tistory.com/
  Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="./include/header.jsp" %>

<div class="container" style="margin-bottom: 50px; max-width: 60%;">
    <div class="jumbotron">
        <div align="center">
            <img width="30%" class="img-circle" src="<c:url value="/resources/img/profile.jpg" />" alt="프로필사진">
        </div>
        <div class="jumbotron" align="center">
            <span style="font-size: 3em; font-weight: bold;">About</span>
            <div>
                <hr color="gray">
                <span style="font-size: 1em; letter-spacing: 2px; word-spacing: 4px">
						저는 공주대학교 정보통신학과를 졸업하였으며(2017), 학부 시절 경험한 프로그래밍에 흥미를 느껴 서울로 상경해 웹개발 국비지원 과정을 이수했습니다.(2019)
						설계한 코드에 따라 결과가 달라지고 무한한 가능성을 보여주는 점에서 매력을 느꼈고, 각종언어로 프로래밍 과제와 팀프로젝트를 한 경험이 지금의 제 진로에 많은 영향을 주었다고 생각합니다.<br><br>
						신입인 만큼 기본기에 충실해야 된다 생각해 알고리즘과 자료구조의 공부에 힘쓰고 있습니다. 단순히 기술적인 부분만 공부하는 것에 그치지 않고,
						제 실력을 기록하며 작은 지식이나마 나누는 것 또한 가치가 있다고 생각하여 블로그 활동도 하고 있습니다.
					</span>
            </div>
            <br>
            <div>
                <a href="https://2-jissun.tistory.com/"><img width="10%" class="img-circle" src="<c:url value="/resources/img/tistory.png" />" alt="티스토리링크"></a>
            </div>
        </div>
    </div>
    <div class="jumbotron">
        <div align="center" style="margin-bottom: 40px">
            <span style="font-size: 3em; font-weight: bold;">Contact Me</span>
        </div>
        <hr color="gray">
        <form id ="email_form">
            <div class="form-group">
                <label for="name">이름</label>
                <input type="email" class="form-control" id="name" placeholder="이름을 입력해주세요.">
                <span id="name_msg" style="color: red"></span>
            </div>
            <div class="form-group">
                <label for="email">이메일 주소</label>
                <input type="email" class="form-control" id="email" placeholder="이메일을 입력해주세요.">
                <span id="email_msg" style="color: red"></span>
            </div>
            <div class="form-group">
                <label for="content">내용</label>
                <textarea rows="5" class="form-control" id="content" placeholder="내용을 입력해주세요."></textarea>
                <span id="content_msg" style="color: red"></span>
            </div>
            <div class="form-group" align="right">
                <button id="submit" type="submit" class="btn btn-warning">보내기</button>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript" src="/resources/js/about.js"></script>
<%@ include file="./include/footer.jsp" %>
