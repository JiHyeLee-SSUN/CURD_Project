/***
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2019-10-25
 Time: 오전 1:11
 desc: 게시판 관련 controller

 Created by altjd815@gmail.com 
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 ***/
package com.board.controller;

import com.board.domain.BoardVO;
import com.board.domain.UserVO;
import com.board.service.BoardService;
import com.board.service.LikeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("boards")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private BoardService boardService;

    @Autowired
    private LikeService likeService;

    // 전체 글 목록 화면
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String readAll(Model model) throws Exception {
        model.addAttribute("list", boardService.readAll());
        return "boards/list";
    }

    //글 조회화면
    @RequestMapping(value = "read", method = RequestMethod.GET)
    public String readArticle(HttpServletRequest request, @RequestParam("bno") int bno, Model model) throws Exception {
        HttpSession session = request.getSession();
        UserVO uVO = (UserVO) session.getAttribute("login");
        String uid = uVO.getUid();

        model.addAttribute("bVO", boardService.read(bno));
        model.addAttribute("like", likeService.read(bno, uid));

        return "/boards/view";
    }

    // 추천기능
    @RequestMapping(value = "like", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> like(HttpServletRequest request, @RequestParam("bno") int bno) {
        HttpSession session = request.getSession();
        UserVO uVO = (UserVO) session.getAttribute("login");
        String uid = uVO.getUid();

        ResponseEntity<String> entity = null;
        try {
            if (likeService.create(bno,uid) > 0) {
                entity = new ResponseEntity<>("SUCCESS ", HttpStatus.OK);
                logger.info(String.valueOf(entity));
            }
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    // 추천해제기능
    @RequestMapping(value = "unlike", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> unlike(HttpServletRequest request, @RequestParam("bno") int bno) {
        HttpSession session = request.getSession();
        UserVO uVO = (UserVO) session.getAttribute("login");
        String uid = uVO.getUid();

        ResponseEntity<String> entity = null;
        try {
            if (likeService.delete(bno,uid) > 0) {
                entity = new ResponseEntity<>("SUCCESS DELETE", HttpStatus.OK);
                logger.info(String.valueOf(entity));
            }
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }


    // 글 쓰기 화면
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String createGET() throws Exception {
        return "boards/new";
    }

    @RequestMapping(value = "post", method = RequestMethod.POST)
    public String createPOST(BoardVO bVO) throws Exception {
        boardService.create(bVO);
        return "redirect:/boards";
    }

}
