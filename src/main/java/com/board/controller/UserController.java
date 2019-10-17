/***
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2019-10-07
 Time: 오후 5:17
 desc: 유저 정보관련 Controller

 Created by altjd815@gmail.com 
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 ***/
package com.board.controller;

import com.board.domain.UserVO;
import com.board.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Console;

@Controller
@RequestMapping("user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    //회원가입 페이지 이동
    @RequestMapping(value = "join", method = RequestMethod.GET)
    public String joinGET() {
        return "user/join";
    }

    @RequestMapping(value="joinPost", method=RequestMethod.POST)
    public String joinPost(@ModelAttribute("uVO") UserVO uVO) throws Exception {
        logger.info("statement join member: " + uVO.toString());
        userService.createByUser(uVO);
        return "user/joinPost";
    }


    // 회원가입 관련 ajax 처리(중복 uid, email 체크)
    // ajax uid 중복체크
    @ResponseBody
    @RequestMapping(value = "joinUidCheck", method = RequestMethod.GET)
    public ResponseEntity<String> joinUidCheck(String uid) {
        ResponseEntity<String> entity = null;
        try {
            if (userService.readByUid(uid) != null) {
                entity = new ResponseEntity<>("UID_DUP", HttpStatus.OK);
                logger.info(String.valueOf(entity));
            } else {
                entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
                logger.info(String.valueOf(entity));
            }
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
    //ajax email 중복체크
    @ResponseBody
    @RequestMapping(value="joinEmailCheck",method = RequestMethod.GET)
    public ResponseEntity<String> joinEmailCheck(String email){
        ResponseEntity<String> entity = null;
        try {
            if (userService.readyByEmail(email) != null){
                entity = new ResponseEntity<>("EMAIL_DUP", HttpStatus.OK);
            } else {
                entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
            }
        } catch(Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    // email 인증
    @RequestMapping(value="joinConfirm", method=RequestMethod.GET)
    public String emailConfirm(@ModelAttribute("uVO") UserVO uVO, Model model) throws Exception {
        logger.info(uVO.getEmail() + ": auth confirmed");
        uVO.setAuthstatus(1);
        userService.updateAuthStatus(uVO);
        model.addAttribute("auth_check", 1);
        return "user/joinPost";
    }

}
