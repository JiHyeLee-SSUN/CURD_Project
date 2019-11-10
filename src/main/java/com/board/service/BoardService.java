package com.board.service;

import com.board.domain.BoardVO;

import java.util.List;

public interface BoardService {

    public void create(BoardVO bVO) throws Exception;

    public BoardVO read(int bno) throws Exception;

    public List<BoardVO> readAll() throws Exception;

    public BoardVO readNoViewcnt(int bno) throws Exception;

    public void update(BoardVO bVO) throws Exception;

    public void delete(int bno) throws Exception;

}
