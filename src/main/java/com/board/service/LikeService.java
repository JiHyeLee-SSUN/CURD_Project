package com.board.service;

import com.board.domain.LikeVO;

public interface LikeService {

    public int create(int bno, String uid) throws Exception;

    public LikeVO read(int bno, String uid) throws Exception;

    public int delete(int bno, String uid) throws Exception;
}
