package com.controller;

import com.domain.Role;
import com.domain.User;
import com.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
        map.put("code",0);
        map.put("count",1000);
        map.put("msg","");
        map.put("data",users) ;
        return map ;

    }
}
