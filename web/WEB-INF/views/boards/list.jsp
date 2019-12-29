<%--
  Created by IntelliJ IDEA.
  Project : curd
  User: leejihye
  Date: 2019-10-25
  Time: 오전 1:22
  desc: 게시판 글 조회 페이지
    
  Created by altjd815@gmail.com 
  Blog : https://2-jissun.tistory.com/
  Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../include/header.jsp" %>

<!-- 검색 -->
<div class="container">
    <div class="panel-body">
        <div class="nav" role="navigation" style="margin-bottom: 10px">
            <a href="/boards/new" class="create btn btn-success btn-wide pull-right">
                <i class="fa fa-pencil"></i> 새 글 쓰기
            </a>
            <h2>게시판</h2>
            <div class="category-filter-wrapper">
                <div class="category-filter-query pull-right">
                    <div class="input-group input-group-sm">
                        <input type="search" name="query" id="query" class="form-control" placeholder="검색어"/>
                        <span class="input-group-btn">
                            <button id="search-btn" class="btn btn-default">
                                <i class="fa fa-search"></i>
                            </button>
                        </span>
                    </div>
                </div>

                <ul class="list-sort pull-left">
                    <li>
                        <a id="bno" class="category-sort-link active">최신순</a>
                    </li>
                    <li>
                        <a id="likecnt" class="category-sort-link ">추천순</a>
                    </li>
                    <li>
                        <a id="replycnt" class="category-sort-link ">댓글순</a>
                    </li>
                    <li>
                        <a id="viewcnt" class="category-sort-link ">조회순</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <!-- 테이블 -->
    <div class='box-body'>
        <div class="table-responsive">
            <table class="table table-hover">
                <tr>
                    <th width="550px">제목</th>
                    <th width="100px">댓글</th>
                    <th width="100px">추천</th>
                    <th width="100px">조회</th>
                    <th colspan="2">작성자</th>
                </tr>
                <c:forEach var="bVo" items="${list }">
                    <tr>
                        <td rowspan="2" style=" color:gray;">
                            &nbsp;&#35; <i>${bVo.bno }</i>&nbsp;&nbsp;
                                ${bVo.title }
                            <a ></a>
                        </td>
                        <td rowspan="2">
                            <i class="fa fa-comment"></i> ${bVo.replycnt }
                        </td>
                        <td rowspan="2">
                            <i class="fa fa-heart"></i> ${bVo.likecnt }
                        </td>
                        <td rowspan="2">
                            <i class="fa fa-eye"></i> ${bVo.viewcnt }
                        </td>

                        <td width="200px">${bVo.writer }</td>
                    </tr>
                    <tr>
                        <td width="200px">${bVo.regdate }</td>
                    </tr>
                </c:forEach>

            </table>
        </div>
        <%--<div align="center" style="font-size:4em;color: gray;">

            글이 없습니다.

        </div>--%>
    </div>
</div>


<%@ include file="../include/footer.jsp" %>