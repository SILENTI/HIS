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

    private UserDao userDao = MybatisUtil.getMapper(UserDao.class,true);

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
        System.out.println(values);
        UserDao userDao = MybatisUtil.getMapper(UserDao.class,true);
        userDao.deleteUser(values);
        //优化，如果执行成功就返回true
        return "成功";
    }

    //修改用户信息
    public String EditUserInfo (Integer uid , String username,String password ) {
        if (uid !=null && username != null && username !="" && password != null && password !="" ){
            userDao.updateUserInfo(new User(uid,username,password,null,null));
            return "编辑成功";
        }
        return "编辑失败";
    }
}
