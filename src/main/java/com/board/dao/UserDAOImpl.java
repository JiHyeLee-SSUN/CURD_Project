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
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    protected SqlSessionTemplate sqlSessionTemplate;

    private static final String NAMESPACE ="com.board.mapper.userMapper";

    @Override
    public UserVO readByUid(String uid)  {
        return sqlSessionTemplate.selectOne(NAMESPACE+".readByUid", uid);
    }
}
