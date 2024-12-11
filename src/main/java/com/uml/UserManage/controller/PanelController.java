package com.uml.UserManage.controller;

import com.uml.annotation.CheckPermission;
import com.uml.UserManage.entity.DefensePanelMember;
import com.uml.UserManage.service.PanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/panel")
public class PanelController {

    @Autowired
    private PanelService panelService;

    // 为评阅小组添加成员
    @PostMapping
    @CheckPermission("admin")
    public ResponseEntity<DefensePanelMember> insertPanelMember(@RequestBody DefensePanelMember defensePanelMember) {
        // 创建评阅小组成员，使用Optional包装，避免返回null
        Optional<DefensePanelMember> createdPanelMember = panelService.insertPanelMember(defensePanelMember);

        // 返回评阅小组成员信息，若创建失败返回500
        return createdPanelMember.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // 获取评阅小组的所有成员信息
    @GetMapping
    @CheckPermission("admin")
    public boolean getPanelMembers(Integer defensePanelId) {
        return panelService.getPanelMembers(defensePanelId);
    }

    // 获取评语小组指定成员信息
    @GetMapping("{id}")
    @CheckPermission("admin")
    public boolean getPanelMember(@PathVariable("id") Integer panelMemberId) {
        return panelService.getPanelMember(panelMemberId);
    }

    // 删除评阅小组成员
    @DeleteMapping("{id}")
    @CheckPermission("admin")
    public boolean deletePanelMember(@PathVariable("id") Integer panelMemberId) {
        return panelService.deletePanelMember(panelMemberId);
    }

    // 更新评阅小组成员信息
    @PutMapping("{id}")
    @CheckPermission("admin")
    public boolean updatePanelMember(@PathVariable("id") Integer panelMemberId, DefensePanelMember defensePanelMember) {
        return panelService.updatePanelMember(panelMemberId, defensePanelMember);
    }
}
