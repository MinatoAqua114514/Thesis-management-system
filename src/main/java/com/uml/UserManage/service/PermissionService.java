package com.uml.UserManage.service;

import com.uml.UserManage.dao.PermissionMapper;
import com.uml.UserManage.dao.UserMapper;
import com.uml.UserManage.dao.UserRoleMapper;
import com.uml.UserManage.entity.Permission;
import com.uml.UserManage.entity.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    // 判断用户权限
    public boolean checkUserPermission(HttpServletRequest request, String requireRole) {
        // 从cookie中获取用户id
        String userIdStr = getUserIdFromCookie(request);

        if (userIdStr == null) {
            return false;
        }
        Integer userId = Integer.parseInt(userIdStr);
        User user = userMapper.findById(userId);
        return user != null && userRoleMapper.getRoleName(userId).equals(requireRole);
    }

    // 从cookie中获取用户id
    private String getUserIdFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    // 添加权限
    public void addPermission(String permissionName, String description) {
        permissionMapper.addPermission(permissionName, description);
    }

    // 删除权限
    public void deletePermission(Integer permissionId) {
        permissionMapper.deletePermission(permissionId);
    }

    // 更新权限
    public void updatePermission(Integer permissionId, String permissionName, String description) {
        permissionMapper.updatePermission(permissionId, permissionName, description);
    }

    // 根据Id获取权限
    public Permission getPermissionById(Integer permissionId) {
        return permissionMapper.getPermissionById(permissionId);
    }

    // 获取所有权限
    public List<Permission> getAllPermissions() {
        return permissionMapper.getAllPermissions();
    }
}
