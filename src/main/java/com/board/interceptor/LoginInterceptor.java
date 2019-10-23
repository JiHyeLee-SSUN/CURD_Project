/***
 Created by IntelliJ IDEA.
 Project : CURD_Board
 User: leejihye
 Date: 2019-10-07
 Time: 오후 1:26
 desc: 로그인 interceptor

 Created by altjd815@gmail.com 
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 ***/
package com.board.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final String LOGIN = "login";
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute(LOGIN) != null){
            logger.info("clear login data before....");
            httpSession.removeAttribute(LOGIN);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession httpSession = request.getSession();
        ModelMap modelMap = modelAndView.getModelMap();
        Object uVO = modelMap.get("uVO");

        if (uVO != null) {
            logger.info("login success...");
            httpSession.setAttribute("login", uVO);

            if (request.getParameter("useCookie") != null) {
                logger.info("use cookie.....");
                Cookie loginCookie = new Cookie("loginCookie", httpSession.getId());
                loginCookie.setPath("/");
                loginCookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(loginCookie);
            }

            String destination = (String) httpSession.getAttribute("destination");
            response.sendRedirect(destination != null ? destination : "/");
        }
    }
}
