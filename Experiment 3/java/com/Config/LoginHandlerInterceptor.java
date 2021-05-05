package com.Config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ding
 * 配置拦截器
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginuser=request.getSession().getAttribute("user");
        if(loginuser==null){
            request.setAttribute("alert","loginfirst");
            request.getRequestDispatcher("index.html").forward(request,response);
            return false;
        }else {
            return true;
        }
    }

}
