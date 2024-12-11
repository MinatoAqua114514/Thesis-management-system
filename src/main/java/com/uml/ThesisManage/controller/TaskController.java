package com.uml.ThesisManage.controller;

import com.uml.ThesisManage.entity.Task;
import com.uml.ThesisManage.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/task")
@Tag(name = "Task", description = "任务书相关接口")
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @GetMapping
    @Operation(summary = "获取任务书列表")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "获取任务书列表成功"),
        @ApiResponse(responseCode = "500", description = "获取任务书列表失败")
    })
    public ResponseEntity<Map<String, Object>> getTaskList() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Task> taskList = taskService.getTaskList();
            response.put("status", "success");
            response.put("data", taskList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching task list", e);
            throw new RuntimeException("Failed to fetch task list", e);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "通过ID获取任务书")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "获取任务书成功"),
        @ApiResponse(responseCode = "404", description = "任务书不存在"),
        @ApiResponse(responseCode = "500", description = "获取任务书失败")
    })
    public ResponseEntity<Map<String, Object>> getTaskById(
            @Parameter(name = "id",description = "中期报告ID",required = true) @PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Task> task = taskService.getTaskById(id);
            if (task.isPresent()) {
                response.put("status", "success");
                response.put("data", task.get());
                return ResponseEntity.ok(response);
            } else {
                log.error("Task not found");
                response.put("status", "error");
                response.put("message", "Task not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error fetching task", e);
            throw new RuntimeException("Failed to fetch task", e);
        }
    }

    @PutMapping
    @Operation(summary = "提交任务书")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "提交任务书成功"),
        @ApiResponse(responseCode = "500", description = "提交任务书失败")
    })
    public ResponseEntity<Map<String, Object>> submitTask(
            @Parameter(name = "task", description = "任务书实体", required = true) @RequestBody Task task) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Task> submittedTask = taskService.submitTask(task);
            if (submittedTask.isPresent()) {
                response.put("status", "success");
                response.put("data", submittedTask.get());
                return ResponseEntity.ok(response);
            } else {
                log.error("Failed to submit task");
                response.put("status", "error");
                response.put("message", "Failed to submit task");
                return ResponseEntity.internalServerError().body(response);
            }
        } catch (Exception e) {
            log.error("Error submitting task", e);
            throw new RuntimeException("Failed to submit task", e);
        }
    }

    @PostMapping
    @Operation(summary = "修改任务书")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "修改任务书成功"),
        @ApiResponse(responseCode = "404", description = "任务书不存在"),
        @ApiResponse(responseCode = "500", description = "修改任务书失败")
    })
    public ResponseEntity<Map<String, Object>> updateTask(
            @Parameter(name = "task", description = "任务书实体", required = true) @RequestBody Task task) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Task> updatedTask = taskService.updateTask(task);
            if (updatedTask.isPresent()) {
                response.put("status", "success");
                response.put("data", updatedTask.get());
                return ResponseEntity.ok(response);
            } else {
                log.error("Failed to update task, task not found");
                response.put("status", "error");
                response.put("message", "Failed to update task, task not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error updating task", e);
            throw new RuntimeException("Failed to update task", e);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除任务书")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "删除任务书成功"),
        @ApiResponse(responseCode = "404", description = "任务书不存在"),
        @ApiResponse(responseCode = "500", description = "删除任务书失败")
    })
    public ResponseEntity<Map<String, Object>> deleteTask(
            @Parameter(name = "id", description = "任务书ID", required = true) @PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean deleted = taskService.deleteTask(id);
            if (deleted) {
                response.put("status", "success");
                return ResponseEntity.ok(response);
            } else {
                log.error("Failed to delete task, task not found");
                response.put("status", "error");
                response.put("message", "Failed to delete task, task not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error deleting task", e);
            throw new RuntimeException("Failed to delete task", e);
        }
    }
}
