package com.uml.ThesisManage.controller;

import com.uml.ThesisManage.entity.Submission;
import com.uml.ThesisManage.service.SubmissionService;
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
@RequestMapping("/api/submission")
@Tag(name = "Submission", description = "选题申报相关接口")
public class SubmissionController {

    private static final Logger log = LoggerFactory.getLogger(SubmissionController.class);

    @Autowired
    private SubmissionService submissionService;

    @GetMapping
    @Operation(summary = "获取选题申报列表")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "获取选题申报列表成功"),
        @ApiResponse(responseCode = "500", description = "获取选题申报列表失败")
    })
    public ResponseEntity<Map<String, Object>> getSubmissionList() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Submission> submissionList = submissionService.getSubmissionList();
            response.put("status", "success");
            response.put("data", submissionList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching submission list", e);
            throw new RuntimeException("Failed to fetch submission list", e);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "通过ID获取选题申报")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "获取选题申报成功"),
        @ApiResponse(responseCode = "404", description = "选题申报不存在"),
        @ApiResponse(responseCode = "500", description = "获取选题申报失败")
    })
    public ResponseEntity<Map<String, Object>> getSubmissionById(
            @Parameter(name = "id",description = "选题申报ID",required = true) @PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Submission> submission = submissionService.getSubmissionById(id);
            if (submission.isPresent()) {
                response.put("status", "success");
                response.put("data", submission.get());
                return ResponseEntity.ok(response);
            } else {
                log.error("Submission not found");
                response.put("status", "error");
                response.put("message", "Submission not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error fetching submission", e);
            throw new RuntimeException("Failed to fetch submission", e);
        }
    }

    @PutMapping
    @Operation(summary = "提交选题申报")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "提交选题申报成功"),
        @ApiResponse(responseCode = "500", description = "提交选题申报失败")
    })
    public ResponseEntity<Map<String, Object>> submitSubmission(
            @Parameter(name = "submission", description = "选题申报实体", required = true) @RequestBody Submission submission) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Submission> submittedSubmission = submissionService.submitSubmission(submission);
            if (submittedSubmission.isPresent()) {
                response.put("status", "success");
                response.put("data", submittedSubmission.get());
                return ResponseEntity.ok(response);
            } else {
                log.error("Failed to submit submission");
                response.put("status", "error");
                response.put("message", "Failed to submit submission");
                return ResponseEntity.internalServerError().body(response);
            }
        } catch (Exception e) {
            log.error("Error submitting submission", e);
            throw new RuntimeException("Failed to submit submission", e);
        }
    }

    @PostMapping
    @Operation(summary = "修改选题申报")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "修改选题申报成功"),
        @ApiResponse(responseCode = "404", description = "选题申报不存在"),
        @ApiResponse(responseCode = "500", description = "修改选题申报失败")
    })
    public ResponseEntity<Map<String, Object>> updateSubmission(
            @Parameter(name = "submission", description = "选题申报实体", required = true) @RequestBody Submission submission) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Submission> updatedSubmission = submissionService.updateSubmission(submission);
            if (updatedSubmission.isPresent()) {
                response.put("status", "success");
                response.put("data", updatedSubmission.get());
                return ResponseEntity.ok(response);
            } else {
                log.error("Failed to update submission, submission not found");
                response.put("status", "error");
                response.put("message", "Failed to update submission, submission not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error updating submission", e);
            throw new RuntimeException("Failed to update submission", e);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除选题申报")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "删除选题申报成功"),
        @ApiResponse(responseCode = "404", description = "选题申报不存在"),
        @ApiResponse(responseCode = "500", description = "删除选题申报失败")
    })
    public ResponseEntity<Map<String, Object>> deleteSubmission(
            @Parameter(name = "id", description = "选题申报ID", required = true) @PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean deleted = submissionService.deleteSubmission(id);
            if (deleted) {
                response.put("status", "success");
                response.put("message", "Submission deleted successfully");
                return ResponseEntity.ok(response);
            } else {
                log.error("Failed to delete submission, submission not found");
                response.put("status", "error");
                response.put("message", "Failed to delete submission, submission not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error deleting submission", e);
            throw new RuntimeException("Failed to delete submission", e);
        }
    }
}
