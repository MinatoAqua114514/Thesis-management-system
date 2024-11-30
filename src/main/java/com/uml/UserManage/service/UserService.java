package com.uml.UserManage.service;

import com.uml.UserManage.dao.UserMapper;
import com.uml.UserManage.entity.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 批量导入用户信息
    public void importUsers(MultipartFile file) {
        try {
            List<User> users = parseFile(file);
            validateUsersFromExcel(users);
            userMapper.batchInsert(users);
        } catch (Exception e) {
            throw new RuntimeException("导入失败: " + e.getMessage(), e);
        }
    }

    private List<User> parseFile(MultipartFile file) {
        List<User> users = new ArrayList<>();
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // 跳过标题行
                }
                User user = new User();
                user.setUsername(row.getCell(0).getStringCellValue());
                user.setPassword(row.getCell(1).getStringCellValue());
                // 根据需要解析其他字段
                users.add(user);
            }
        } catch (IOException e) {
            throw new RuntimeException("解析文件失败", e);
        }
        return users;
    }

    // 验证批量插入用户的信息是否正确
    private void validateUsersFromExcel(List<User> users) {
        for (User user : users) {
            if (user.getUsername() == null || user.getUsername().isEmpty()) {
                throw new RuntimeException("用户名不能为空");
            }
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                throw new RuntimeException("密码不能为空");
            }
        }
    }

    // 验证用户名和密码
    public User validateUser(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
