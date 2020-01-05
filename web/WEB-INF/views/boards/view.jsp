<%--
  Created by IntelliJ IDEA.
  Project : curd
  User: leejihye
  Date: 2020-01-02
  Time: 오전 9:14
  desc:
    
  Created by altjd815@gmail.com 
  Blog : https://2-jissun.tistory.com/
  Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>
<style>
    .article::before {
        display: inline-block;
        background: #ccc;
        content: "";
        width: 1px;
        height: 12px;
        margin: 0 10px 0 6px;
    }

    .fa {
        font-size: 1rem;
    }

    .like-container {
        text-align: center;
        width: 100%;
        padding-right: 15px;
        padding-left: 15px;
        margin-right: auto;
        margin-left: auto;
    }
</style>

<!-- 글 보기 -->
<div class="container" style="margin-bottom: 50px; max-width: 70%;">
    <div class="panel-body" style="margin-top: 1em;">
        <table class="table " style="text-align: center; border: 1px solid #dddddd">
            <thead class="thead-light">
            <tr>
                <th colspan="4">${bVO.title }</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th colspan="2">
                    <input type="hidden" id="bno" value="${bVO.bno }">
                    ${bVO.writer }
                    <span class="article regdate">${bVO.regdate }</span>
                </th>
                <td colspan="2">
                    <span class="article viewcount"><i class="fa fa-eye fa-2x"></i> ${bVO.viewcnt }</span>
                    <span class="article like"><i id="like" class="fa fa-heart fa-2x"></i> ${bVO.likecnt }</span>
                    <span class="article replycnt"><i class="fa fa-comment fa-2x"></i> ${bVO.replycnt }</span>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="left" height="200">
                    <div class="content" style="padding: 1rem;">
                        ${bVO.content }
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <c:if test="${login.uid == bVO.writer }">
    <div class="dropdown" align="left" style="margin-left: 10px">
        <a href="javascript://" data-toggle="dropdown">
            <i class="fa fa-cog fa-2x" data-toggle="tooltip" data-placement="left" data-original-title="게시물 설정"></i>
        </a>
        <ul class="dropdown-menu" role="menu">
            <li>
                <a href="/boards/${bVO.bno }/edit" class="edit">
                    <i class="fa fa-edit fa-fw"></i> 수정
                </a>
            </li>
            <li>
                <a id="del-btn" href="${bVO.bno }" class="delete">
                    <i class="fa fa-trash-o fa-fw"></i> 삭제
                </a>
            </li>
        </ul>
    </div>
    </c:if>

    <div class="like-container">
        <button type="button" class="btn btn-light like-button" style="border: solid 1px;">
            추천<br>
            <c:if test="${like.lno == null }">
                <img class="unlike" src="<c:url value="/resources/img/unlike.png" />" alt="비추천">
            </c:if>
            <c:if test="${like.lno != null }">
                <img class="like" src="<c:url value="/resources/img/like.png" />" alt="추천">
            </c:if>
        </button>
    </div>
</div>


<hr style="border-width: 1px; border-color: gray">

<!-- 댓글 쓰기 -->
<div class="container" style="max-width: 70%;">
    <div class="panel-body">
        <h3 style="display: inline;">댓글</h3>
        <h3 class="replycnt" style="display: inline;"></h3>
            <fieldset class="form">
                <div class="form-group  has-feedback">
                    <div class="form-group">
                        <input id="replytext" class="form-control" type="text" name="title" placeholder="내용을 입력해 주세요.">
                    </div>
                </div>
                <div class="nav" role="navigation">
                    <fieldset class="buttons">
                        <input id="reply-submit-btn" type="submit" class="create btn btn-success btn-wide pull-right"
                               value="등록">
                    </fieldset>
                </div>
            </fieldset>
    </div>
</div>

<div class="container" style="max-width: 70%;">
    <!-- 댓글 목록 -->
    <div>
        <ul id="replies" class="list-group">
        </ul>
    </div>

    <!-- 댓글 페이징 -->
    <div align="center" style="margin-bottom: 20px">
        <nav>
            <ul class="pagination">
            </ul>
        </nav>
    </div>
</div>

<script type="text/javascript" src="/resources/js/boards/like.js"></script>
<%@ include file="../include/footer.jsp" %>