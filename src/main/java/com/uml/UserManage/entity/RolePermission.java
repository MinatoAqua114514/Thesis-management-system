package com.uml.UserManage.entity;

import lombok.Data;

@Data
public class RolePermission {
    // 角色id
    private Integer roleId;
    // 权限id
    private Integer permissionId;
}
