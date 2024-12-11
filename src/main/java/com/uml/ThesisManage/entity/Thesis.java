package com.uml.ThesisManage.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "Thesis", description = "论文实体类")
public class Thesis {

    @Schema(name = "thesisId", description = "论文ID")
    private Integer thesisId;

    @Schema(name = "thesisTitle", description = "论文题目")
    private String thesisTitle;

    @Schema(name = "studentId", description = "学生ID")
    private Integer studentId;

    @Schema(name = "advisorId", description = "指导老师ID")
    private Integer advisorId;

    @Schema(name = "interimReportId", description = "中期报告ID")
    private Integer interimReportId;

    @Schema(name = "thesisFileId", description = "论文文件ID")
    private Integer thesisFileId;

    @Schema(name = "status", description = "论文状态")
    private String status;

    @Schema(name = "submittedAt", description = "论文提交时间")
    private Date submittedAt;

    @Schema(name = "updatedAt", description = "论文更新时间")
    private Date updatedAt;

    @Schema(name = "deleted", description = "删除标记")
    private Integer deleted;
}
