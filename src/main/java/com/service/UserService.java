package com.service;

import com.dao.UserDao;
import com.domain.User;
import com.util.MybatisUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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
}
