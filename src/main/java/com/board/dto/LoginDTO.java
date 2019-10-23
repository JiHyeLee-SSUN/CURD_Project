/***
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2019-10-20
 Time: 오후 7:23
 desc: 로그인 정보 DTO 클래스

 Created by altjd815@gmail.com 
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 ***/
package com.board.dto;

public class LoginDTO {
    private String uid;
    private String pw;
    private boolean useCookie;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public boolean isUseCookie() {
        return useCookie;
    }

    public void setUseCookie(boolean useCookie) {
        this.useCookie = useCookie;
    }

    @Override
    public String toString() {
        return "LoginDTO [uid=" + uid + ", pw=" + pw + ", useCookie=" + useCookie + "]";
    }
}
