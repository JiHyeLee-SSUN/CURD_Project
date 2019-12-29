/***
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2019-11-10
 Time: 오후 6:24
 desc: 로그인 필요영역 로그인인터셉터

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

public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private UserService userService;

    private void saveDestination(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String query = request.getQueryString();

        if (query == null || query.equals("null")) {
            query = "";
        } else {
            query = query + "?";
        }

        if (request.getMethod().equals("GET")) {
            logger.info("destination: " + (uri + query));
            request.getSession().setAttribute("destination", (uri + query));
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        if (session.getAttribute("login") == null) {
            logger.info("current user is not logined");
            saveDestination(request);

            Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");

            if (loginCookie != null) {
                UserVO uVO = userService.readForCheckSession(loginCookie.getValue());
                logger.info("uVO: " + uVO);

                if (uVO != null) {
                    session.setAttribute("login", uVO);
                    return true;
                }
            }

            response.sendRedirect("/user/login");
            return false;
        }

        return true;
    }
}
