package com.uml.ThesisManage.controller;

import com.uml.ThesisManage.entity.Interim;
import com.uml.ThesisManage.service.InterimService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/interim")
//@Api(tags = "中期报告管理")
public class InterimController {

    private static final Logger log = LoggerFactory.getLogger(InterimController.class);

    @Autowired
    private InterimService interimService;

    @GetMapping
//    @ApiOperation(value = "获取所有中期报告")
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
}