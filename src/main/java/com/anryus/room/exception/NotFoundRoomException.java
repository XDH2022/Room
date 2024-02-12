package com.anryus.room.exception;

import com.anryus.room.common.StatusCode;

public class NotFoundRoomException extends BaseException {
    public NotFoundRoomException(){
        super(StatusCode.NOT_FOUNT_ROOM);
    }
}
