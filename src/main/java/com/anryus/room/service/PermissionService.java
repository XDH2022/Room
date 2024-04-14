package com.anryus.room.service;

import com.anryus.room.exception.CheckPermissionException;
import com.anryus.room.mapper.PermissionMapper;
import com.anryus.room.mapper.UserMapper;
import com.anryus.room.model.Permission;
import com.anryus.room.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    private final PermissionMapper permissionMapper;
    private final UserMapper userMapper;
    public PermissionService(PermissionMapper permissionMapper,UserMapper userMapper){
        this.permissionMapper = permissionMapper;
        this.userMapper = userMapper;
    }

    /**
     * 获取用户所拥有的权限
     * @param userId 用户id
     * @return 权限列表
     */
    public List<Permission> getPermissionByUserId(int userId){
        try{
            List<Role> roleByUserId = userMapper.getRoleByUserId(userId);
            List<Integer> roleIds = roleByUserId.stream().map(Role::getId).toList();

            List<Integer> permissionIds = permissionMapper.getPermissionIdByRoleId(roleIds);
            return permissionMapper.getPermissionById(permissionIds);
        }catch (Exception e){
            throw new CheckPermissionException();
        }

    }

}
