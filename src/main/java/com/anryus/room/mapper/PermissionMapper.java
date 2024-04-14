package com.anryus.room.mapper;

import com.anryus.room.model.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper {

    @Select("select id from t_role_permission where role_id in #{id}")
    List<Integer> getPermissionIdByRoleId(List<Integer> ids);

    @Select("select id,permission_name, permission_parent, category, create_at, update_at from t_permission where id in #{permission_id}")
    List<Permission> getPermissionById(List<Integer> id);

}
