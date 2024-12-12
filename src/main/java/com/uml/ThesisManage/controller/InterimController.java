package com.uml.ThesisManage.controller;

import com.uml.ThesisManage.entity.Interim;
import com.uml.ThesisManage.service.InterimService;
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
@RequestMapping("/api/interim")
@Tag(name = "Interim", description = "中期报告相关接口")
public class InterimController {

    private static final Logger log = LoggerFactory.getLogger(InterimController.class);

    @Autowired
    private InterimService interimService;

    @GetMapping
    @Operation(summary = "获取中期报告列表")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "获取中期报告列表成功"),
        @ApiResponse(responseCode = "500", description = "获取中期报告列表失败")
    })
    public ResponseEntity<Map<String, Object>> getInterimList() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Interim> interimList = interimService.getInterimList();
            response.put("status", "success");
            response.put("data", interimList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching interim list", e);
            throw new RuntimeException("Failed to fetch interim list", e);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "通过ID获取中期报告")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "获取中期报告成功"),
        @ApiResponse(responseCode = "404", description = "中期报告不存在"),
        @ApiResponse(responseCode = "500", description = "获取中期报告失败")
    })
    public ResponseEntity<Map<String, Object>> getInterimById(
            @Parameter(name = "id",description = "中期报告ID",required = true) @PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Interim> interim = interimService.getInterimById(id);
            if (interim.isPresent()) {
                response.put("status", "success");
                response.put("data", interim.get());
                return ResponseEntity.ok(response);
            } else {
                log.error("Interim report not found");
                response.put("status", "error");
                response.put("message", "Interim report not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error fetching interim report", e);
            throw new RuntimeException("Failed to fetch interim report", e);
        }
    }

    @PutMapping
    @Operation(summary = "学生提交中期报告")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "提交中期报告成功"),
        @ApiResponse(responseCode = "500", description = "提交中期报告失败")
    })
    public ResponseEntity<Map<String, Object>> submitInterim(
            @CookieValue(value = "userId", defaultValue = "0") int userId,
            @Parameter(name = "interimReportFileId",description = "中期报告文件ID",required = true) @RequestParam("interimReportFileId") int interimReportFileId){
        Map<String, Object> response = new HashMap<>();
        Interim interim = new Interim();
        // 处理Cookie异常
        if (userId == 0) {
            log.error("User ID not found in cookies");
            response.put("status", "error");
            response.put("message", "User ID not found in cookies");
            return ResponseEntity.badRequest().body(response);
        } else {
            interim.setStudentId(userId);
        }
        // TODO 通过指导老师学生表获取指导老师的userId
        interim.setInterimReportFileId(interimReportFileId);
        interim.setStatus("pending");
        try {
            Optional<Interim> submittedInterim = interimService.submitInterim(interim);
            if (submittedInterim.isPresent()) {
                response.put("status", "success");
                response.put("data", submittedInterim.get());
                return ResponseEntity.ok(response);
            } else {
                log.error("Failed to submit interim report");
                response.put("status", "error");
                response.put("message", "Failed to submit interim report");
                return ResponseEntity.internalServerError().body(response);
            }
        } catch (Exception e) {
            log.error("Error submitting interim report", e);
            throw new RuntimeException("Failed to submit interim report", e);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除中期报告")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "删除中期报告成功"),
        @ApiResponse(responseCode = "404", description = "中期报告不存在"),
        @ApiResponse(responseCode = "500", description = "删除中期报告失败")
    })
    public ResponseEntity<Map<String, Object>> deleteInterim(
            @Parameter(name = "id",description = "中期报告ID",required = true) @PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean deleted = interimService.deleteInterim(id);
            if (deleted) {
                response.put("status", "success");
                response.put("message", "Interim report deleted successfully");
                return ResponseEntity.ok(response);
            } else {
                log.error("Failed to delete interim report, interim not found");
                response.put("status", "error");
                response.put("message", "Failed to delete interim report, interim not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error deleting interim report", e);
            throw new RuntimeException("Failed to delete interim report", e);
        }
    }
}