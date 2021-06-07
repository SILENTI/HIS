package com.controller;

import com.domain.User;
import com.service.LoginAndJudge;
import com.util.ThreadLocalManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController extends HttpServlet {

    /*@Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置字符集
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //获取用户名和密码
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
//        String autoLogin = request.getParameter("AutoLogin");

        System.out.println("账号："+userName +"  "+"密码："+passWord);
        //调用业务层
        String judge = new LoginAndJudge().loginAndJudge(userName,passWord);
        //响应结果——>根据判断结果-进行有选择的响应
        if(judge.equals("登录成功")){

            //通过TheadLocal获取对象User
            User user = (User) ThreadLocalManager.getTreadLocal(userName).get();
            //自动登录
            //登录成功——存储自动登录的信息
            if (autoLogin!=null && !autoLogin.equals("")) {
                //含有自动登录的标识

                //创建tokenID - key 从Browser获取Token-保证唯一性
                String tokenID = UUID.randomUUID().toString();

                //创建一个新的cookie,将TokenID存入Cookie中
                Cookie cookie = new Cookie("TokenID",tokenID);
                //获取Browser的ID地址
                String ID = request.getRemoteAddr();
                //获取创建的时间
                long start = System.currentTimeMillis();
                //获取失效的时间
                long end = System.currentTimeMillis()+1000L*60*60*24*7;

                //创建Token对象
                Token token = new Token(tokenID,user,ID,start,end);

                //将创建好的Token存入服务器——>Redis缓存
                request.getServletContext().setAttribute(tokenID,token);
            }

            System.out.println(judge);
            //登录成功后转发-展示界面
            request.getSession().setAttribute("user",user);
            //将user存入Session，在Filter实行登录认证
            //request.getRequestDispatcher("Main.html").forward(request,response);
            response.sendRedirect("Main.html");
        }else if (judge.equals("账号或密码错误")){
            //重新返回登录
            System.out.println(judge);
            response.sendRedirect("Login.html");
         }
    }*/

    //返回的URL
    private String URL = "redirect:Login.html";
    @RequestMapping("/HospitalSystem")
    public String LoginController (String userName , String passWord , HttpServletRequest request){
        HttpSession session = request.getSession(true);
        System.out.println("账号："+userName +"  "+"密码："+passWord);
        //调用业务层
        String judge = new LoginAndJudge().loginAndJudge(userName,passWord);
        //响应结果——>根据判断结果-进行有选择的响应
        if(judge.equals("登录成功")){
            //通过TheadLocal获取对象User
            User user = (User) ThreadLocalManager.getTreadLocal(userName).get();
            System.out.println(judge);
            //登录成功后转发-展示界面
            session.setAttribute("user",user);
            //重定向-到中央页面
            URL = "redirect:Main.html";
        }else if (judge.equals("账号或密码错误")){
            //重新返回登录
            System.out.println(judge);
            URL = "redirect:Login.html";
        }
        return URL;
    }

}
