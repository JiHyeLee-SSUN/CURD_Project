package com.board.dao;

import com.board.domain.UserVO;

public interface UserDAO {
    public UserVO readByUid(String uid) throws Exception;
}
