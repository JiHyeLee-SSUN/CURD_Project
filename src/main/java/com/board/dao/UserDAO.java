package com.board.dao;

import com.board.domain.UserVO;

public interface UserDAO {
    //아이디 조회
    public UserVO readByUid(String uid) ;
}
