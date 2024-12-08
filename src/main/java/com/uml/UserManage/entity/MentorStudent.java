package com.uml.UserManage.entity;

import lombok.Data;

@Data
public class MentorStudent {
    // 指导老师学生关系Id
    private Integer advisorStudentId;
    // 指导老师Id
    private Integer advisorId;
    // 学生Id
    private Integer studentId;
    // 创建时间
    private String createdAt;
    // 更新时间
    private String updatedAt;
}
