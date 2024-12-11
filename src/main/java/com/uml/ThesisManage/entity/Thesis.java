package com.uml.ThesisManage.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Thesis {
    // 论文ID
    private Integer thesisId;
    // 论文题目
    private String thesisTitle;
    // 学生ID
    private Integer studentId;
    // 指导老师ID
    private Integer advisorId;
    // 中期报告ID
    private Integer interimReportId;
    // 论文文件ID
    private Integer thesisFileId;
    // 论文状态
    private String status;
    // 论文提交时间
    private Date submittedAt;
    // 论文更新时间
    private Date updatedAt;
    // 删除标记
    private Integer deleted;
}
