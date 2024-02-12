package com.anryus.room.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Room {
    int roomId;
    String roomName;
    String roomDescription;
    String createAt;
    String updateAt;

    public Room() {
    }
}
