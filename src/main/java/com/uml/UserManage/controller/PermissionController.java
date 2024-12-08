package com.uml.UserManage.controller;

import com.uml.UserManage.annotation.CheckPermission;
import com.uml.UserManage.entity.Permission;
import com.uml.UserManage.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    // 添加权限
    @PostMapping
    @CheckPermission("admin")
    public void addPermission(String permissionName, String description) {
        permissionService.addPermission(permissionName, description);
    }

    // 获取所有权限
    @GetMapping
    @CheckPermission("admin")
    public List<Permission> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    // 删除权限
    @CheckPermission("admin")
    @DeleteMapping("{id}")
    public void deletePermission(@PathVariable("id") Integer permissionId) {
        permissionService.deletePermission(permissionId);
    }

    // 更新权限
    @CheckPermission("admin")
    @PutMapping("{id}")
    public void updatePermission(@PathVariable("id") Integer permissionId, String permissionName, String description) {
        permissionService.updatePermission(permissionId, permissionName, description);
    }

    // 根据Id获取权限
    @CheckPermission("admin")
    @GetMapping("{id}")
    public Permission getPermissionById(@PathVariable("id") Integer permissionId) {
        return permissionService.getPermissionById(permissionId);
    }
}
