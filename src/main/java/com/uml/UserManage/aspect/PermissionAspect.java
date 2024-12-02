package com.uml.UserManage.aspect;

import com.uml.UserManage.service.PermissionService;
import com.uml.UserManage.annotation.CheckPermission;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// 创建切面类
@Aspect
@Component
public class PermissionAspect {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private HttpServletRequest request;

    // 检查用户权限
    @Before("@annotation(checkPermission)")
    public void checkPermission(CheckPermission checkPermission) {
        String requiredPermission = checkPermission.value();
        if (!permissionService.checkUserPermission(request, requiredPermission)) {
            throw new SecurityException("权限不足");
        }
    }

}
