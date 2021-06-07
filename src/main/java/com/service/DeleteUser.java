package com.service;

import com.dao.UserDao;
import com.util.MybatisUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class DeleteUser {

    //删除用户信息

    public void delete(String username , String password){
        UserDao userDao = MybatisUtil.getMapper(UserDao.class,true);
        HashMap values = new HashMap();
        values.put("username",username);
        values.put("password",password);
        userDao.deleteUser(values);
    }

}
