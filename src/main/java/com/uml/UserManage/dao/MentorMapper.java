package com.uml.UserManage.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MentorMapper {
    // 获取指导老师及其学生
    void getMentorStudent();

    // 为指导老师分配学生
    void insertMentorStudent(Integer mentorId, Integer studentId);

    // 修改指导老师的指导学生
    void updateMentorStudent(Integer mentorId, Integer studentId);

    // 删除指导老师的指导学生
    void deleteMentorStudent(Integer mentorId, Integer studentId);
}
