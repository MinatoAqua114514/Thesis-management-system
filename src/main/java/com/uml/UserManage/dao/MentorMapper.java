package com.uml.UserManage.dao;

import com.uml.UserManage.entity.MentorStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MentorMapper {
    // 获取指导老师及其学生
    void getMentorStudent(@Param("advisorId") Integer advisorId);

    // 获取学生及其指导老师
    MentorStudent getStudentMentor(@Param("studentId") Integer studentId);

    // 为指导老师分配学生
    void insertMentorStudent(Integer mentorId, Integer studentId);

    // 修改指导老师的指导学生
    void updateMentorStudent(Integer mentorId, Integer studentId);

    // 删除指导老师的指导学生
    void deleteMentorStudent(Integer mentorId, Integer studentId);
}
