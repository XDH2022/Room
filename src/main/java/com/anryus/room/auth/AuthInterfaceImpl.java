package com.anryus.room.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.anryus.room.model.Permission;
import com.anryus.room.model.Role;
import com.anryus.room.service.PermissionService;
import com.anryus.room.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthInterfaceImpl implements StpInterface {
    PermissionService permissionService;
    UserService userService;

    public AuthInterfaceImpl(PermissionService permissionService, UserService userService){
        this.permissionService = permissionService;
        this.userService = userService;
    }

    @Override
    public List<String> getPermissionList(Object loginId, String s) {
        return permissionService.getPermissionByUserId((Integer) loginId).stream().map(Permission::getPermissionName).toList();
    }

    @Override
    public List<String> getRoleList(Object loginId, String s) {
        return userService.getUserRoles((Integer) loginId).stream().map(Role::getRoleName).toList();
    }
}
