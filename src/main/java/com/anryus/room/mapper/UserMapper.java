package com.anryus.room.mapper;

import com.anryus.room.model.Role;
import com.anryus.room.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select id,password,role_id,create_at,update_at from t_user where username = #{username}")
    User getUserByUsername(String username);

    @Insert("insert into t_user(username,password,role_id,email,create_at,update_at) values (#{username},#{password},#{roleId},#{email},now(),now())")
    int insertNewUser(User user);

    @Update("update t_user set password = #{password},update_at = now()")
    int updateUserPassword(User user);

    @Delete("delete from t_user where id = #{user_id}")
    int deleteUsrByUserId(int userId);

    @Select("select username,password,role_id,create_at,update_at from t_user where user_id = #{user_id}")
    User getUserInfoByUserId(int userId);

    @Select("select role_id from t_user where id = #{user_id}")
    List<Role> getRoleByUserId(int userId);

    List<User> batchSelectUserInfo(@Param("userIds") List<Integer> userIds);

    @Select("select username,password,role_id,create_at,update_at from t_user where email = #{email}")
    User getUserByEmail(String email);

}
