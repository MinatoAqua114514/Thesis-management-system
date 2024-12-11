package com.uml.ThesisManage.controller;

import com.uml.ThesisManage.entity.Thesis;
import com.uml.ThesisManage.service.ThesisService;
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
@RequestMapping("/api/thesis")
@Tag(name = "Thesis", description = "论文相关接口")
public class ThesisController {

    private static final Logger log = LoggerFactory.getLogger(ThesisController.class);

    @Autowired
    private ThesisService thesisService;

    @GetMapping
    @Operation(summary = "获取论文列表")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "获取论文列表成功"),
        @ApiResponse(responseCode = "500", description = "获取论文列表失败")
    })
    public ResponseEntity<Map<String, Object>> getThesisList() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Thesis> thesisList = thesisService.getThesisList();
            response.put("status", "success");
            response.put("data", thesisList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching thesis list", e);
            throw new RuntimeException("Failed to fetch thesis list", e);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "通过ID获取论文")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "获取论文成功"),
        @ApiResponse(responseCode = "404", description = "论文不存在"),
        @ApiResponse(responseCode = "500", description = "获取论文失败")
    })
    public ResponseEntity<Map<String, Object>> getThesisById(
            @Parameter(name = "id",description = "论文ID",required = true) @PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Thesis> thesis = thesisService.getThesisById(id);
            if (thesis.isPresent()) {
                response.put("status", "success");
                response.put("data", thesis.get());
                return ResponseEntity.ok(response);
            } else {
                log.error("Thesis not found");
                response.put("status", "error");
                response.put("message", "Thesis not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error fetching thesis", e);
            throw new RuntimeException("Failed to fetch thesis", e);
        }
    }

    @PutMapping
    @Operation(summary = "提交论文")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "提交论文成功"),
        @ApiResponse(responseCode = "500", description = "提交论文失败")
    })
    public ResponseEntity<Map<String, Object>> submitThesis(
            @Parameter(name = "thesis", description = "论文实体", required = true) @RequestBody Thesis thesis) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Thesis> submittedThesis = thesisService.submitThesis(thesis);
            if (submittedThesis.isPresent()) {
                response.put("status", "success");
                response.put("data", submittedThesis.get());
                return ResponseEntity.ok(response);
            } else {
                log.error("Failed to submit thesis");
                response.put("status", "error");
                response.put("message", "Failed to submit thesis");
                return ResponseEntity.internalServerError().body(response);
            }
        } catch (Exception e) {
            log.error("Error submitting thesis", e);
            throw new RuntimeException("Failed to submit thesis", e);
        }
    }

    @PostMapping
    @Operation(summary = "修改论文")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "修改论文成功"),
        @ApiResponse(responseCode = "404", description = "论文不存在"),
        @ApiResponse(responseCode = "500", description = "修改论文失败")
    })
    public ResponseEntity<Map<String, Object>> updateThesis(
            @Parameter(name = "thesis", description = "论文实体", required = true) @RequestBody Thesis thesis) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Thesis> updatedThesis = thesisService.updateThesis(thesis);
            if (updatedThesis.isPresent()) {
                response.put("status", "success");
                response.put("data", updatedThesis.get());
                return ResponseEntity.ok(response);
            } else {
                log.error("Failed to update thesis, thesis not found");
                response.put("status", "error");
                response.put("message", "Failed to update thesis, thesis not found");
                return ResponseEntity.internalServerError().body(response);
            }
        } catch (Exception e) {
            log.error("Error updating thesis", e);
            throw new RuntimeException("Failed to update thesis", e);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除论文")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "删除论文成功"),
        @ApiResponse(responseCode = "404", description = "论文不存在"),
        @ApiResponse(responseCode = "500", description = "删除论文失败")
    })
    public ResponseEntity<Map<String, Object>> deleteThesis(
            @Parameter(name = "id",description = "论文ID",required = true) @PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean deleted = thesisService.deleteThesis(id);
            if (deleted) {
                response.put("status", "success");
                response.put("message", "Thesis deleted successfully");
                return ResponseEntity.ok(response);
            } else {
                log.error("Failed to delete thesis, thesis not found");
                response.put("status", "error");
                response.put("message", "Failed to delete thesis, thesis not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error deleting thesis", e);
            throw new RuntimeException("Failed to delete thesis", e);
        }
    }
}
