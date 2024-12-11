package com.uml.ThesisManage.service;

import com.uml.ThesisManage.dao.TaskMapper;
import com.uml.ThesisManage.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskMapper taskMapper;

    // 获取任务书列表
    public List<Task> getTaskList() {
        return taskMapper.getTaskList();
    }

    // 通过ID查找获取任务书
    public Optional<Task> getTaskById(int id) {
        Task task = taskMapper.getTaskById(id);
        if (task == null || task.getDeleted() == 1) {
            return Optional.empty();
        }
        return Optional.of(task);
    }

    // 提交任务书
    public Optional<Task> submitTask(Task task) {
        taskMapper.submitTask(task);
        return Optional.of(task);
    }

    // 修改任务书
    public Optional<Task> updateTask(Task task) {
        Task existingTask = taskMapper.getTaskById(task.getTaskId());
        if (existingTask == null || existingTask.getDeleted() == 1) {
            return Optional.empty();
        }
        taskMapper.updateTask(task);
        return Optional.of(task);
    }

    // 删除任务书
    public boolean deleteTask(int id) {
        Task task = taskMapper.getTaskById(id);
        if (task == null || task.getDeleted() == 1) {
            return false;
        }
        taskMapper.deleteTask(id);
        return true;
    }
}
