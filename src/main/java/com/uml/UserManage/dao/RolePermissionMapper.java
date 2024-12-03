package com.uml.UserManage.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RolePermissionMapper {
    // 分配角色权限
    void insertRolePermission(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);
}
