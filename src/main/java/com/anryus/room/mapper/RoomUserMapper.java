package com.anryus.room.mapper;

import com.anryus.room.model.Room;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoomUserMapper {

    @Select("select id from t_user_room where user_id = #{userId} and room_id = #{roomId}")
    int isUserInRoom(int roomId,int userId);

    @Select("select t_user_room.user_id from t_user_room where room_id = #{roomId}")
    List<Integer> getUsersIdInRoom(int roomId);

    @Insert("insert into t_user_room(user_id,is_archive,room_id) values (#{userId},false,#{roomId})")
    void insertRoomUser(int userId,int roomId);

    @Select("select * from t_user_room where user_id = #{userId}")
    List<Room> getRoomsByUser(int userId);
}
