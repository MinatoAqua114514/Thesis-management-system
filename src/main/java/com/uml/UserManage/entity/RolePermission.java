package com.uml.UserManage.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RolePermission {
    // 角色id
    private Integer roleId;
    // 权限id
    private Integer permissionId;
}
