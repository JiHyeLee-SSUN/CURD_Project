package com.board.dao;

import com.board.domain.BoardVO;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface BoardDAO {

    //게시글
    public void create(BoardVO bVO) throws Exception;

    public BoardVO read(int bno) throws Exception;

    public List<BoardVO> readAll() throws Exception;

    public void update(BoardVO bVO) throws Exception;

    public void updateViewcnt(int bno, int cnt) throws Exception;

    public void updateReplycnt(int bno, int cnt) throws Exception;

    public void updateLikecnt(int bno, int cnt) throws Exception;

    public void delete(int bno) throws Exception;



    //파일 추가
    public void createAttach(String fileName) throws Exception;


}
