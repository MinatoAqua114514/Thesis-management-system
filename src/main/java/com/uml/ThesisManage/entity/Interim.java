package com.uml.ThesisManage.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "Interim", description = "中期报告实体类")
public class Interim {

    @Schema(name = "interimReportId", description = "中期报告ID")
    private Integer interimReportId;

    @Schema(name = "proposalId", description = "开题报告ID")
    private Integer proposalId;

    @Schema(name = "studentId", description = "学生ID")
    private Integer studentId;

    @Schema(name = "advisorId", description = "指导老师ID")
    private Integer advisorId;

    @Schema(name = "interimReportFileId", description = "中期报告文件ID")
    private Integer interimReportFileId;

    @Schema(name = "status", description = "中期报告状态")
    private String status;

    @Schema(name = "submittedAt", description = "中期报告提交时间")
    private Date submittedAt;

    @Schema(name = "updatedAt", description = "中期报告更新时间")
    private Date updatedAt;

    @Schema(name = "advisorEvaluation", description = "指导老师评价")
    private String advisorEvaluation;

    @Schema(name = "deleted", description = "删除标记")
    private Integer deleted;
}
