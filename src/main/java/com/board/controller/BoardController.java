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
import com.board.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("boards")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private BoardService boardService;

    // 전체 글 목록 화면
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String readAll(Model model) throws Exception {
        model.addAttribute("list", boardService.readAll());
        return "boards/list";
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
