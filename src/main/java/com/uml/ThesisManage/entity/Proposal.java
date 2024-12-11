package com.uml.ThesisManage.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Proposal {
    // 开题报告ID
    private Integer proposalId;
    // 任务书ID
    private Integer taskId;
    // 学生ID
    private Integer studentId;
    // 指导老师ID
    private Integer advisorId;
    // 开题报告文件ID
    private Integer proposalFileId;
    // 开题报告状态
    private String status;
    // 开题报告提交时间
    private Date submittedAt;
    // 开题报告更新时间
    private Date updatedAt;
    // 指导老师反馈
    private String advisorFeedback;
    // 专业负责人反馈
    private String professionalLeaderFeedback;
    // 删除标记
    private int deleted;
}
