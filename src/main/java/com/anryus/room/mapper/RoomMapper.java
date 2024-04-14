package com.anryus.room.mapper;

import com.anryus.room.model.Room;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RoomMapper {

    @Select("select id, room_name, room_description, create_at, update_at from t_room where id = #{roomId}")
    Room getRoomById(int room);

    @Insert({"insert into t_room(room_name, room_description, create_at, update_at) values(#{roomName},#{roomDescription},now(),now())"})
    @SelectKey(statement = "select last_insert_id()",keyProperty = "roomId",keyColumn = "room_id",before = false,resultType = int.class)
    int insertNewRoom(Room room);

    @Update("update t_room set room_name = #{room_name},room_description = #{room_description} where id = #{roomId}")
    int updateRoom(Room room);

    @Delete("delete from t_room where id = ${roomId}")
    int deleteRoom(int roomId,int userId);
}
