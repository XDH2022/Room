package com.anryus.room.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Data
public class Room extends BaseModel{
    String roomName;
    String roomDescription;

    public Room() {
    }
}
