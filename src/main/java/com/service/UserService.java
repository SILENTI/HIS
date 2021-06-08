package com.service;

import com.alibaba.fastjson.JSON;
import com.dao.UserDao;
import com.domain.User;
import com.util.MybatisUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    public void servier (User user){

        //调用dao层
        String username = user.getUsername();
        String password = user.getPassword();

        HashMap map = new HashMap();
        map.put("username",username);
        map.put("password",password);

        UserDao userDao = MybatisUtil.getMapper(UserDao.class,true);
        userDao.insertUser(map);

    }

    //查询用户信息
    public List<User> selectUserList (int page , int limit){
        UserDao userDao = MybatisUtil.getMapper(UserDao.class,true);
        return userDao.selectUserList((page-1)*10,limit);
    }

    //删除用户信息
    public String delectUserInfo (String data){
        Map map = (Map) JSON.parse(data);
        HashMap values = new HashMap();
        values.put("username",map.get("username"));values.put("password",map.get("password"));
        UserDao userDao = MybatisUtil.getMapper(UserDao.class,true);
        userDao.deleteUser(values);
        //优化，如果执行成功就返回true
        return "成功";
    }
}
