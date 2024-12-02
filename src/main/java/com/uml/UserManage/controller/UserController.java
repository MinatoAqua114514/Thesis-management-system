package com.uml.UserManage.controller;

import com.uml.UserManage.annotation.CheckPermission;
import com.uml.UserManage.entity.User;
import com.uml.UserManage.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 获取所有用户信息
    @GetMapping
    @CheckPermission("admin")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 获取指定用户的信息
    @GetMapping("{id}")
    @CheckPermission("admin")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        // 获取用户信息，使用Optional包装，避免返回null
        Optional<User> user = userService.getUserById(id);

        // 返回用户信息，若用户不存在返回404
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 创建用户
    @PostMapping
    @CheckPermission("admin")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // 创建用户，使用Optional包装，避免返回null
        Optional<User> createdUser = userService.createUser(user);

        // 返回用户信息，若用户创建失败返回500
        return createdUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    // 更新指定用户信息
    @PutMapping("{id}")
    @CheckPermission("admin")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        // 更新用户信息,使用Optional包装，避免返回null
        Optional<User> updatedUser = userService.updateUser(id, user);

        // 返回用户信息，若用户不存在返回404
        return updatedUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 删除指定用户
    @DeleteMapping("{id}")
    @CheckPermission("admin")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 解析Excel表，批量导入用户信息
    @PostMapping("/import")
    @CheckPermission("admin")
    public ResponseEntity<String> importUsers(@RequestParam("file") MultipartFile file) {
        // 导入用户信息
        userService.importUsers(file);

        return ResponseEntity.ok("导入成功");
    }

    // 获取所有用户信息，导入到Excel表格中
    @GetMapping("/export")
    @CheckPermission("admin")
    public ResponseEntity<ByteArrayResource> exportUsers() {
        try {
            byte[] data = userService.exportUsers();
            ByteArrayResource resource = new ByteArrayResource(data);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=用户信息.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(data.length)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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