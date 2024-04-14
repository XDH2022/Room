package com.anryus.room.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class Chat  extends BaseModel{
    int sendUserId;
    int receiveId;
    int roomId;
    String content;
}
