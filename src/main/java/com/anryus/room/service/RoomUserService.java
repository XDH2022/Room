package com.anryus.room.service;

import com.anryus.room.exception.AlreadyInRoomException;
import com.anryus.room.mapper.RoomUserMapper;
import com.anryus.room.model.Room;
import com.anryus.room.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomUserService {
    RoomUserMapper roomUserMapper;
    UserService userService;

    public RoomUserService(RoomUserMapper roomUserMapper,UserService userService){
        this.roomUserMapper = roomUserMapper;
        this.userService = userService;
    }

    public boolean isUserInRoom(int roomId,int userId){
        return roomUserMapper.isUserInRoom(roomId,userId)>0;
    }

    public List<Integer> getUsersIdInRoom(int roomId){
        return roomUserMapper.getUsersIdInRoom(roomId);
    }

    public void joinRoom(int roomId,int userId){
        if (isUserInRoom(roomId,userId)){
            throw new AlreadyInRoomException();
        }else {
            roomUserMapper.insertRoomUser(userId,roomId);
        }
    }

    public List<User> getUsersInRoom(int roomId){
        List<Integer> usersIdInRoom = getUsersIdInRoom(roomId);
        return userService.getUsersInfo(usersIdInRoom);
    }

    public List<Room> getRoomList(int userId){
        return roomUserMapper.getRoomsByUser(userId);
    }

}
