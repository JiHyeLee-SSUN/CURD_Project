/***
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2020-01-04
 Time: 오후 8:05
 desc: 좋아요 DAO

 Created by altjd815@gmail.com 
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 ***/
package com.board.dao;

import com.board.domain.LikeVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class LikeDAOImpl implements LikeDAO{

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    private static final String NAMESPACE ="com.board.mapper.likeMapper";

    @Override
    public int create(int bno, String uid) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("bno",bno);
        map.put("uid",uid);

        int result = sqlSessionTemplate.insert(NAMESPACE+".create", map);
        return result;
    }

    @Override
    public LikeVO read(int bno, String uid) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("bno",bno);
        map.put("uid",uid);
        return sqlSessionTemplate.selectOne(NAMESPACE + ".read",map);
    }

    @Override
    public int delete(int bno, String uid) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("bno",bno);
        map.put("uid",uid);
        return sqlSessionTemplate.delete(NAMESPACE+".delete",map);
    }
}
