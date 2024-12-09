package com.uml.UserManage.controller;

import com.uml.UserManage.annotation.CheckPermission;
import com.uml.UserManage.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mentor")
public class MentorController {

    @Autowired
    private MentorService mentorService;

    // 获取指导老师及其学生
    @GetMapping("{advisorId}")
    @CheckPermission("admin")
    public void getMentorStudent(@PathVariable("advisorId") Integer advisorId) {
        mentorService.getMentorStudent(advisorId);
    }

    // 获取学生及其指导老师
    @GetMapping("{studentId}")
    @CheckPermission("admin")
    public void getStudentMentor(@PathVariable("studentId") Integer studentId) {
        mentorService.getStudentMentor(studentId);
    }

    // 为指导老师分配学生
    @PostMapping("{id}")
    @CheckPermission("admin")
    public void insertMentorStudent(@PathVariable("id") Integer mentorId, Integer studentId) {
        mentorService.insertMentorStudent(mentorId, studentId);
    }

    // 修改指导老师的指导学生
    @PutMapping("{id}")
    @CheckPermission("admin")
    public void updateMentorStudent(@PathVariable("id") Integer mentorId, Integer studentId) {
        mentorService.updateMentorStudent(mentorId, studentId);
    }

    // 删除指导老师的指导学生
    @DeleteMapping("{id}")
    @CheckPermission("admin")
    public void deleteMentorStudent(@PathVariable("id") Integer mentorId, Integer studentId) {
        mentorService.deleteMentorStudent(mentorId, studentId);
    }
}
