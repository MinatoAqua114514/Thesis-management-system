package com.uml.UserManage.entity;

import lombok.Data;

@Data
public class DefensePanelMember {
    // 评阅小组成员Id
    private Integer panelMemberId;
    // 评阅老师Id
    private Integer teacherId;
    // 评阅小组Id
    private Integer defensePanelId;
    // 评阅小组成员角色
    private String role;
    // 创建时间
    private String createdAt;
    // 更新时间
    private String updatedAt;
}
