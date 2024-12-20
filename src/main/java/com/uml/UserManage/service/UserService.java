package com.uml.UserManage.service;

import com.uml.UserManage.dao.UserMapper;
import com.uml.UserManage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 获取所有用户信息
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    // 获取指定用户的信息
    public Optional<User> getUserById(Integer id) {
        return Optional.ofNullable(userMapper.getUserById(id));
    }

    // 创建用户
    public Optional<User> createUser(User user) {
        userMapper.createUser(user);
        return Optional.of(user);
    }

    // 更新用户信息
    public Optional<User> updateUser(Integer id, User user) {
        User oldUser = userMapper.getUserById(id);
        if (oldUser == null) {
            return Optional.empty();
        }
        userMapper.updateUser(id, user);
        return Optional.of(user);
    }

    // 删除指定用户
    public boolean deleteUser(Integer id) {
        User user = userMapper.getUserById(id);
        if (user == null) {
            return false;
        }
        userMapper.deleteUser(id);
        return true;
    }

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

    // 解析Excel表格信息
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
        User user = userMapper.selectByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // 导出用户信息至Excel表格
    public byte[] exportUsers() throws IOException {
        List<User> users = getAllUsers(); // 获取所有用户信息

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Users");

            // 创建表头
            Row headerRow = sheet.createRow(0);
            String[] columns = {"User ID", "Username", "Password", "Email", "Created At", "Updated At", "Deleted"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // 填充数据
            int rowNum = 1;
            for (User user : users) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(user.getUserId());
                row.createCell(1).setCellValue(user.getUsername());
                row.createCell(2).setCellValue(user.getPassword());
                row.createCell(3).setCellValue(user.getEmail());
                row.createCell(4).setCellValue(user.getCreatedAt().toString());
                row.createCell(5).setCellValue(user.getUpdatedAt().toString());
                row.createCell(6).setCellValue(user.getDeleted());
            }

            // 将数据写入ByteArrayOutputStream
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                return out.toByteArray();
            }
        }
    }
}
