package com.anryus.room.mapper;

import com.anryus.room.model.Chat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatMapper {
    @Select("select t_chat.id,t_chat.send_user_id,t_chat.receive_id,t_chat.room_id,t_chat.content,t_chat.create_at,t_chat.update_at from t_chat where room_id = #{roomId} and create_at <= #{lastTime}")
    List<Chat> getAllChatByRoomId(int roomId,long lastTime);

    @Select("select t_chat.id,t_chat.send_user_id,t_chat.receive_user_id,t_chat.room_id,t_chat.content,t_chat.create_at,t_chat.update_at from t_chat where send_user_id = #{sendId} and receive_user_id = #{receiveId} and create_at = #{lastTime}")
    List<Chat> getAllChatBySender(int sendId,int receiveId,long lastTime);

    @Select("select t_chat.id,t_chat.send_user_id,t_chat.receive_user_id,t_chat.room_id,t_chat.content,t_chat.create_at,t_chat.update_at from t_chat where send_user_id = #{receiveId} and receive_user_id = #{sendId} and create_at = #{lastTime}")
    List<Chat> getAllChatByReceiver(int sendId, int receiveId, long lastTime);

    @Insert("insert into t_chat(send_user_id,receive_id,room_id,content) values(#{sendUserId},#{receiveId},#{roomId},#{content})")
    int insertChat(Chat chat);
}
