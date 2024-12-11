package com.uml.ThesisManage.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Task {
    // 任务书ID
    private Integer taskId;
    // 选题申报ID
    private Integer topicId;
    // 学生ID
    private Integer studentId;
    // 指导老师ID
    private Integer advisorId;
    // 任务书文件路径ID
    private Integer taskFileId;
    // 任务书状态
    private String status;
    // 任务书提交时间
    private Date submittedAt;
    // 任务书更新时间
    private Date updatedAt;
    // 专业负责人反馈
    private String professionalLeaderFeedback;
    // 院领导反馈
    private String hospitalLeaderFeedback;
    // 删除标记
    private Integer deleted;
}
