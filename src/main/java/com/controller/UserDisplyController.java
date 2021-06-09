package com.controller;

import com.alibaba.fastjson.JSON;
import com.domain.Role;
import com.domain.User;
import com.service.UserService;
import com.util.LayuiUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserDisplyController {

    @RequestMapping(value = "/userDisply",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> userDisply(int page , int limit) {
        List<User> users = new UserService().selectUserList(page,limit);
        Map<String,Object> map = new HashMap<String,Object>();
        System.out.println(users);
        map.put("code",0);
        map.put("count",1000);
        map.put("msg","");
        map.put("data",users) ;
        return map ;

    }

    //删除用户信息
    @RequestMapping("/delUserInfo")
    @ResponseBody
    public Map delUserInfo (String data){
        return LayuiUtil.ReturnMapInfo("data",new UserService().delectUserInfo(data));
    }

    //编辑用户信息
    @RequestMapping("/EditUserInfo")
    @ResponseBody
    public String EditUserInfo (Integer uid,String username , String password){
        System.out.println(uid+"  "+username+"  "+password);
      return new UserService().EditUserInfo(uid, username, password);
    }
}
