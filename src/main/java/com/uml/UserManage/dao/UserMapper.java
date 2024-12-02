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
    User getUserById(@Param("id") Integer id);

    // 创建用户
    void createUser(@Param("user") User user);

    // 更新用户信息
    void updateUser(@Param("id") Integer id, @Param("user") User user);

    // 删除指定用户
    void deleteUser(@Param("id") Integer id);

    // 批量插入用户信息
    void batchInsert(@Param("users") List<User> users);

    // 用户ID获取用户信息
    User findById(@Param("id") String id);

    // 用户名获取用户信息
    User findByUsername(@Param("username") String username);
}
