package com.anryus.room.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoomAndUser extends BaseModel {
    int userId;
    boolean isArchive;
    Date lastReadTime;
    int roomId;

}
