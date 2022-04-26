package com.yyc.interceptor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 */

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    @CrossOrigin
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user= request.getSession().getAttribute("loginUser");
        if(user ==null){
            //未登录，返回登录页面
            //错误消息
            System.out.println("未登录，返回登录页面");
            request.setAttribute("loginMsg","没有权限，请先登录");
            //转发到/index.html这个请求，将请求和响应转发出去。
            response.sendRedirect("toLogin");
            return false;
        }else{
            //已经登录，放行请求
            return true;
        }
        //拦截器写完之后要到配置文件中进行配置。
    }

}
