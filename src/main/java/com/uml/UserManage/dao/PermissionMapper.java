package com.uml.UserManage.dao;

import com.uml.UserManage.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper {
    // 添加权限
    void addPermission(String permissionName, String description);
    // 删除权限
    void deletePermission(Integer permissionId);
    // 更新权限
    void updatePermission(Integer permissionId, String permissionName, String description);
    // 根据Id获取权限
    Permission getPermissionById(Integer permissionId);
    // 获取所有权限
    List<Permission> getAllPermissions();
}
