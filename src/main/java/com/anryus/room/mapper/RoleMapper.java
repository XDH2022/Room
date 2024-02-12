package com.anryus.room.mapper;

import com.anryus.room.model.Role;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RoleMapper {
    @Select("select role_id, role_name, role_description, create_at, update_at from t_role where role_id")
    Role getRoleById(int role);

    @Insert({"insert into t_role(role_name, role_description, create_at, update_at) values(#{role_name},#{role_description},now(),now())"})
    int insertNewRole(Role role);

    @Update("update t_role set role_name = #{role_name},role_description = #{role_description} where role_id = #{role_id}")
    int updateRole(Role role);

    @Delete("delete from t_role where role_id = ${role_id}")
    int deleteRole(int roleId);
}
