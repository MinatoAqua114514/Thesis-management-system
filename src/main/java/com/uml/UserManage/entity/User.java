package com.uml.UserManage.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "User", description = "用户实体类")
public class User {

    @Schema(name = "userId", description = "用户ID")
    private Integer userId;

    @Schema(name = "username", description = "用户名")
    private String username;

    @Schema(name = "password", description = "密码")
    private String password;

    @Schema(name = "email", description = "邮箱")
    private String email;

    @Schema(name = "createdAt", description = "创建时间")
    private Date createdAt;

    @Schema(name = "updatedAt", description = "更新时间")
    private Date updatedAt;

    @Schema(name = "deleted", description = "删除标记")
    private Integer deleted;
}

