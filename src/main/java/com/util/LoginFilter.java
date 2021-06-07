package com.util;


import com.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements HandlerInterceptor {

    /**
     * 用于验证用户登录，拦截器
     * 防止用户在未登录的情况下，访问重要的内容
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        //获取请求
        if (
                path.contains("Login")||
                        path.endsWith(".js")   ||
                        path.endsWith(".css")||
                        path.endsWith(".jpg")
        ){
            return true;
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user==null){
            response.sendRedirect("Login.html");
        }else {
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
