package com.anryus.room.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class RoomAndUser {
    int id;
    int userId;
    boolean isArchive;
    Date lastReadTime;
    int roomId;
    Date createTime;
    Date updateTime;

}
