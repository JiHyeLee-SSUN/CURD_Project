/***
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2019-10-23
 Time: 오후 5:07
 desc: 자동로그인 Interceptor

 Created by altjd815@gmail.com 
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 ***/
package com.board.interceptor;

import com.board.domain.UserVO;
import com.board.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RememberUserInterceptor extends HandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(RememberUserInterceptor.class);

    @Autowired
    private UserService userService ;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
        if (loginCookie != null) {
            UserVO uVO = userService.readForCheckSession(loginCookie.getValue());
            logger.info("uVo : " + uVO);

            if (uVO != null) {
                logger.info("Auto login....");
                httpSession.setAttribute("login", uVO);
            }
        }
        return true;
    }
}
