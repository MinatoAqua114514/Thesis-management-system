package com.uml.UserManage.dao;

import com.uml.UserManage.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    // 批量插入用户信息
    void batchInsert(@Param("users") List<User> users);

    // 用户ID获取用户信息
    User findById(@Param("id") String id);

    // 用户名获取用户信息
    User findByUsername(@Param("username") String username);
}
