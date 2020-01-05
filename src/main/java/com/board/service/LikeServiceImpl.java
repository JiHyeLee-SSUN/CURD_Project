/***
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2020-01-04
 Time: 오후 8:03
 desc: 좋아요 Service

 Created by altjd815@gmail.com 
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 ***/
package com.board.service;

import com.board.dao.BoardDAO;
import com.board.dao.LikeDAO;
import com.board.domain.LikeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    private LikeDAO likeDAO;

    @Autowired
    private BoardDAO bDAO;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class})
    public int create(int bno, String uid) throws Exception {
        bDAO.updateLikecnt(bno,1);
        return likeDAO.create(bno,uid);
    }

    @Override
    public LikeVO read(int bno, String uid) throws Exception {
        return likeDAO.read(bno,uid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class})
    public int delete(int bno, String uid) throws Exception {
        bDAO.updateLikecnt(bno,-1);
        return likeDAO.delete(bno, uid);
    }
}
