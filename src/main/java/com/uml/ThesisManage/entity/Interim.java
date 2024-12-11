package com.uml.ThesisManage.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Interim {
    // 中期报告ID
    private Integer interimReportId;
    // 开题报告ID
    private Integer proposalId;
    // 学生ID
    private Integer studentId;
    // 指导老师ID
    private Integer advisorId;
    // 中期报告文件ID
    private Integer interimReportFileId;
    // 中期报告状态
    private String status;
    // 中期报告提交时间
    private Date submittedAt;
    // 中期报告更新时间
    private Date updatedAt;
    // 指导老师评价
    private String advisorEvaluation;
    // 删除标记
    private Integer deleted;
}
