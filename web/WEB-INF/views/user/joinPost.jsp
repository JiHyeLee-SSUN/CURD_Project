<%--
  Created by IntelliJ IDEA.
  Project : curd
  User: leejihye
  Date: 2019-10-15
  Time: 오전 3:16
  desc: 회원가입 성공 페이지
    
  Created by altjd815@gmail.com 
  Blog : https://2-jissun.tistory.com/
  Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

<div class="panel-body panel-body-content text-center">
    <c:if test="${auth_check != 1 }">
        <p class="lead">
            <strong><u>${uVO.email }</u></strong> 로 인증 요청 메일을 보냈습니다.
            <br> 해당 이메일을 확인 하시고, 인증 확인 링크를 눌러 주시기 바랍니다.
        </p>
        <p>이메일 인증이 완료 되지 않을 경우 사이트 이용에 제한이 있습니다.</p>
        <p>
            <strong>※ 서비스에 따라 스팸으로 분류 되있을 수도 있습니다. 스팸함도 꼭 확인해 주시기 바랍니다.</strong>
        </p>
    </c:if>
    <c:if test="${auth_check == 1 }">
        <p class="lead" style="color: blue">
            <strong>${uVO.uid }</strong> 님 이메일 인증이 완료되었습니다!
            <br> 더욱 다양한 기능을 사용하실 수 있습니다.
        </p>
        <p>감사합니다.</p>
    </c:if>
    <a href="/user/login" class="btn btn-primary">로그인</a>
</div>

<%@ include file="../include/footer.jsp" %>
