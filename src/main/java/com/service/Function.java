package com.service;

import com.dao.FunDao;
import com.dao.RoleDao;
import com.domain.Fun;
import com.domain.Role;
import com.util.MybatisUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class Function {

    FunDao dao = MybatisUtil.getMapper(FunDao.class,true);

    //找寻到所有的功能表的信息
    public List<Fun> findAll(){
        return dao.findAll();
    }

    //新建菜单功能
    public void insertMenu (String fname,String ftype,String fhref,int pid,String auth){
        //将传递的数据组成一个map集合
        HashMap map = new HashMap();
        map.put("fname",fname);map.put("ftype",ftype);map.put("fhref",fhref);map.put("auth",auth);map.put("pid",pid);
        //写入数据库
         dao.inserttMenu(map);
    }

    //找寻所有的角色表信息
    public List<Role> findRole(){
        RoleDao roleDao = MybatisUtil.getMapper(RoleDao.class,true);
        return roleDao.findRoel();
    }

}
