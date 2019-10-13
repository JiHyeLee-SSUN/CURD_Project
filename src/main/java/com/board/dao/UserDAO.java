package com.board.dao;

import com.board.domain.UserVO;

public interface UserDAO {
    //아이디 중복 조회
    public UserVO readByUid(String uid) ;
    //이메일 중복 조회
    public UserVO readyByEmail(String email);
}
