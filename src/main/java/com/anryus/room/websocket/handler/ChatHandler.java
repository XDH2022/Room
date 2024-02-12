package com.anryus.room.websocket.handler;

import com.anryus.room.model.Chat;
import com.anryus.room.model.JsonVO;
import com.anryus.room.service.ChatService;
import com.anryus.room.service.RoomUserService;
import com.anryus.room.utils.CommonExtKt;
import com.anryus.room.websocket.SessionPool;
import jakarta.annotation.Resource;
import jakarta.websocket.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatHandler {

    @Resource
    ChatService chatService;
    @Resource
    RoomUserService roomUserService;


    public void saveChat(Chat chat) {
        chatService.saveChat(chat);
    }

    /**
     * 将消息发送给聊天室的所有人
     * @param chat 聊天消息
     */
    public void sendToAllOnlineUserInRoom(Chat chat) {
        List<Integer> usersIdInRoom = roomUserService.getUsersIdInRoom(chat.getRoomId());
        usersIdInRoom.forEach(userId -> {
            Session session = SessionPool.getSession(userId.toString());
            if (session!=null && userId != chat.getSendUserId()){
                session.getAsyncRemote().sendText(CommonExtKt.toJson(JsonVO.success(chat)));
            }
        });

    }

}
