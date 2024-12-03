package com.uml.UserManage.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class Permission {
    // 权限id
    @Id
    private Integer permissionId;
    // 权限名
    private String permissionName;
    // 权限描述
    private String description;
    // 创建时间
    private Timestamp createAt;
    // 更新时间
    private Timestamp updateAt;
}
