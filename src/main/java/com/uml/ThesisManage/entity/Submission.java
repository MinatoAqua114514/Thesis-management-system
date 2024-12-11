package com.uml.ThesisManage.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "Submission", description = "选题申报实体类")
public class Submission {

    @Schema(name = "topicId", description = "选题申报ID")
    private int topicId;

    @Schema(name = "studentId", description = "选题申报学生ID")
    private int studentId;

    @Schema(name = "advisorId", description = "选题申报指导教师ID")
    private int advisorId;

    @Schema(name = "topicName", description = "选题申报名称")
    private String topicName;

    @Schema(name = "submissionFileId", description = "选题申报文件ID")
    private int submissionFileId;

    @Schema(name = "status", description = "选题申报状态")
    private String status;

    @Schema(name = "submittedAt", description = "选题申报时间")
    private Date submittedAt;

    @Schema(name = "updatedAt", description = "选题申报更新时间")
    private Date updatedAt;

    @Schema(name = "professionalLeaderFeedback", description = "专业负责人指导意见")
    private String professionalLeaderFeedback;

    @Schema(name = "hospitalLeaderFeedback", description = "院领导指导意见")
    private String hospitalLeaderFeedback;

    @Schema(name = "deleted", description = "删除标记")
    private int deleted;
}
