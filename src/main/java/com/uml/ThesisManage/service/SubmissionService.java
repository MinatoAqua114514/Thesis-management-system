package com.uml.ThesisManage.service;

import com.uml.ThesisManage.dao.SubmissionMapper;
import com.uml.ThesisManage.entity.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionMapper submissionMapper;

    // 获取开题报告列表
    public List<Submission> getSubmissionList() {
        return submissionMapper.getSubmissionList();
    }

    // 通过ID查找获取开题报告
    public Optional<Submission> getSubmissionById(int id) {
        Submission submission = submissionMapper.getSubmissionById(id);
        if (submission == null || submission.getDeleted() == 1) {
            return Optional.empty();
        }
        return Optional.of(submission);
    }

    // 提交开题报告
    public Optional<Submission> submitSubmission(Submission submission) {
        submissionMapper.submitSubmission(submission);
        return Optional.of(submission);
    }

    // 修改开题报告
    public Optional<Submission> updateSubmission(Submission submission) {
        Submission existingSubmission = submissionMapper.getSubmissionById(submission.getTopicId());
        if (existingSubmission == null || existingSubmission.getDeleted() == 1) {
            return Optional.empty();
        }
        submissionMapper.updateSubmission(submission);
        return Optional.of(submission);
    }

    // 删除开题报告
    public boolean deleteSubmission(int id) {
        Submission submission = submissionMapper.getSubmissionById(id);
        if (submission == null || submission.getDeleted() == 1) {
            return false;
        }
        submissionMapper.deleteSubmission(id);
        return true;
    }
}
