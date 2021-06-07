package com.service;

import com.dao.FunDao;
import com.domain.PageInfo;
import com.dao.UserDao;
import com.domain.User;
import com.util.MybatisUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserInquire {
    //负责查询用户的信息

    public PageInfo userInquiry (int page , int rows) {//page：起始页数 ， rows：每页所展示的信息数

    //逻辑判断，让业务变的更加合理，减少漏洞。

        UserDao userDao = MybatisUtil.getMapper(UserDao.class,true);
        FunDao funDao = MybatisUtil.getMapper(FunDao.class,true);
        //作判断——判断上限及下限
        if (page<1){
            page = 1 ;
        }

        //获取数据库存储信息的存储数据量
        int total = funDao.total();
        //判断——三目运算符
        int max = total%rows==0 ? total/rows : total / rows + 1 ;
                //如果，用户的信息量与每页展示的信息数的余数 == 0 ，则表明可以取整 否者则加一后 取整 ？？？
        max = max == 0 ? 1 : max ;

        //如果-page大于max，表明让查询的页数
        if (page > max ) {
            page = max ;
        }

        //数据库查询的有效页数-《数据库查询从零开始，lengh是查询数据库的总数》
        int start = (page - 1 ) * rows ;
        int length = rows ;
        //调用dao层查询信息

        //存入集合中
        HashMap<String,Object> param = new HashMap<>();
        param.put("start",start) ;
        param.put("length",length) ;

        //使用配置文件的方式使用Mybatis
//        List <User> users = funDao.findUserByPage(param);

        //使用配置文件+接口方式使用Mybatis
        List <User> users = MybatisUtil.getSqlSession(true).getMapper(FunDao.class).findUserByPage(param);

        //返回一个储存的所有信息的domain实体对象
        return new PageInfo(page , rows,total,max,start,(start+rows-1) , users) ;
    }

}
