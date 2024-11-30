package com.uml.UserManage.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper {

    // 通过用户id获取角色名
    String getRoleName(String userId);
}
