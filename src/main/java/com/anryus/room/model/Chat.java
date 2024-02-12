package com.anryus.room.model;

import lombok.Data;

import java.util.Date;

@Data
public class Chat {
    int chatId;
    int sendUserId;
    int receiveId;
    int roomId;
    String content;
    Date createAt;
    Date updateAt;
}
