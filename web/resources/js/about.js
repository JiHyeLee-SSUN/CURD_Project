/**
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2019-10-24
 Time: 오후 7:58
 desc: about.jsp 의 javascript파일

 Created by altjd815@gmail.com
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 **/

<!-- 관리자에게 메세지 보내기 -->
$(function () {

    $("#submit").on('click', function (event) {
        event.preventDefault();

        var name = $("#name").val();
        var email = $("#email").val();
        var content = $("#content").val();

        // null 체크
        if (name == null || name == "") {
            $("#name_msg").text("이름을 입력해주세요.");
        }
        if (email == null || email == "") {
            $("#email_msg").text("이메일을 입력해주세요..");
        }
        if (content == null || content == "") {
            $("#content_msg").text("내용을 입력해주세요.");
        }

        if (name == null || name == "" || email == null || email == "" || content == null || content == "") {
            return false;
        }

        // emailjs 라이브러리

        var data = {
            user_id: "user_5yyyznYChFz58nsclGE0O",
            service_id : "gmail",
            template_id: "template_ue2ggcV5",
            template_params : {
                "to_name"     : name,
                "from_name"   : email,
                "message_html": content
            }
        }

        $.ajax('https://api.emailjs.com/api/v1.0/email/send', {
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json'
        }).done(function() {

            $("#email_form").each(function () {
                this.reset();
            });
            alert('메일을 성공적으로 전송하였습니다.!');

        }).fail(function(error) {
            alert('에러가 발생했습니다.... ' + JSON.stringify(error));
        });
    });

    $(".form-group .form-control").on('click',function () {
        $(this).parent(".form-group").children("span").text("");
    });

})
