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
        <ul class="media-list" style="margin-top: 20px;">
            <li class="media">
                <div class="media-left">
                    <a href="#">
                        <img class="media-object" src="..." alt="IMG">
                    </a>
                </div>
                <div class="media-body">
                    <h4 style="display: inline;" class="media-heading">${login.uid }</h4>
                </div>
            </li>
        </ul>
        <div class="form-group">
            <form action="/boards/new" method="post" id="create-form" class="create-form">
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
                <div class="form-group" style="margin-top: 30px">
                    <label>아래 영역에 첨부하실 파일을 올려주세요.</label>
                    <div class="fileDrop">
                    </div>
                </div>
                <div class="box-footer" style="background: #f8f8f8">
                    <div>
                        <hr color="gray">
                    </div>

                    <ul class="mailbox-attachments clearfix uploadedList">
                    </ul>
                </div>
                <div class="box-footer" style="background: #f8f8f8">
                    <button type="button" class="btn btn-primary listBtn"><i class="fa fa-list"></i>목록</button>
                    <div class="pull-right">
                        <button type ="reset" class="btn btn-warning"><i class="fa -reply"></i>초기화</button>
                        <button type ="submit" class="btn btn-success"><i class="fa -save"></i>저장</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 첨부파일 템플릿 -->
<script id="attach-template" type="text/x-handlebars-template">
    <li>
			<span class="mailbox-attachment-icon has-img">
				<img src="{{imgSrc}}" alt="Attachment">
			</span>
        <div class="mailbox-attachment-info">
            <a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
            <a href="{{fullName}}" class="btn btn-default btn-xs pull-right del-btn">
                <i class="fa fa-fw fa-remove"></i>
            </a>
        </div>
    </li>
</script>

<!-- 첨부파일 업로드 -->
<script>
    // 파일  드랍 관련 기본 이벤트 취소
    $(".fileDrop").on("dragenter dragover", function (event) {
        event.preventDefault();
    });

    // 파일 드랍시 처리
    $(".fileDrop").on("drop", function (event) {
        event.preventDefault();

        var source = $("#attach-template").html();
        var template = Handlebars.compile(source);

        var files = event.originalEvent.dataTransfer.files;
        var file = files[0];

        var formData = new FormData();
        formData.append("file", file);

        $.ajax({
            type       : "POST",
            url        : "/boards/new/uploadFile",
            data       : formData,
            processData: false,
            contentType: false,
            dataType   : "text",
            success    : function (result) {
                var fileInfo = getFileInfo(result);
                var html = template(fileInfo);

                $(".uploadedList").append(html);
            }
        });
    });
</script>

<!-- 첨부된 파일 삭제 -->
<script>
    $(".uploadedList").on("click", ".del-btn", function (event) {
        event.preventDefault();

        var that = $(this);

        $.ajax({
            type    : "POST",
            url     : "/boards/deleteFile",
            data    : {fileName: that.attr("href")},
            dataType: "text",
            success : function (result) {
                console.log("RESULT: " + result);
                if (result == "deleted") {
                    that.closest("li").remove();
                }
            }
        });
    });
</script>

<!-- 글쓰기 -->
<script>
    $("#create-form").submit(function (event) {
        event.preventDefault();

        var that = $(this);
        var str = "";

        $(".uploadedList .del-btn").each(function (index) {
            str += "<input type='hidden' name='files[" + index + "]' value='" + $(this).attr("href") + "' >";
        });

        that.append(str);
        that.get(0).submit();
    });
</script>
<%@ include file="../include/footer.jsp" %>
