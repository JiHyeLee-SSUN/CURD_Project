/***
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2019-10-10
 Time: 오후 6:23
 desc: 로그 인터셉터 파일

 Created by altjd815@gmail.com 
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 ***/
package com.board.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (log.isInfoEnabled()) {
            log.info("====================================== START ======================================");
            log.info(" Request URI \t: " + request.getRequestURI());
        }
        return super.preHandle(request, response, handler);

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (log.isInfoEnabled()) {
            log.info("====================================== END ======================================\n");
        }

    }
}
