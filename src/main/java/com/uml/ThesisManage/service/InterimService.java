package com.uml.ThesisManage.service;

import com.uml.ThesisManage.dao.InterimMapper;
import com.uml.ThesisManage.entity.Interim;
import com.uml.UserManage.dao.MentorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterimService {

    @Autowired
    private InterimMapper interimMapper;
    @Autowired
    private MentorMapper mentorMapper;


    // 获取中期报告列表
    public List<Interim> getInterimList() {
        return interimMapper.getInterimList();
    }

    // 通过ID获取中期报告
    public Optional<Interim> getInterimById(int id) {
        Interim interim = interimMapper.getInterimById(id);
        if (interim == null || interim.getDeleted() == 1) {
            return Optional.empty();
        }
        return Optional.of(interim);
    }

    // 提交中期报告
    public Optional<Interim> submitInterim(Interim interim) {
        // 设置提交后中期报告状态为未审核
        interim.setStatus("pending");
        // 通过学生ID获取指导老师ID
        int studentId = interim.getStudentId();
        int advisorId = mentorMapper.getStudentMentor(studentId).getAdvisorId();
        interim.setAdvisorId(advisorId);
        interimMapper.submitInterim(interim);
        return Optional.of(interim);
    }

    // 重传中期报告
    public Optional<Interim> updateInterim(Integer oldFileId, Interim interim) {
        Interim existingInterim = interimMapper.getInterimById(oldFileId);
        if (existingInterim == null || existingInterim.getDeleted() == 1) {
            return Optional.empty();
        }
        // 如果中期报告已经审核通过，则不允许重传
        if (existingInterim.getStatus().equals("approved")) {
            return Optional.empty();
        }
        // 如果中期报告已经存在文件，则删除原文件，上传新文件,并修改文件ID
        // 获取更新后的中期报告
        return Optional.of(interim);
    }

    public boolean deleteInterim(int id) {
        Interim interim = interimMapper.getInterimById(id);
        if (interim == null || interim.getDeleted() == 1) {
            return false;
        }
        interimMapper.deleteInterim(id);
        return true;
    }
}