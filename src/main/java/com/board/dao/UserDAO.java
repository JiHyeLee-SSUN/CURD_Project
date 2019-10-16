package com.board.dao;

import com.board.domain.UserVO;

public interface UserDAO {
    //아이디 중복 조회
    public UserVO readByUid(String uid) throws Exception ;
    //이메일 중복 조회
    public UserVO readyByEmail(String email) throws Exception;
    // 회원가입
    public void createByUser(UserVO userVO) throws Exception;
    // 인증용 권한키 업데이트
    public void updateAuthKey(UserVO userVO) throws Exception;
}
