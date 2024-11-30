package com.uml.UserManage.service;

import com.uml.UserManage.dao.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    // 通过用户id获取角色名
    public String getRoleName(String userId) {
        return userRoleMapper.getRoleName(userId);
    }
}
