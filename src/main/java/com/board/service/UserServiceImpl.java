/***
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2019-10-07
 Time: 오후 8:39
 desc: User service 오버라이드 메소드 재정의

 Created by altjd815@gmail.com 
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 ***/
package com.board.service;
import com.board.dao.UserDAO;
import com.board.domain.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDAO uDAO;

    @Override
    public void create(UserVO uVO) throws Exception {
        uDAO.create(uVO);

    }

    @Override
    public UserVO readByUid(String uid) throws Exception {
        return uDAO.readByUid(uid);
    }

    @Override
    public UserVO readyByEmail(String email) throws Exception {
        return uDAO.readyByEmail(email);
    }
}
