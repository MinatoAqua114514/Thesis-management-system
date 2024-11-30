package com.uml.UserManage.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.io.Serializable;

/**
 * (Roles)实体类
 *
 * @author makejava
 * @since 2024-11-30 22:32:45
 */
@Setter
@Getter
public class Role implements Serializable {
    private Integer roleId;

    private String roleName;

    private String description;

    private Date createdAt;

    private Date updatedAt;


}

