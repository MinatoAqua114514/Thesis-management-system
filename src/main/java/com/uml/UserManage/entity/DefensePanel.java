package com.uml.UserManage.entity;

import lombok.Data;

@Data
public class DefensePanel {
    // 评阅小组Id
    private Integer defensePanelId;
    // 评阅小组组长
    private Integer panelLeaderId;
    // 评阅小组名称
    private String panelName;
    // 评阅小组创建时间
    private String createdAt;
    // 评阅小组更新时间
    private String updatedAt;
}
