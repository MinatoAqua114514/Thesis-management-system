package com.uml.ThesisManage.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "Task", description = "任务书实体类")
public class Task {

    @Schema(name = "taskId", description = "任务书ID")
    private Integer taskId;

    @Schema(name = "topicId", description = "选题申报ID")
    private Integer topicId;

    @Schema(name = "studentId", description = "学生ID")
    private Integer studentId;

    @Schema(name = "advisorId", description = "指导老师ID")
    private Integer advisorId;

    @Schema(name = "taskFileId", description = "任务书文件ID")
    private Integer taskFileId;

    @Schema(name = "status", description = "任务书状态")
    private String status;

    @Schema(name = "submittedAt", description = "任务书提交时间")
    private Date submittedAt;

    @Schema(name = "updatedAt", description = "任务书更新时间")
    private Date updatedAt;

    @Schema(name = "professionalLeaderFeedback", description = "专业负责人反馈")
    private String professionalLeaderFeedback;

    @Schema(name = "hospitalLeaderFeedback", description = "院领导反馈")
    private String hospitalLeaderFeedback;

    @Schema(name = "deleted", description = "删除标记")
    private Integer deleted;
}
