<%--
  Created by IntelliJ IDEA.
  Project : curd
  User: leejihye
  Date: 2019-10-25
  Time: 오전 1:42
  desc: 게시판 글 작성 페이지
    
  Created by altjd815@gmail.com 
  Blog : https://2-jissun.tistory.com/
  Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../include/header.jsp" %>
<style>
    .fileDrop {
        width: 100%;
        height: 200px;
        border: 2px dotted #0b58a2;
    }
</style>

<div class="container" style="max-width: 70%;">
    <div class="panel-body">
        <h2 style="display: inline;">새 글 쓰기</h2>
        <br>
        <div class="form-group" style="margin-top: 1rem;">
            <form action="/boards/post" method="post" id="create-form" class="create-form">
                <fieldset class="form">
                    <div class="form-group  has-feedback">
                        <div class="form-group">
                            <input type="hidden" name="writer" value="${login.uid }">
                            <input class="form-control" type="text" name="title" placeholder="제목을 입력해 주세요.">
                        </div>
                        <div class="form-group">
                            <textarea class="form-control" name="content" style="height: 250px; resize: none"
                                      placeholder="내용을 입력해 주세요."></textarea>
                        </div>
                    </div>
                </fieldset>
                <hr color="gray">
                <div class="box-footer" style="background: #f8f8f8">
                    <button type="button" class="btn btn-primary listBtn" onclick="window.location.href='/boards'"><i class="fa fa-list"></i>목록</button>
                    <div class="pull-right">
                        <button type ="reset" class="btn btn-warning"><i class="fa fa-reply"></i>초기화</button>
                        <button type ="submit" class="btn btn-success"><i class="fa fa-save"></i>저장</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="../include/footer.jsp" %>
