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
import com.board.dto.LoginDTO;

import java.util.Date;

public interface UserService {

    // ID 중복 조회
    public UserVO readByUid(String uid) throws Exception;
    // Email 중복 조회
    public UserVO readyByEmail(String email) throws Exception;
    // 회원 가입
    public void createByUser(UserVO userVO) throws Exception;
    // 인증메일 승인시 상태변경
    public void updateAuthStatus(UserVO uVO) throws Exception;

    // 일반 로그인
    public UserVO login(LoginDTO lDTO) throws Exception;
    // 세션 체크
    public UserVO readForCheckSession(String cookieValue) throws Exception;
    // 쿠키사용 업데이트
    void updateForCookie(String uid, String sessionKey, Date sessionlimit) throws Exception;
}
