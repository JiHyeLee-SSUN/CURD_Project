/***
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2019-10-28
 Time: 오전 12:21
 desc: BoardDAO 파일

 Created by altjd815@gmail.com 
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 ***/
package com.board.dao;

import com.board.domain.BoardVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.print.attribute.standard.MediaSize;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDAOImpl implements BoardDAO {

    private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);

    private static final String NAMESPACE ="com.board.mapper.boardMapper";

    @Autowired
    private final SqlSessionTemplate sqlSessionTemplate;

    public BoardDAOImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public void create(BoardVO bVO) throws Exception{
        sqlSessionTemplate.insert(NAMESPACE + ".create", bVO);
    }

    @Override
    public BoardVO read(int bno) throws Exception {
        return sqlSessionTemplate.selectOne(NAMESPACE+".read",bno);
    }

    @Override
    public List<BoardVO> readAll() throws Exception {
        return sqlSessionTemplate.selectList(NAMESPACE+".readAll");
    }

    @Override
    public void update(BoardVO bVO) throws Exception {
        sqlSessionTemplate.update(NAMESPACE+".update", bVO);
    }

    @Override
    public void updateViewcnt(int bno, int cnt) throws Exception {
        Map<String, Integer> map = new HashMap<>();
        map.put("bno", bno);
        map.put("cnt", cnt);

        sqlSessionTemplate.update(NAMESPACE+".updateViewcnt", map);
    }

    @Override
    public void updateReplycnt(int bno, int cnt) throws Exception {
        Map<String, Integer> map = new HashMap<>();
        map.put("bno", bno);
        map.put("cnt", cnt);

        sqlSessionTemplate.update(NAMESPACE+".updateReplycnt", map);

    }

    @Override
    public void updateLikecnt(int bno, int cnt) throws Exception {
        Map<String, Integer> map = new HashMap<>();
        map.put("bno", bno);
        map.put("cnt", cnt);
        sqlSessionTemplate.update(NAMESPACE +".updateLikecnt", map);
    }

    @Override
    public void delete(int bno) throws Exception {
        sqlSessionTemplate.delete(NAMESPACE +".delete", bno);
    }

}
