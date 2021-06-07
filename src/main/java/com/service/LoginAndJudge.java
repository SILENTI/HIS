package com.service;

import com.dao.UserDao;
import com.domain.User;
import com.util.MybatisUtil;
import com.util.ThreadLocalManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginAndJudge {

    //利用属性-延长生命周期
    private String judge;

    //判断登录结果
    public  String loginAndJudge (String userName,String passWord){
        //进行判断
        if (userName == null || passWord == null || userName.equals("") || passWord.equals("")){
            this.judge = "账号或密码错误";
        } else {

            //存入Map集合中
            HashMap<String,Object> values = new HashMap<>();
            values.put("userName",userName);
            values.put("passWord",passWord);

            //调用dao-进行数据的读取
               UserDao dao = MybatisUtil.getSqlSession(true).getMapper(UserDao.class);
               User user = dao.selectUserOne(values);

            //根据数据进行判断+有问题
            if (user!=null && user.getUsername().equals(userName) && user.getPassword().equals(passWord)){
                judge = "登录成功";

                //利用ThreadLocal传递User到Controller层
                ThreadLocal threadLocal = ThreadLocalManager.getTreadLocal(userName);
                threadLocal.set(user);

            } else {
                this.judge = "账号或密码错误";
            }
        }
        return judge;
    }
}
