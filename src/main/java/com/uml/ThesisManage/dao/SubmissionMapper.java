package com.uml.ThesisManage.dao;

import com.uml.ThesisManage.entity.Submission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SubmissionMapper {

    // 获取选题申报列表
    List<Submission> getSubmissionList();

    // 通过ID查找获取选题申报
    Submission getSubmissionById(@Param("topicId") int id);

    // 提交选题申报
    int submitSubmission(Submission submission);

    // 修改选题申报
    int updateSubmission(Submission submission);

    // 删除选题申报
    int deleteSubmission(@Param("topicId") int id);

}
