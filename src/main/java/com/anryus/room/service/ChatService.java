package com.anryus.room.service;

import com.anryus.room.exception.NotFoundRoomException;
import com.anryus.room.exception.NotHavePermissionException;
import com.anryus.room.mapper.ChatMapper;
import com.anryus.room.mapper.RoomMapper;
import com.anryus.room.mapper.UserMapper;
import com.anryus.room.model.Chat;
import com.anryus.room.model.Room;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    UserMapper userMapper;
    ChatMapper chatMapper;
    RoomMapper roomMapper;
    RoomUserService roomUserService;
    RoomService roomService;
    UserService userService;


    public ChatService(UserMapper userMapper, ChatMapper chatMapper, RoomMapper roomMapper, RoomUserService roomUserService, RoomService roomService,UserService userService) {
        this.chatMapper = chatMapper;
        this.userMapper = userMapper;
        this.roomMapper = roomMapper;
        this.roomUserService = roomUserService;
        this.roomService = roomService;
        this.userService = userService;
    }

    /**
     * 获取聊天室内的聊天记录
     *
     * @param roomId   聊天室
     * @param userId   用户
     * @param lastTime 最早消息时间
     * @return 内容列表
     */
    public List<Chat> getChatsInRoom(int roomId, int userId, Long lastTime) {
        Room room = roomMapper.getRoomById(roomId);
        if (room != null) {
                if (roomUserService.isUserInRoom(roomId, userId)) {
                return chatMapper.getAllChatByRoomId(roomId, lastTime);
            } else {
                throw new NotHavePermissionException();
            }
        } else {
            throw new NotFoundRoomException();
        }
    }

    /**
     * 获取两个用户之间的所有聊天记录
     * @param sendId 发送者id
     * @param receiveId 接收者id
     * @param lastTime 聊天记录截止时间
     * @return 消息列表
     */
    public List<Chat> getChatsByUser(int sendId, int receiveId, long lastTime) {
        userService.checkUserExist(sendId);
        userService.checkUserExist(receiveId);
        List<Chat> senderChats = chatMapper.getAllChatBySender(sendId, receiveId, lastTime);
        List<Chat> receiverChats = chatMapper.getAllChatByReceiver(sendId, receiveId, lastTime);
        senderChats.addAll(receiverChats);

        senderChats.sort((o1, o2) -> {
            return Long.compare(o2.getCreateAt().getTime(), o1.getCreateAt().getTime());
        });

        return senderChats;
    }

    public void saveChat(Chat chat) {
        chatMapper.insertChat(chat);
    }
}
