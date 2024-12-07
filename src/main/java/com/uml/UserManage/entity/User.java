package com.uml.UserManage.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    // 用户ID
    private Integer userId;
    // 用户名称
    private String username;
    // 用户密码
    private String password;
    // 用户邮箱
    private String email;
    // 用户创建时间
    private Date createdAt;
    // 用户更新时间
    private Date updatedAt;
    // 用户删除标记
    private Integer deleted;
}

