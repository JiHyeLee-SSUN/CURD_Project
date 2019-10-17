/***
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2019-10-07
 Time: 오후 8:44
 desc: UserDAO 인터페이스 오버라이드 재정의

 Created by altjd815@gmail.com 
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 ***/
package com.board.dao;

import com.board.domain.UserVO;
import com.board.service.UserService;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.xml.stream.events.Namespace;


@Repository
public class UserDAOImpl implements UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;

    private static final String NAMESPACE ="com.board.mapper.userMapper";

    @Override
    public UserVO readByUid(String uid) throws Exception  {
        return sqlSessionTemplate.selectOne(NAMESPACE+".readByUid", uid);
    }

    @Override
    public UserVO readyByEmail(String email) throws Exception {
        return sqlSessionTemplate.selectOne(NAMESPACE+".readByEmail", email);
    }
    @Override
    public void createByUser(UserVO userVO) throws Exception {
        sqlSessionTemplate.insert(NAMESPACE+".createByUser",userVO);
    }

    @Override
    public void updateAuthKey(UserVO userVO) throws Exception {
        sqlSessionTemplate.update(NAMESPACE+".updateAuthKey",userVO);
        logger.info(userVO.getUid() + " AuthKey Update  : " + userVO.getAuthkey());
    }
    @Override
    public void updateAuthStatus(UserVO userVO) throws Exception {
        sqlSessionTemplate.update(NAMESPACE+".updateAuthStatus", userVO);
    }
}
