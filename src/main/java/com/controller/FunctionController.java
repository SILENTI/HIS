package com.controller;

import com.domain.Fun;
import com.domain.Role;
import com.service.Function;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通过AJAX获取少量数据库数据，实现少量的数据交换
 * */

//使用自己封装的MVC框架

@Controller
public class FunctionController {

    //功能管理展示-tree-table
    @RequestMapping("/FunctionAll")
    @ResponseBody //直接返回，转换为Jose
    public Map<String, Object> showFunction(){
        Function fun = new Function();
        List<Fun> funList = fun.findAll();

        System.out.println(funList);
        System.out.println("FunctionController");

        //将查询结果按照layui-treetable组件的规范组装，并响应
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",0);
        map.put("msg","出错啦");
        map.put("data",funList) ;
        return map;
    }

    //功能管理菜单新建功能
    @RequestMapping("/funAdd")
    public void toAddFunMenu (@RequestParam("fname") String fname
            ,@RequestParam("ftype") String ftype
            ,@RequestParam("fhref") String fhref
            ,@RequestParam("auth") String auth
            ,@RequestParam("pid") int pid //根菜单-1
    ){
        //将传递的数据，写入数据库
        //1.将其直接传值到底层
            //fid主键自增
//        System.out.println(fname+" "+ftype+" "+fhref+" "+ auth +" "+ pid );
            Function function = new Function();
        //2.将其存入map集合中
    }

    //删除数据
    @RequestMapping("/Fun")
    public void funDelect (@RequestParam("fid") int fid , @RequestParam("fname") String fname){
        System.out.println(fid + "   " + fname);

    }

    //角色表的用户信息展示
    @RequestMapping("/SelectRole")
    @ResponseBody
    public Map<String, Object> roleController (){
        Function function = new Function();
        List<Role> roles= function.findRole();
        Map<String,Object> map = new HashMap<String,Object>();
        System.out.println("请求角色表数据");
        map.put("code",0);
        map.put("count",1000);
        map.put("msg","");
        map.put("data",roles) ;
        return map;


    }

}
