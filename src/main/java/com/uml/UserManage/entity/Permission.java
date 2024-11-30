package com.uml.UserManage.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * (Permissions)实体类
 *
 * @author makejava
 * @since 2024-11-30 22:33:33
 */
@Getter
@Setter
public class Permission implements Serializable {
    private Integer permissionId;

    private String permissionName;

    private String description;

    private Date createdAt;

    private Date updatedAt;


}

