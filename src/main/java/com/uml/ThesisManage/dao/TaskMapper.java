package com.uml.ThesisManage.dao;

import com.uml.ThesisManage.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskMapper {

    // 获取任务书列表
    List<Task> getTaskList();

    // 通过ID查找获取任务书
    Task getTaskById(@Param("taskId") int id);

    // 提交任务书
    int submitTask(Task task);

    // 修改任务书
    int updateTask(Task task);

    // 删除任务书
    int deleteTask(@Param("taskId") int id);
}
