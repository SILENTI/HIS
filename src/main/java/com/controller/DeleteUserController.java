package com.controller;

import com.service.DeleteUser;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class DeleteUserController extends HttpServlet {

    /**
     * 用于修改用户的信息
     * */

    //如果含有多个相同的项目，则需要创建多个controller类？？？

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

        //获取用户信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
       

        //将用户信息传递给业务层

        DeleteUser deleteUser = new DeleteUser();
        deleteUser.delete(username,password);

        //重新刷新——重新调用查询的方法
        request.getRequestDispatcher("userManagementController?page=1&rows=10").forward(request,response);

    }
}
