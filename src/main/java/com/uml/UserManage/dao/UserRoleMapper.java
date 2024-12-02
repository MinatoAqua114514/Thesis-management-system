package com.uml.UserManage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRoleMapper {

    // 通过用户id获取角色名
    String getRoleName(@Param("userId") Integer userId);
}
