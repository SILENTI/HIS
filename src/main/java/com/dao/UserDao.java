package com.dao;

import com.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface UserDao {

    /**@param查询单条数据库中的用户数据*/
    @Select("select * from t_user where username = #{userName} and password = #{passWord}")
    public User selectUserOne (HashMap<String,Object> values) ;

    /**@param删除用户信息*/
    @Delete("  delete from t_user where username = #{username} and password = #{passWord}")
    public void deleteUser(HashMap<String,Object> map);

    /**@param用于新建用户信息*/
    @Insert(" insert into t_user (username,password) values (#{username},#{password})")
    public void insertUser (HashMap<String,Object> values);
    //构成方法重载
    /**@param用于新建用户信息*/
    @Insert(" insert into t_user (username,password) values (#{username},#{password})")
    public void inportUserInfo (User user);

    @Select("select * from t_user")
    public List<User> selectUsers();

    /**分页查询用户的信息*/
    @Select("select * from t_user limit #{page},#{limit}")
    public List<User> selectUserList(@Param("page") int page ,@Param("limit") int limit);
}




