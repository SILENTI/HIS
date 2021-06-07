package com.dao;

import com.domain.Fun;
import com.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import java.util.HashMap;
import java.util.List;

public interface FunDao {

    /**
     * @paramdao层的分页查询
     * */
    @Select("select * from t_user limit #{start} , #{length} ")
    public List<User> findUserByPage(HashMap<String,Object> values);

    /**@param数据库中数据的总条数*/
    @Select(" select count(*) from t_user ")
    public int total ();

   /**@param查询所有的t_fun数据*/
   @Select("select * from t_fun ")
    public List<Fun> findAll();

    /**@param功能列表-新建菜单*/
    @Insert("insert into t_fun values (#{fname},#{ftype},#{fhref},#{pid},#{auth})")
    public void inserttMenu(HashMap values);

}
