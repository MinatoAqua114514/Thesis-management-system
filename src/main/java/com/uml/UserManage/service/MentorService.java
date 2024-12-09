package com.uml.UserManage.service;

import com.uml.UserManage.dao.MentorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MentorService {

    @Autowired
    private MentorMapper mentorMapper;

    // 获取指导老师及其学生
    public void getMentorStudent(Integer advisorId) {
        mentorMapper.getMentorStudent(advisorId);
    }

    // 获取学生及其指导老师
    public void getStudentMentor(Integer studentId) {
        mentorMapper.getStudentMentor(studentId);
    }

    // 为指导老师分配学生
    public void insertMentorStudent(Integer mentorId, Integer studentId) {
        mentorMapper.insertMentorStudent(mentorId, studentId);
    }

    // 修改指导老师的指导学生
    public void updateMentorStudent(Integer mentorId, Integer studentId) {
        mentorMapper.updateMentorStudent(mentorId, studentId);
    }

    // 删除指导老师的指导学生
    public void deleteMentorStudent(Integer mentorId, Integer studentId) {
        mentorMapper.deleteMentorStudent(mentorId, studentId);
    }
}
