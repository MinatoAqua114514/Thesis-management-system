package com.uml.UserManage.entity;

import javax.persistence.Table;
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
@Table(name = "users")
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

