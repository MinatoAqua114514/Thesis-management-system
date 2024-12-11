package com.uml.ThesisManage.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Submission {
    // 选题申报ID
    private int topicId;
    // 选题申报学生ID
    private int studentId;
    // 选题申报指导教师ID
    private int advisorId;
    // 选题申报名称
    private String topicName;
    // 选题申报文件ID
    private int submissionFileId;
    // 选题申报状态
    private String status;
    // 选题申报时间
    private Date submittedAt;
    // 选题申报更新时间
    private Date updatedAt;
    // 专业负责人指导意见
    private String professionalLeaderFeedback;
    // 院领导指导意见
    private String hospitalLeaderFeedback;
    // 删除标记
    private int deleted;
}
