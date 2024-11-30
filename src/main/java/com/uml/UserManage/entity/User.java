package com.uml.UserManage.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
/**
 * (Users)实体类
 *
 * @author makejava
 * @since 2024-11-30 22:26:34
 */
@Setter
@Getter
public class User{
    private Integer userId;

    private String username;

    private String password;

    private String email;

    private Date createdAt;

    private Date updatedAt;

    private Integer deleted;


}

