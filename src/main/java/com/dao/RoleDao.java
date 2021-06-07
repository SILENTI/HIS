package com.dao;

import com.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {

    /**@param查询所有角色表的信息*/
    @Select("select * from t_role")
    public  List<Role> findRoel();
}
