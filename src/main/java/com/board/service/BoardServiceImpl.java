/***
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2019-10-28
 Time: 오전 12:37
 desc:

 Created by altjd815@gmail.com 
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 ***/
package com.board.service;

import com.board.dao.BoardDAO;
import com.board.domain.BoardVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImpl implements  BoardService {

    private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

    @Autowired
    private BoardDAO bDAO;

    @Override
    public void create(BoardVO bVO) throws Exception {
        bDAO.create(bVO);
    }

    @Transactional
    @Override
    public BoardVO read(int bno) throws Exception {
        bDAO.updateViewcnt(bno, 1);
        return bDAO.read(bno);
    }

    @Override
    public List<BoardVO> readAll() throws Exception {
        return bDAO.readAll();
    }

    @Override
    public BoardVO readNoViewcnt(int bno) throws Exception {
        return bDAO.read(bno);
    }

    @Override
    public void update(BoardVO bVO) throws Exception {
        bDAO.update(bVO);
    }

    @Override
    public void delete(int bno) throws Exception {
        bDAO.delete(bno);
    }
}
