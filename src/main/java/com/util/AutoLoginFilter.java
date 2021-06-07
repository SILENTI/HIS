package com.util;

import com.domain.Token;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutoLoginFilter extends HttpFilter implements Filter {
    /**
     * 用于自动登录的过滤器作用
     * 主要是拦截，从登录页到主页的过程
     * */
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //判断，如果访问的是静态资源的话——直接放行
        String path = request.getServletPath();
        //获取请求名
        if (
                path.endsWith(".js")||
                path.endsWith(".jpg")||
                path.contains(".css")
        ){
            //静态资源-不需要自动登录处理，放过请求
                chain.doFilter(request,response);
                return;
        }
        //获取浏览器，发送请求所携带所有的Cookie
        Cookie [] cookies = request.getCookies();
        if (cookies!=null && cookies.length>0){//和证明含有cookie
            //循环获取TokenID
            for (Cookie cookie : cookies){
                //cookidName = "tokenID"
                String cookieName = cookie.getName();
                //tokenID=UUID
                String tokenID = cookie.getValue();
                //获取服务器存储的Token
                Token token = (Token) request.getServletContext().getAttribute(tokenID);
                if (cookieName == null || token == null){
                    //伪造的cookie
                    chain.doFilter(request , response);
                    return;//终止
                }else if (!token.getIp().equals( request.getRemoteAddr())){
                    //存储的ip地址与当前电脑端的ip不同
                    chain.doFilter(request,response);
                    return;
                }else if (token.getEnd() < System.currentTimeMillis()){
                    //token已经过期
                    chain.doFilter(request,response);
                    return;
                }else {
                    //token能正常-可以正常登录
                    //如果访问的是登录界面-直接进入主界面
                    if (path.contains("Login")){
//                        request.getSession().setAttribute("user",token.getUser());
//                        request.getRequestDispatcher("Main.jsp").forward(request,response);

                        response.sendRedirect("Main.html");
                        chain.doFilter(request,response);
                        return;
                    }
                    chain.doFilter(request,response);
                    return;
                }
            }
        }
        //进行放行
        System.out.println("最后的放行！！！");
        chain.doFilter(request,response);
    }
}
