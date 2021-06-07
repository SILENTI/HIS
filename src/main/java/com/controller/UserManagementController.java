package com.controller;

import com.domain.PageInfo;

import com.service.UserInquire;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

@Controller
public class UserManagementController extends HttpServlet {
    //用于管理用户信息

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        //获取查询的数据的页数和每页的个数
        int page = Integer.parseInt(request.getParameter("page"));
        int rows = Integer.parseInt(request.getParameter("rows"));

        //调取业务层-传递查询的页数和个数
        PageInfo users = new UserInquire().userInquiry(page,rows);

        //将处理结果-进行展示——转发
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("users",users);
        request.getRequestDispatcher("ShowUsers.jsp").forward(request,response);
    }
}
