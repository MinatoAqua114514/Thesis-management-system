package com.uml.ThesisManage.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SubmissionMapper {
    // 选题申报ID
    private Integer topicId;
    // 学生ID
    private Integer studentId;
    // 指导老师ID
    private Integer advisorId;
    // 选题名称
    private String topicName;
    // 选题申报文件ID
    private Integer submissionFileId;
    // 选题状态
    private String status;
    // 选题提交时间
    private Date submittedAt;
    // 选题更新时间
    private Date updatedAt;
    // 专业负责人反馈
    private String professionalLeaderFeedback;
    // 院领导反馈
    private String hospitalLeaderFeedback;
    // 删除标记
    private Integer deleted;
}
