package com.uml.ThesisManage.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "Proposal", description = "开题报告实体类")
public class Proposal {

    @Schema(name = "proposalId", description = "开题报告ID")
    private Integer proposalId;

    @Schema(name = "taskId", description = "任务书ID")
    private Integer taskId;

    @Schema(name = "studentId", description = "学生ID")
    private Integer studentId;

    @Schema(name = "advisorId", description = "指导老师ID")
    private Integer advisorId;

    @Schema(name = "proposalFileId", description = "开题报告文件ID")
    private Integer proposalFileId;

    @Schema(name = "status", description = "开题报告状态")
    private String status;

    @Schema(name = "submittedAt", description = "开题报告提交时间")
    private Date submittedAt;

    @Schema(name = "updatedAt", description = "开题报告更新时间")
    private Date updatedAt;

    @Schema(name = "advisorFeedback", description = "指导老师反馈")
    private String advisorFeedback;

    @Schema(name = "professionalLeaderFeedback", description = "专业负责人反馈")
    private String professionalLeaderFeedback;

    @Schema(name = "deleted", description = "删除标记")
    private int deleted;
}
