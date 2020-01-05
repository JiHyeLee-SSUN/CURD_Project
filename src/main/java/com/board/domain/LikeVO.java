/***
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2020-01-04
 Time: 오후 7:58
 desc: 좋아요 기능 VO

 Created by altjd815@gmail.com 
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 ***/
package com.board.domain;

public class LikeVO {
    private int lno;
    private int bno;
    private String uid;

    public int getLno() {
        return lno;
    }

    public void setLno(int lno) {
        this.lno = lno;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
