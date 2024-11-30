package com.uml.UserManage.service;

import com.uml.UserManage.dao.UserMapper;
import com.uml.UserManage.dao.UserRoleMapper;
import com.uml.UserManage.entity.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    // 判断用户权限
    public boolean checkUserPermission(HttpServletRequest request, String requireRole) {
        // 从cookie中获取用户id
        String userId = getUserIdFromCookie(request);
        if (userId == null) {
            return false;
        }
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
}
