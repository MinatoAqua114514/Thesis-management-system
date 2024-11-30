package com.uml.UserManage.controller;

import com.uml.UserManage.entity.User;
import com.uml.UserManage.service.PermissionService;
import com.uml.UserManage.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    // 导入用户信息Excel表信息
    @PostMapping("/import")
    public ResponseEntity<String> importUsers(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        // 确认管理员权限
        if (!permissionService.checkUserPermission(request, "admin")) {
            return ResponseEntity.status(403).body("权限不足");
        }

        userService.importUsers(file);
        return ResponseEntity.ok("导入成功");
    }

    // 用户登录
    @PostMapping("/login")
    public  ResponseEntity<String> login(@RequestBody User loginRequest, HttpServletResponse response) {
        // 登录数据获取
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // 简单校验
        if (username == null || password == null) {
            return ResponseEntity.badRequest().body("用户名或密码不能为空");
        }

        // 验证用户名和密码
        User user = userService.validateUser(username, password);
        if (user == null) {
            return ResponseEntity.status(401).body("用户名或密码错误");
        }
        // 将用户ID存入网页cookie中
        Cookie cookie = new Cookie("userId", String.valueOf(user.getUserId()));
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok("登录成功");
    }
}