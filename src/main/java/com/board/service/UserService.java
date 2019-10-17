/***
 Created by IntelliJ IDEA.
 Project : CURD_Board
 User: leejihye
 Date: 2019-10-07
 Time: 오후 1:26
 desc: User관련 기능 interface

 Created by altjd815@gmail.com
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 ***/

package com.board.service;
import com.board.domain.UserVO;

public interface UserService {

    public UserVO readByUid(String uid) throws Exception;
    public UserVO readyByEmail(String email) throws Exception;
    public void createByUser(UserVO userVO) throws Exception;
    public void updateAuthStatus(UserVO uVO) throws Exception;
}
