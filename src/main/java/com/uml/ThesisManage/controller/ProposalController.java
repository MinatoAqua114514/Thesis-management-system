package com.uml.ThesisManage.controller;

import com.uml.ThesisManage.entity.Proposal;
import com.uml.ThesisManage.service.ProposalService;
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
@RequestMapping("/api/proposal")
@Tag(name = "Proposal", description = "开题报告相关接口")
public class ProposalController {

    private static final Logger log = LoggerFactory.getLogger(ProposalController.class);

    @Autowired
    private ProposalService proposalService;

    @GetMapping
    @Operation(summary = "获取开题报告列表")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "获取开题报告列表成功"),
        @ApiResponse(responseCode = "500", description = "获取开题报告列表失败")
    })
    public ResponseEntity<Map<String, Object>> getProposalList() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Proposal> proposalList = proposalService.getProposalList();
            response.put("status", "success");
            response.put("data", proposalList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching proposal list", e);
            throw new RuntimeException("Failed to fetch proposal list", e);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "通过ID获取开题报告")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "获取开题报告成功"),
        @ApiResponse(responseCode = "404", description = "开题报告不存在"),
        @ApiResponse(responseCode = "500", description = "获取开题报告失败")
    })
    public ResponseEntity<Map<String, Object>> getProposalById(
            @Parameter(name = "id",description = "开题报告ID",required = true) @PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Proposal> proposal = proposalService.getProposalById(id);
            if (proposal.isPresent()) {
                response.put("status", "success");
                response.put("data", proposal.get());
                return ResponseEntity.ok(response);
            } else {
                log.error("Proposal not found");
                response.put("status", "error");
                response.put("message", "Proposal not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error fetching proposal", e);
            throw new RuntimeException("Failed to fetch proposal", e);
        }
    }

    @PutMapping
    @Operation(summary = "提交开题报告")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "提交开题报告成功"),
        @ApiResponse(responseCode = "500", description = "提交开题报告失败")
    })
    public ResponseEntity<Map<String, Object>> submitProposal(
            @Parameter(name = "proposal",description = "开题报告",required = true) @RequestBody Proposal proposal) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Proposal> submittedProposal = proposalService.submitProposal(proposal);
            if (submittedProposal.isPresent()) {
                response.put("status", "success");
                response.put("data", submittedProposal.get());
                return ResponseEntity.ok(response);
            } else {
                log.error("Failed to submit proposal");
                response.put("status", "error");
                response.put("message", "Failed to submit proposal");
                return ResponseEntity.internalServerError().build();
            }
        } catch (Exception e) {
            log.error("Error submitting proposal", e);
            throw new RuntimeException("Failed to submit proposal", e);
        }
    }

    @PostMapping
    @Operation(summary = "修改开题报告")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "修改开题报告成功"),
        @ApiResponse(responseCode = "404", description = "开题报告不存在"),
        @ApiResponse(responseCode = "500", description = "修改开题报告失败")
    })
    public ResponseEntity<Map<String, Object>> updateProposal(
            @Parameter(name = "proposal",description = "开题报告",required = true) @RequestBody Proposal proposal) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<Proposal> updatedProposal = proposalService.updateProposal(proposal);
            if (updatedProposal.isPresent()) {
                response.put("status", "success");
                response.put("data", updatedProposal.get());
                return ResponseEntity.ok(response);
            } else {
                log.error("Failed to update proposal, proposal not found");
                response.put("status", "error");
                response.put("message", "Failed to update proposal, proposal not found");
                return ResponseEntity.internalServerError().build();
            }
        } catch (Exception e) {
            log.error("Error updating proposal", e);
            throw new RuntimeException("Failed to update proposal", e);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除开题报告")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "删除开题报告成功"),
        @ApiResponse(responseCode = "404", description = "开题报告不存在"),
        @ApiResponse(responseCode = "500", description = "删除开题报告失败")
    })
    public ResponseEntity<Map<String, Object>> deleteProposal(
            @Parameter(name = "id",description = "开题报告ID",required = true) @PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean deleted = proposalService.deleteProposal(id);
            if (deleted) {
                response.put("status", "success");
                return ResponseEntity.ok(response);
            } else {
                log.error("Failed to delete proposal, proposal not found");
                response.put("status", "error");
                response.put("message", "Failed to delete proposal, proposal not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error deleting proposal", e);
            throw new RuntimeException("Failed to delete proposal", e);
        }
    }
}
