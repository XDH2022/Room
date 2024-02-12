package com.anryus.room.service;

import com.anryus.room.exception.DatabaseUpdateException;
import com.anryus.room.exception.NotFoundRoomException;
import com.anryus.room.mapper.RoomMapper;
import com.anryus.room.model.Room;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private final RoomMapper roomMapper;


    public RoomService(RoomMapper roomMapper){
        this.roomMapper = roomMapper;
    }

    public int addNewRoom(Room room) {
        try {
            return roomMapper.insertNewRoom(room);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除聊天室
     * @param roomId 聊天室id
     * @param userId 聊天室所有者id
     */
    public void deleteRoom(int roomId,int userId) {
        Boolean exist = checkRoomExist(roomId);
        if (exist){
            int i = roomMapper.deleteRoom(roomId,userId);
            if (i<1){
                throw new DatabaseUpdateException("deleteRoom");
            }
        }else {
            throw new NotFoundRoomException();
        }

    }

    public Room updateRoom(Room room){
        Boolean exist = checkRoomExist(room.getRoomId());
        if (exist){
            int i = roomMapper.updateRoom(room);
            if (i<1){
                throw new DatabaseUpdateException("updateRoom");
            }
            return room;
        }else {
            throw new NotFoundRoomException();
        }
    }

    public Boolean checkRoomExist(int roomId){
        Room roomById = roomMapper.getRoomById(roomId);
        return roomById!=null;
    }
}
