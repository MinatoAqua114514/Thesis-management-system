package com.uml.UserManage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class RolePermission {
    // 角色id
    @Id
    private Integer roleId;
    // 权限id
    private Integer permissionId;
}
