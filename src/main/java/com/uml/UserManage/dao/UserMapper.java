package com.uml.UserManage.dao;

import com.uml.UserManage.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    // 获取所有用户信息
    List<User> getAllUsers();

    // 获取指定用户的信息
    User getUserById(@Param("userId") Integer userId);

    // 创建用户
    void createUser(User user);

    // 更新用户信息
    void updateUser(@Param("userId") Integer userId, User user);

    // 删除指定用户
    void deleteUser(@Param("userId") Integer userId);

    // 批量插入用户信息
    void batchInsert(List<User> users);

    // 用户名获取用户信息
    User selectByUsername(@Param("username") String username);
}